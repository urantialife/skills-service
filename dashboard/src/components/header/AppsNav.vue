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
            <b-col v-for="app in apps" :key="app.name" cols="6" class="text-center app-link"
                   :class="{ 'app-not-selected': !app.selected, 'app-selected': app.selected }">
              <router-link :to="app.navTo" :event="!app.selected ? 'click' : ''">
                <div @click="closeDropdown()">
                  <i class="border rounded" :class="app.icon"></i>
                </div>
                <div class="app-title text-secondary" :disabled="app.selected">{{ app.title }}</div>
                <div class="app-subTitle text-secondary" :disabled="app.selected">{{ app.subTitle }}</div>
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
          title: 'My',
          subTitle: 'Progress',
          selected: false,
          startPath: '/progress-and-rankings',
        });
      }
      myApps.push(...[{
        navTo: { name: 'AdminHomePage' },
        icon: 'fas fa-cogs skills-color-projectAdmin',
        title: 'Project',
        subTitle: 'Admin',
        selected: false,
        startPath: '/administrator',
      }, {
        navTo: { name: 'QuizTakerHomePage' },
        icon: 'fas fa-user-graduate text-success',
        title: 'Quiz',
        subTitle: 'Taker',
        selected: false,
        startPath: '/quiz-taker',
      }, {
        navTo: { name: 'QuizAdminHome' },
        icon: 'fas fa-tools text-warning border rounded',
        title: 'Quiz',
        subTitle: 'Builder',
        selected: false,
        startPath: '/quiz-admin',
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
          copy.selected = currentRouterItem.path && currentRouterItem.path.startsWith(item.startPath);
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

  .app-link .app-title {
    margin-top: 0.2rem;
    font-size: 0.8rem;
    font-weight: bold;
  }

  .app-link .app-subTitle {
    font-size: 0.8rem;
    font-weight: bold;
    line-height: 0.7rem;
  }

  .app-link i {
    font-size: 1.5rem;
    padding: 0.6rem;
  }

  .apps-dropdown-form {
    padding: 0rem 0.5rem 1.5rem 0.5rem;
  }

  .app-selected:hover, .app-selected a:hover {
    cursor: not-allowed !important;
    text-decoration: none !important;
  }

  .app-selected i {
    background-color: map_get($theme-colors, primary) !important;
    color: white !important;
  }

  .app-not-selected:hover i {
    background-color: #efefef;
    border-color: map_get($theme-colors, primary) !important;
  }

  .app-not-selected:hover .app-title, .app-not-selected:hover .app-subTitle {
    color: map_get($theme-colors, primary) !important;
  }

</style>
