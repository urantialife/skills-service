/*
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
describe('My Usage Tests', () => {

    before(() => {
        Cypress.env('disableResetDb', true);
        cy.resetDb();

        cy.fixture('vars.json').then((vars) => {
            cy.logout()

            if (!Cypress.env('oauthMode')) {
                cy.log('NOT in oauthMode, using form login')
                cy.login(vars.defaultUser, vars.defaultPass);
            } else {
                cy.log('oauthMode, using loginBySingleSignOn')
                cy.loginBySingleSignOn()
            }
        });

        for (let i = 9; i >= 2; i -= 1) {
            cy.createProject(i);
            cy.enableProdMode(i);
            cy.addToMyProjects(i)
        }

    });
    after(() => {
        Cypress.env('disableResetDb', false);
    });


    it('point history chart - 4 projects are loaded by default', () => {
        cy.visit('/progress-and-rankings');
        cy.get('[data-cy="viewUsageBtn"]')
            .click();

        // validate 4 projects are loaded by default
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 3')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 4')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 5')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('not.be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 7')
            .should('not.be.visible');
    })

    it('point history chart - remove project', () => {
        cy.visit('/progress-and-rankings');
        cy.get('[data-cy="viewUsageBtn"]')
            .click();

        cy.get('[data-cy=eventHistoryChartProjectSelector] .multiselect__tag-icon')
            .should('have.length', 4)
            .as('removeBtns');
        cy.get('@removeBtns')
            .eq(2)
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 3')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 4')
            .should('not.be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 5')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('not.be.visible');
    });

    it('point history chart - add project', () => {
        cy.visit('/progress-and-rankings/my-usage');

        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('be.visible')
            .as('project6');
        cy.get('@project6')
            .click();
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 3')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 4')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 5')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('be.visible');

    });

    it('point history chart - only up to 5 projects can be selected', () => {
        cy.visit('/progress-and-rankings/my-usage');

        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('be.visible')
            .as('project6');
        cy.get('@project6')
            .click();
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 3')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 4')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 5')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 6')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('Maximum of 5 options selected');

    });

    it('point history chart - all projects removed', () => {
        cy.visit('/progress-and-rankings/my-usage');

        cy.get('[data-cy=eventHistoryChartProjectSelector] .multiselect__tag-icon')
            .should('have.length', 4)
            .as('removeBtns');
        for (let i = 0; i < 4; i += 1) {
            cy.get('@removeBtns')
                .eq(0)
                .click()
        }
        cy.get('[data-cy=eventHistoryChart]')
            .contains('Please select at least one project from the list above.');

    });

    it('point history chart - projects with no user events', () => {
        cy.visit('/progress-and-rankings/my-usage');

        cy.get('[data-cy=eventHistoryChartProjectSelector] .multiselect__tag-icon')
            .should('have.length', 4)
            .as('removeBtns');
        for (let i = 0; i < 4; i += 1) {
            cy.get('@removeBtns')
                .eq(0)
                .click()
        }
        cy.get('[data-cy=eventHistoryChart]')
            .contains('Please select at least one project from the list above.');

        // select just project 2 (which has no user events yet)
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .click()
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 2')
            .should('be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 3')
            .should('not.be.visible');
        cy.get('[data-cy=eventHistoryChartProjectSelector]')
            .contains('project 4')
            .should('not.be.visible');
        // cy.get('[data-cy=eventHistoryChartProjectSelector]').click()
        cy.get('[data-cy=eventHistoryChart]')
            .contains('There are no events for the selected project(s) and time period.');
    });

    it('point history chart - time controls call out to the server',() => {

        cy.intercept('GET', '/api/metrics/allProjectsSkillEventsOverTimeMetricsBuilder*').as('pointHistoryChart');

        cy.visit('/progress-and-rankings/my-usage');

        cy.get('[data-cy=eventHistoryChart] [data-cy=timeLengthSelector]').contains('6 months').click();
        cy.wait('@pointHistoryChart');
        cy.get('[data-cy=eventHistoryChart] [data-cy=timeLengthSelector]').contains('1 year').click();
        cy.wait('@pointHistoryChart');
    });
})