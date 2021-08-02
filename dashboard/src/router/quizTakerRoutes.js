import QuizTakerApp from '../components/quizTaker/QuizTakerApp';
import QuizTakerHomePage from '../components/quizTaker/QuizTakerHomePage';
import TakeQuiz from '../components/quizTaker/quiz/TakeQuiz';
import StartQuiz from '../components/quizTaker/quiz/StartQuiz';
import QuizResult from '../components/quizTaker/quiz/QuizResult';

export default [
  {
    path: '/quiz-taker',
    component: QuizTakerApp,
    meta: {
      requiresAuth: true,
      nonAdmin: true,
    },
    children: [{
      name: 'QuizTakerHomePage',
      path: '',
      component: QuizTakerHomePage,
      meta: {
        requiresAuth: true,
        nonAdmin: true,
      },
    }, {
      name: 'TakeQuiz',
      path: 'quizzes/:quizId/take',
      component: TakeQuiz,
      meta: {
        requiresAuth: true,
        nonAdmin: true,
      },
    }, {
      name: 'StartQuiz',
      path: 'quizzes/:quizId/start',
      component: StartQuiz,
      meta: {
        requiresAuth: true,
        nonAdmin: true,
      },
    }, {
      name: 'QuizResult',
      path: 'quizzes/:quizId/result',
      component: QuizResult,
      meta: {
        requiresAuth: true,
        nonAdmin: true,
      },
    }],
  },
];
