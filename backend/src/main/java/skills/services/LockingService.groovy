package skills.services

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import skills.storage.model.ProjDef
import skills.storage.model.SkillsDBLock
import skills.storage.model.UserAttrs
import skills.storage.model.UserPoints
import skills.storage.repos.SkillsDBLockRepo

@Service
@CompileStatic
class LockingService {

    @Autowired
    SkillsDBLockRepo skillsDBLockRepo

    SkillsDBLock lockGlobalSettings() {
        return skillsDBLockRepo.findByLock("global_settings_lock")
    }

    SkillsDBLock lockProjects() {
        return skillsDBLockRepo.findByLock("projects_lock")
    }

    SkillsDBLock lockGlobalBadges() {
        return skillsDBLockRepo.findByLock("global_badges_lock")
    }



    ProjDef lockProject(String projectId) {
        assert projectId
        return skillsDBLockRepo.findByProjectIdIgnoreCase(projectId)
    }

    UserAttrs lockUser(String userId) {
        assert userId
        return skillsDBLockRepo.findUserAttrsByUserId(userId?.toLowerCase())
    }

    UserPoints lockUserPoints(String projectId, String userId) {
        assert projectId
        assert userId
        return skillsDBLockRepo.findUserPointsByProjectIdAndUserId(projectId, userId)
    }



}