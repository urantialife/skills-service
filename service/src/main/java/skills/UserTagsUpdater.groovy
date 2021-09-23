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
package skills

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import skills.storage.model.UserAttrs
import skills.storage.repos.UserAttrsRepo

@Component
@Slf4j
class UserTagsUpdater {

    @Autowired
    UserDetailsService userDetailsService

    @Autowired
    UserAttrsRepo userAttrsRepo

    void populateExistingUsers(String userId, boolean userIdNotInUserTags=true) {

        List<UserAttrs> userAttrs
        if (userId) {
            userAttrs = userAttrsRepo.findByUserIdGreaterThanOrderByUserId(userId)
        } else {
            if (userIdNotInUserTags) {
                userAttrs = userAttrsRepo.findAllWhereNotInUserTags()
            } else {
                userAttrs = userAttrsRepo.findAll()
            }
        }

        int totalUsers = userAttrs.size()
        int notFoundInCasport = 0
        int otherErrors = 0
        log.info("Found [${totalUsers}] userAttrs")
        userAttrs.eachWithIndex { userAttr, idx ->
            try {
                if (idx % 100 == 0) {
                    showStats(idx, notFoundInCasport, otherErrors)
                }
                userDetailsService.loadUserByUsername(userAttr.dn)
            } catch (Exception e) {
//                log.error("Failed to update user [${userAttr.dn}]", e)
                if (e.message && e.message.containsIgnoreCase("not found in Casport")) {
                    notFoundInCasport++
                } else {
                    otherErrors++
                }
            }
        }
        showStats(totalUsers, notFoundInCasport, otherErrors)
    }

    void showStats(int currentIdx, int notFoundInCasport, int otherErrors) {
        log.info("Updated user [${currentIdx}] users. users not found in CASPORT: [${notFoundInCasport}], other errors: [${otherErrors}].")
    }
}
