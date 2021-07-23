<template>
  <div>
    <b-card header="My Quiz History">

      <div v-for="(q, index) in myQuizHistory" :key="q.quizId">
        <my-quiz-run :quiz-run="q" />

        <hr v-if="index + 1 < myQuizHistory.length"/>
      </div>

    </b-card>
  </div>
</template>

<script>
  import MyQuizRun from './myQuizRun/MyQuizRun';

  export default {
    name: 'MyQuizzes',
    components: { MyQuizRun },
    data() {
      return {
        isLoading: false,
        myQuizHistory: [
          {
            name: 'My First Cool Quiz',
            quizId: 'myFirstCoolQuiz',
            numAttempts: 1,
            startedOn: 1626892802373,
            completedOn: 1626892932373,
            numQuestions: 12,
            numQuestionsAnswered: 12,
            numQuestionsAnsweredRight: 11,
            isInProgress: false,
          },
          {
            name: 'Some other test',
            quizId: 'testone',
            numAttempts: 1,
            startedOn: 1626872831373,
            completedOn: 1626892932373,
            numQuestions: 12,
            numQuestionsAnswered: 12,
            numQuestionsAnsweredRight: 11,
            isInProgress: false,
          },
        ],
      };
    },
  };
</script>

<style scoped>

</style>
