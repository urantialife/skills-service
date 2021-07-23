<template>
  <div>
      <sub-page-header title="Quizzes" action="Quiz" />

      <b-card body-class="p-0">
        <quizzes-table />
      </b-card>

  </div>
</template>

<script>
  import SubPageHeader from '../utils/pages/SubPageHeader';
  import QuizzesTable from './quizes/QuizzesTable';

  export default {
    name: 'QuizAdminHome',
    components: { QuizzesTable, SubPageHeader },
  };
</script>

<style scoped>

</style>
