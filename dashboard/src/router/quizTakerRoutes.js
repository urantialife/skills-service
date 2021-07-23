import QuizTakerApp from '../components/quizTaker/QuizTakerApp';
import QuizTakerHomePage from '../components/quizTaker/QuizTakerHomePage';

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
    }],
  },
];
