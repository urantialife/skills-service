<template>
  <b-dropdown v-if="apps" right variant="link" aria-label="Available Apps Dropdown" ref="appsDropdown">
    <template #button-content>
      <i class="fas fa-th text-primary" style="font-size: 1.4rem;"></i>
    </template>
    <b-dropdown-text style="width: 14rem;">
      <div class="text-secondary"><i class="fas fa-th mr-1" aria-hidden="true"/>  Available Apps</div>
    </b-dropdown-text>
    <b-dropdown-divider />
    <b-dropdown-form form-class="apps-dropdown-form">
      <b-container fluid>
        <div style="width: 12rem;">
          <b-row :no-gutters="false">
            <b-col v-for="app in apps" :key="app.name" cols="6" class="text-center app-link">
              <router-link :to="app.navTo">
                <div @click="closeDropdown()">
                  <i class="border rounded" :class="app.icon"></i>
                </div>
                <div class="app-label">{{ app.name }}</div>
              </router-link>
            </b-col>
          </b-row>
        </div>
      </b-container>
    </b-dropdown-form>
  </b-dropdown>
</template>

<script>
  export default {
    name: 'AppsNav',
    data() {
      return {
        apps: [],
      };
    },
    mounted() {
      const myApps = [];
      if (this.isProgressAndRankingEnabled()) {
        myApps.push({
          navTo: { name: 'MyProgressPage' },
          icon: 'fas fa-chart-bar skills-color-progressAndRanking',
          name: 'Progress',
        });
      }
      myApps.push(...[{
        navTo: { name: 'AdminHomePage' },
        icon: 'fas fa-cogs skills-color-projectAdmin',
        name: 'Admin',
      }, {
        navTo: { name: 'MyProgressPage' },
        icon: 'fas fa-user-graduate text-success',
        name: 'Take Quiz',
      }, {
        navTo: { name: 'MyProgressPage' },
        icon: 'fas fa-tasks text-warning border rounded',
        name: 'Create Quiz',
      }]);
      this.apps = myApps;
    },
    methods: {
      closeDropdown() {
        this.$refs.appsDropdown.hide(false);
      },
      isProgressAndRankingEnabled() {
        return this.$store.getters.config.rankingAndProgressViewsEnabled === true || this.$store.getters.config.rankingAndProgressViewsEnabled === 'true';
      },
    },
  };
</script>

<style lang="scss" scoped>
  @import "../../assets/custom";

  .app-link {
    margin-top: 1rem !important;
  }

  .app-link:hover i {
    background-color: #efefef;
    border-color: map_get($theme-colors, primary) !important;
  }

  .app-link:hover .app-label {
    color: map_get($theme-colors, primary) !important;
  }

  .app-link .app-label {
    margin-top: 0.2rem;
    font-size: 0.8rem;
    font-weight: bold;
  }

  .app-link i {
    font-size: 1.5rem;
    padding: 0.6rem;
  }

  .apps-dropdown-form {
    padding: 0rem 0.5rem 1.5rem 0.5rem;
  }
</style>
