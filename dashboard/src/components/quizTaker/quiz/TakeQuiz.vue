<template>
  <div>
    <sub-page-header :title="quiz.name" class="pt-4">
      <template v-slot:rightOfTitle>
        <span class="h5"><b-badge variant="success" class="ml-2"><i class="fas fa-user-clock"></i> In Progress</b-badge></span>
      </template>

      <b-row class="justify-content-end">
        <b-col cols="auto">
<!--          <div>-->
<!--            <b-badge variant="warning">5</b-badge>-->
<!--            minutes left-->
<!--          </div>-->
          <div class="text-secondary">
            <b-badge>{{ question.questionNum }}</b-badge>
            /
            <b-badge>{{ quiz.numQuestions }}</b-badge>
            questions
          </div>

          <b-progress :max="quiz.numQuestions" class="mb-3" height="6px" variant="success">
            <b-progress-bar :value="question.questionNum"  :aria-labelledby="`points_${question.questionNum}`"></b-progress-bar>
          </b-progress>
        </b-col>
      </b-row>

    </sub-page-header>

    <b-card>
      <b-row :no-gutters="true">
        <b-col cols="auto">
           <span class="h5"><b-badge>{{ question.questionNum}}.</b-badge></span>
        </b-col>
        <b-col class="ml-2">
          <div class="">
            <span class="h4">{{ question.ask }}</span>
          </div>

          <div class="mt-3">

            <b-form-group
              v-slot="{ ariaDescribedby }"
            >
              <b-form-checkbox-group
                v-model="questionSelection.selected"
                :options="questionSelection.options"
                :aria-describedby="ariaDescribedby"
                name="flavour-2a"
                stacked
                size="lg"
                @change="selected"
              ></b-form-checkbox-group>
            </b-form-group>

          </div>
        </b-col>
      </b-row>

      <router-link :data-cy="`take_quiz-${quiz.quizId}`"
                   :to="{ name:'QuizResult', params: { quizId: quiz.quizId }}"
                   :aria-label="`Take Quiz ${quiz.name}`"
                   class="btn btn-outline-primary btn-sm mt-5">
        <span class="d-none d-sm-inline">Next Question </span> <i class="fas fa-arrow-circle-right" aria-hidden="true"/>
      </router-link>
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
        },
        question: {
          questionNum: 4,
          questionId: 'question2',
          ask: 'What fruit do kids traditionally give to teachers?',
          answers: [{
            answerId: 'Banana',
            label: 'A',
            value: 'Banana',
          }, {
            answerId: 'Pineapple',
            label: 'B',
            value: 'Pineapple',
          }, {
            answerId: 'Apple',
            label: 'C',
            value: 'Apple',
          }, {
            answerId: 'Pear',
            label: 'D',
            value: 'Pear',
          }],
        },
        questionSelection: {
          selected: [], // Must be an array reference!
          lastSelection: [],
          options: [],
        },
      };
    },
    mounted() {
      this.questionSelection.options = this.question.answers.map((item) => ({ text: `${item.label}. ${item.value}`, value: item.answerId }));
    },
    methods: {
      selected(vals) {
        this.questionSelection.selected = vals.filter((item) => !this.questionSelection.lastSelection.includes(item));
        this.questionSelection.lastSelection = this.questionSelection.selected;
      },
    },
  };
</script>

<style scoped>

</style>
