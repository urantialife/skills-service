/**
 * Copyright 2021 SkillTree
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
package skills.storage.model;

import javax.persistence.Lob;

public interface ProjectSummaryResult {
    public abstract int getProjectRefId();

    public abstract String getProjectId();

    public abstract String getProjectName();

    public abstract int getPoints();

    public abstract int getTotalPoints();

    public abstract int getLevel();

    public abstract int getTotalUsers();

    public abstract int getRank();

    @Lob
    public abstract String getOrderVal();

}
