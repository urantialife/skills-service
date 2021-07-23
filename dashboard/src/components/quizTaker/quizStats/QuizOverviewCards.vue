<template>
  <div>
    <b-row>
      <b-col>
        <b-card footer-tag="footer">
          <b-row>
            <b-col cols="auto" style="height: 8.5rem;">
              <i class="far fa-check-circle skills-color-subjects" style="font-size: 7rem;"></i>
            </b-col>
            <b-col>
              <div class="mb-1">
                <div class="h1">2</div>
                <div class="text-uppercase text-secondary">Completed</div>
              </div>
              <div>
                <b-badge>1 quiz</b-badge>
                in the last week
              </div>
              <div>
                <b-badge>2 quizes</b-badge>
                in the last month
              </div>
            </b-col>
          </b-row>

          <template #footer>
            <div style="height: 2rem;">
              Congrats on completed
              <b-badge variant="success">2</b-badge>
              quizes!
            </div>
          </template>
        </b-card>
      </b-col>
      <b-col>
        <b-card footer-tag="footer">
          <b-row>
            <b-col cols="auto" style="height: 8.5rem;">
              <i class="fas fa-running skills-color-points" style="font-size: 7rem;"></i>
            </b-col>
            <b-col>
              <div class="mb-1">
                <div class="h1">0</div>
                <div class="text-uppercase text-secondary">In Progress</div>
              </div>
              <div>
                <b-badge>3 months</b-badge>
                since completing a quiz
              </div>
            </b-col>
          </b-row>

          <template #footer>
            <div style="height: 2rem;">
              <b-row>
                <b-col>No quizzes in progress.</b-col>
<!--                <b-col cols="auto">-->
<!--                  <b-button variant="outline-info" size="sm">Explore Quizzes</b-button>-->
<!--                </b-col>-->
              </b-row>
            </div>
          </template>
        </b-card>
      </b-col>
      <b-col>
        <b-card footer-tag="footer">
          <b-row>
            <b-col cols="auto" style="height: 8.5rem;">
              <i class="fas fa-medal skills-color-badges" style="font-size: 7rem;"></i>
            </b-col>
            <b-col>
              <div class="mb-1">
                <div class="h1">12</div>
                <div class="text-uppercase text-secondary">Available</div>
              </div>
            </b-col>
          </b-row>

          <template #footer>
            <div style="height: 2rem;">
              <b-row>
                <b-col><b-badge variant="success">12</b-badge> quizzes available!</b-col>
<!--                <b-col cols="auto">-->
<!--                  <b-button variant="outline-info" size="sm">Take a Quiz</b-button>-->
<!--                </b-col>-->
              </b-row>
            </div>
          </template>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
  export default {
    name: 'QuizOverviewCards',
  };
</script>

<style scoped>
  .title-on-top {
    font-size: 0.5em;
    line-height: 1.2em;
    margin-top: 1.8em;
    background: rgba(255, 255, 255, 0.6) !important;
  }
</style>
