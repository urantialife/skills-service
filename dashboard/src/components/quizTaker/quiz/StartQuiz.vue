<template>
  <div>
    <sub-page-header title="Start Quiz" class="pt-4">
    </sub-page-header>

    <b-card>
      <b-row :no-gutters="true">
        <b-col cols="auto">
          <span class="fa-stack" style="vertical-align: top; font-size: 2rem;">
                <i class="fas fa-circle fa-stack-2x text-info"></i>
                <i class="fa-stack-1x fa-inverse fas fa-chess"></i>
          </span>
        </b-col>
        <b-col class="ml-2">
          <div class="h2 text-primary text-uppercase">{{ quiz.name }}</div>
          <p class="text-secondary">
            {{ quiz.description }}
          </p>

          <b-row>
            <b-col cols="auto">
              <div>
                <i class="far fa-question-circle text-secondary" style="width: 1.5rem;"></i>
                <span class="text-info">Number of Questions:</span>
              </div>
              <div>
                <i class="far fa-hourglass text-secondary" style="width: 1.5rem;"></i>
                <span class="text-info" style="width: 3rem;">Time to Complete:</span>
              </div>
              <div>
                <i class="fas fa-space-shuttle text-secondary" style="width: 1.5rem;"></i>
                <span class="text-info" style="width: 3rem;">Difficulty:</span>
              </div>
            </b-col>
            <b-col>
              <div><b-badge>{{ quiz.numQuestions }}</b-badge></div>
              <div><b-badge variant="warning">~{{ quiz.timeToCompleteInMins }}</b-badge> minutes</div>
              <div class="text-uppercase"><b-badge variant="success">{{ quiz.difficulty }}</b-badge></div>
            </b-col>
          </b-row>

          <router-link :data-cy="`take_quiz-${quiz.quizId}`"
                       :to="{ name:'TakeQuiz', params: { quizId: quiz.quizId }}"
                       :aria-label="`Take Quiz ${quiz.name}`"
                       class="btn btn-outline-primary mt-4">
            <span class="d-none d-sm-inline">Start Quiz </span> <i class="fas fa-arrow-circle-right" aria-hidden="true"/>
          </router-link>

        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
  import SubPageHeader from '../../utils/pages/SubPageHeader';

  export default {
    name: 'Quiz',
    components: { SubPageHeader },
    data() {
      return {
        quiz: {
          name: 'My First Cool Quiz',
          description: 'Fun trivia questions! It will require you to know soo many interesting thins and when you are done you will be very happy!',
          quizId: 'myFirstCoolQuiz',
          numQuestions: 12,
          timeToCompleteInMins: 20,
          difficulty: 'easy',
        },
      };
    },
  };
</script>

<style scoped>

</style>
