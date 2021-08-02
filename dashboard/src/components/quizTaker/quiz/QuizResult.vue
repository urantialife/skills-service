<template>
  <div>
    <sub-page-header title="Quiz Result" class="pt-4">
    </sub-page-header>

    <b-card class="mb-5">
      <my-quiz-run :quizRun="quizResult"/>

      <p class="text-secondary">
        {{ quizResult.description }}
      </p>

      <b-card header="Questions">
        <div v-for="question in quizResult.questions" :key="question.questionId" class="mb-4">
          <div class="">
            <span class="font-weight-bold text-secondary">{{ question.ask }}</span>
          </div>

          <div class="mt-2 ml-3">
            <div v-for="(a) in question.answers" :key="a" class="mt-1">
                <span style="font-size: 1.3rem;" class="mr-1">
                  <i v-if="a.isSelected" class="far fa-check-square"></i>
                  <i v-else class="far fa-square"></i>
                </span>
              {{ a.label }}. {{ a.value }}
              <b-badge v-if="a.isAnswer && a.isSelected" variant="success">Correct</b-badge>
              <b-badge v-if="!a.isAnswer && a.isSelected" variant="danger">Incorrect</b-badge>
            </div>
          </div>
        </div>
      </b-card>

    </b-card>

  </div>
</template>

<script>
  import SubPageHeader from '../../utils/pages/SubPageHeader';
  import MyQuizRun from '../myQuizRun/MyQuizRun';

  export default {
    name: 'Quiz',
    components: {
      MyQuizRun,
      SubPageHeader,
    },
    data() {
      return {
        quizResult: {
          name: 'My First Cool Quiz',
          quizId: 'myFirstCoolQuiz',
          description: 'Fun trivia questions! It will require you to know soo many interesting thins and when you are done you will be very happy!',
          numAttempts: 1,
          startedOn: 1626892802373,
          completedOn: 1626892932373,
          numQuestions: 12,
          numQuestionsAnswered: 12,
          numQuestionsAnsweredRight: 11,
          isInProgress: false,
          questions: [{
            questionNum: 4,
            questionId: 'question2',
            ask: 'What fruit do kids traditionally give to teachers?',
            answers: [{
              answerId: 'Banana',
              label: 'A',
              value: 'Banana',
              isAnswer: false,
              isSelected: false,
            }, {
              answerId: 'Pineapple',
              label: 'B',
              value: 'Pineapple',
              isAnswer: false,
              isSelected: false,
            }, {
              answerId: 'Apple',
              label: 'C',
              value: 'Apple',
              isAnswer: true,
              isSelected: true,
            }, {
              answerId: 'Pear',
              label: 'D',
              value: 'Pear',
              isAnswer: false,
              isSelected: false,
            }],
          }, {
            questionNum: 4,
            questionId: 'question2',
            ask: 'What fruit do kids traditionally give to teachers?',
            answers: [{
              answerId: 'Banana',
              label: 'A',
              value: 'Banana',
              isAnswer: false,
              isSelected: false,
            }, {
              answerId: 'Pineapple',
              label: 'B',
              value: 'Pineapple',
              isAnswer: false,
              isSelected: true,
            }, {
              answerId: 'Apple',
              label: 'C',
              value: 'Apple',
              isAnswer: true,
              isSelected: false,
            }, {
              answerId: 'Pear',
              label: 'D',
              value: 'Pear',
              isAnswer: false,
              isSelected: false,
            }],
          }, {
            questionNum: 4,
            questionId: 'question2',
            ask: 'What fruit do kids traditionally give to teachers?',
            answers: [{
              answerId: 'Banana',
              label: 'A',
              value: 'Banana',
              isAnswer: false,
              isSelected: false,
            }, {
              answerId: 'Pineapple',
              label: 'B',
              value: 'Pineapple',
              isAnswer: false,
              isSelected: false,
            }, {
              answerId: 'Apple',
              label: 'C',
              value: 'Apple',
              isAnswer: true,
              isSelected: true,
            }, {
              answerId: 'Pear',
              label: 'D',
              value: 'Pear',
              isAnswer: false,
              isSelected: false,
            }],
          }],
        },
      };
    },
  };
</script>

<style scoped>

</style>
