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
package skills.intTests.utils

class SkillsFactory {

    static String DEFAULT_PROJ_NAME = "Test Project"
    static String DEFAULT_PROJ_ID_PREPEND = DEFAULT_PROJ_NAME.replaceAll(" ", "")

    static String getDefaultProjId(int projNum = 1) {
        DEFAULT_PROJ_ID_PREPEND + "${projNum}"
    }

    static String getDefaultProjName(int projNum = 1) {
        DEFAULT_PROJ_NAME + "#${projNum}"
    }

    static String getSubjectId(int subjNumber = 1) {
        return "TestSubject${subjNumber}".toString()
    }

    static String getSubjectName(int subjNumber = 1) {
        return "Test Subject #${subjNumber}".toString()
    }

    static Map createSkill(int projNumber = 1, int subjNumber = 1, int skillNumber = 1, int version = 0, int numPerformToCompletion = 1, pointIncrementInterval = 480, pointIncrement = 10, type="Skill") {
        return [projectId: getDefaultProjId(projNumber), subjectId: getSubjectId(subjNumber),
                skillId: "skill${skillNumber}${subjNumber > 1 ? "subj" + subjNumber : ""}".toString(),
                name     : "Test Skill ${skillNumber}${subjNumber > 1 ? " Subject" + subjNumber : "" }".toString(),
                type     : type, pointIncrement: pointIncrement, numPerformToCompletion: numPerformToCompletion,
                pointIncrementInterval: pointIncrementInterval, numMaxOccurrencesIncrementInterval: 1,
                description: "This skill [skill${skillNumber}] belongs to project [${getDefaultProjId(projNumber)}]".toString(),
                helpUrl: "http://veryhelpfulwebsite-${skillNumber}".toString(),
                version  : version]
    }

    static List<Map> createSkills(int numSkills, int projNumber = 1, int subjNumer = 1l, int pointIncrement = 10) {
        return (1..numSkills).collect { createSkill(projNumber, subjNumer, it, 0, 1, 480, pointIncrement) }
    }

    static List<Map> createSkillsWithDifferentVersions(List<Integer> skillVersions, int projNumber = 1, int subjNumber = 1) {
        int num = 1
        return skillVersions.collect { createSkill(projNumber, subjNumber, num++, it)}
    }

    static createProject(int projNumber = 1) {
        return [projectId: getDefaultProjId(projNumber), name: getDefaultProjName(projNumber)]
    }

    static createSubject(int projNumber = 1, int subjNumber = 1) {
        return [projectId: getDefaultProjId(projNumber), subjectId: getSubjectId(subjNumber), name: getSubjectName(subjNumber)]
    }

    static createBadge(int projNumber = 1, int badgeNumber = 1) {
        return [projectId: getDefaultProjId(projNumber), badgeId: "badge${badgeNumber}".toString(), name: "Test Badge ${badgeNumber}".toString()]
    }

    static createSkillsGroup(int projNumber = 1, int subjNumber = 1l, int groupNumber = 1, numSkillsRequired = -1, enabled = false) {
        def skillsGroup = createSkill(projNumber, subjNumber, groupNumber, 0, 0, 0, 0, "SkillsGroup")
        skillsGroup.numSkillsRequired = numSkillsRequired
        skillsGroup.enabled = enabled
        return skillsGroup
    }
}
