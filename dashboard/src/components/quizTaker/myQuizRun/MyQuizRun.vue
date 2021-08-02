<template>
  <div>
    <b-row :no-gutters="true">
      <b-col sm="auto">
        <quiz-score></quiz-score>
      </b-col>
      <b-col class="pt-2 ml-2">
        <b-row>
          <b-col>
            <span class="h2 font-weight-bold text-secondary">{{ quizRun.name }}</span>
          </b-col>
          <b-col class="text-right">
            <span class="h5"><b-badge>{{ dayOfWeekEndTime }}</b-badge></span>
            <div style="font-size: 0.9rem;" class="text-muted"><i class="fas fa-calendar-day"></i> {{ value | date }}</div>
          </b-col>
        </b-row>

        <b-row class="mt-3">
          <b-col>
            <my-quiz-info-card sub-title="Quiz Duration" icon="fas fa-stopwatch-20 skills-color-subjects">
              <template v-slot:title>
                {{ quizRuntime }}
                <b-badge v-if="fastRuntime" variant="success"><i class="fas fa-rocket"></i> Fast</b-badge>
                <b-badge v-else variant="warning"><i class="fas fa-chess-knight"></i> Good Effort</b-badge>
              </template>
            </my-quiz-info-card>
          </b-col>
          <b-col>
            <my-quiz-info-card sub-title="Attempt" icon="fas fa-running skills-color-points">
              <template v-slot:title>
                <b-badge>{{ quizRun.numAttempts }} </b-badge> <i class="fas fa-thumbs-up text-success"></i>
              </template>
            </my-quiz-info-card>
          </b-col>
          <b-col>
            <my-quiz-info-card sub-title="Correct Questions" icon="far fa-question-circle skills-color-badges">
              <template v-slot:title>
                <span class="text-success font-weight-bold">{{ quizRun.numQuestionsAnsweredRight | number }}</span> / {{  quizRun.numQuestions | number }}
              </template>
            </my-quiz-info-card>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>

</template>

<script>
  import QuizScore from './QuizScore';
  import dayjs from '../../../DayJsCustomizer';
  import MyQuizInfoCard from './MyQuizInfoCard';

  export default {
    name: 'MyQuizRun',
    components: { MyQuizInfoCard, QuizScore },
    props: {
      quizRun: Object,
    },
    computed: {
      quizRuntime() {
        // const start = dayjs(this.quizRun.startedOn);
        // const end = dayjs(this.quizRun.completedOn);
        return dayjs.duration(this.quizRun.completedOn - this.quizRun.startedOn, 'ms').format('m[ Minutes ]');
      },
      fastRuntime() {
        const millis = this.quizRun.completedOn - this.quizRun.startedOn;
        console.log(millis);
        if (millis > (1000 * 60 * 5)) {
          return false;
        }

        return true;
      },
      dayOfWeekEndTime() {
        return dayjs(this.quizRun.completedOn).format('dddd');
      },
    },
  };
</script>

<style scoped>

</style>
