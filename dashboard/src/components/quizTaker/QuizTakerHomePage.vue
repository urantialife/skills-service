<template>
  <div>
    <sub-page-header title="My Quizzes" class="pt-4">
    </sub-page-header>

    <quiz-overview-cards class="mb-4"/>
    <available-quizzes class="mb-4" />
    <my-quizzes class="mb-4" />
  </div>
</template>

<script>
  import SubPageHeader from '../utils/pages/SubPageHeader';
  import MyQuizzes from './MyQuizzes';
  import QuizOverviewCards from './quizStats/QuizOverviewCards';
  import AvailableQuizzes from './quizBrowser/AvailableQuizzes';

  export default {
    name: 'QuizTakerHomePage',
    components: {
      AvailableQuizzes, QuizOverviewCards, MyQuizzes, SubPageHeader,
    },
  };
</script>

<style scoped>

</style>
