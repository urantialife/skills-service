/**
 * Copyright 2020 SkillTree
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package skills.services

import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import skills.services.admin.SkillsGroupAdminService
import skills.storage.model.ProjDef
import skills.storage.model.SkillDef
import skills.storage.model.SkillRelDef
import skills.storage.repos.ProjDefRepo
import skills.storage.repos.SkillDefRepo
import skills.storage.repos.SkillRelDefRepo


@Service
@Slf4j
class RuleSetDefinitionScoreUpdater {

    @Autowired
    SkillDefRepo skillDefRepo

    @Autowired
    SkillRelDefRepo skillRelDefRepo

    @Autowired
    ProjDefRepo projDefRepo

    @Autowired
    SkillsGroupAdminService skillsGroupAdminService


    void updateFromLeaf(SkillDef skillDef) {
        List<SkillRelDef> childRels = skillRelDefRepo.findAllByParentAndTypeIn(skillDef, [SkillRelDef.RelationshipType.RuleSetDefinition, SkillRelDef.RelationshipType.SkillsGroupRequirement])
        if (childRels) {
            int total
            if (skillDef.type == SkillDef.ContainerType.SkillsGroup) {
                total = skillsGroupAdminService.getGroupTotalPoints(childRels.collect({it.child}), skillDef.numSkillsRequired)
            } else {
                total = childRels.findAll { it.child.type != SkillDef.ContainerType.SkillsGroup || Boolean.valueOf(it.child.enabled) }.collect({ it.child.totalPoints }).sum() ?: 0
            }
            skillDef.totalPoints = total
            skillDefRepo.save(skillDef)
        }

        List<SkillRelDef> parents = skillRelDefRepo.findAllByChildAndTypeIn(skillDef, [SkillRelDef.RelationshipType.RuleSetDefinition, SkillRelDef.RelationshipType.SkillsGroupRequirement])
        parents?.each {
            updateFromLeaf(it.parent)
        }

        if (skillDef.projDef) {
            updateForProjectByOnlyConsideringImmediateChildren(skillDef.projDef)
        }
    }

    void skillToBeRemoved(SkillDef skillDef) {
        List<SkillRelDef> parents = skillRelDefRepo.findAllByChildAndTypeIn(skillDef, [SkillRelDef.RelationshipType.RuleSetDefinition, SkillRelDef.RelationshipType.SkillsGroupRequirement])
        if (isChildOfDisabledSkillsGroup(skillDef, parents)) {
            // disabled groups do not contribute to parent total points, just update the group itself
            SkillDef skillsGroupDef = parents.first().parent
            skillsGroupDef.totalPoints = skillsGroupDef.totalPoints - skillDef.totalPoints
            skillDefRepo.save(skillDef)
        } else {
            parents?.each {
                walkUpAndSubtractFromTotal(it.parent, skillDef.totalPoints)
            }
            if(SkillDef.ContainerType.Skill == skillDef.type){
                ProjDef projDef = projDefRepo.findByProjectId(skillDef.projectId)
                projDef.totalPoints -= skillDef.totalPoints
            }
        }
    }

    private void walkUpAndSubtractFromTotal(SkillDef skillDef, int pointsToSubtract) {
        skillDef.totalPoints = skillDef.totalPoints - pointsToSubtract
        skillDefRepo.save(skillDef)

        List<SkillRelDef> parents = skillRelDefRepo.findAllByChildAndTypeIn(skillDef, [SkillRelDef.RelationshipType.RuleSetDefinition, SkillRelDef.RelationshipType.SkillsGroupRequirement])
        parents?.each {
            walkUpAndSubtractFromTotal(it.parent, pointsToSubtract)
        }
    }

    void updateForProjectByOnlyConsideringImmediateChildren(ProjDef projDef) {
        List<SkillDef> subjects = skillDefRepo.findAllByProjectIdAndType(projDef.projectId, SkillDef.ContainerType.Subject)
        if (subjects) {
            int total = subjects.collect({ it.totalPoints }).sum()
            projDef.totalPoints = total
            projDefRepo.save(projDef)
        }
    }

    private boolean isChildOfDisabledSkillsGroup(SkillDef skillDef, List<SkillRelDef> parents) {
        boolean isSkillsGroupChild = StringUtils.isNotBlank(skillDef.groupId)
        if (isSkillsGroupChild) {
            assert parents && parents.size() == 1 && parents.first().parent.type == SkillDef.ContainerType.SkillsGroup && parents.first().parent.skillId == skillDef.groupId
            SkillDef skillsGroupDef = parents.first().parent
            return !Boolean.valueOf(skillsGroupDef.enabled)
        }
        return false
    }
}
