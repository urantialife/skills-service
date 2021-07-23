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
            <b-col v-for="app in apps" :key="app.name" cols="6" class="text-center app-link" :class="{ 'app-not-selected': !app.selected }">
              <router-link :to="app.navTo" :disabled="app.selected">
                <div @click="closeDropdown()" :class="{ 'app-selected': app.selected }">
                  <i class="border rounded" :class="app.icon"></i>
                </div>
                <div class="app-label" :disabled="app.selected">{{ app.name }}</div>
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
          selected: false,
        });
      }
      myApps.push(...[{
        navTo: { name: 'AdminHomePage' },
        icon: 'fas fa-cogs skills-color-projectAdmin',
        name: 'Admin',
        selected: false,
      }, {
        navTo: { name: 'QuizTakerHomePage' },
        icon: 'fas fa-user-graduate text-success',
        name: 'Take Quiz',
        selected: false,
      }, {
        navTo: { name: 'QuizAdminHome' },
        icon: 'fas fa-tasks text-warning border rounded',
        name: 'Create Quiz',
        selected: false,
      }]);
      this.apps = myApps;
      this.updateSelected(this.$route);
    },
    methods: {
      closeDropdown() {
        this.$refs.appsDropdown.hide(false);
      },
      isProgressAndRankingEnabled() {
        return this.$store.getters.config.rankingAndProgressViewsEnabled === true || this.$store.getters.config.rankingAndProgressViewsEnabled === 'true';
      },
      updateSelected(currentRouterItem) {
        this.apps = this.apps.map((item) => {
          const copy = { ...item };
          copy.selected = currentRouterItem.path && currentRouterItem.name === item.navTo.name;
          return copy;
        });
      },
    },
    watch: {
      $route(to) {
        this.updateSelected(to);
      },
    },
  };
</script>

<style lang="scss" scoped>
  @import "../../assets/custom";

  .app-link {
    margin-top: 1rem !important;
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

  .app-selected i {
    background-color: map_get($theme-colors, primary) !important;
  }

  .app-not-selected:hover i {
    background-color: #efefef;
    border-color: map_get($theme-colors, primary) !important;
  }

  .app-not-selected:hover .app-label {
    color: map_get($theme-colors, primary) !important;
  }

</style>
