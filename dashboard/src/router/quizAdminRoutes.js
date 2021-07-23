import QuizAdminApp from '../components/quizAdmin/QuizAdminApp';
import QuizAdminHome from '../components/quizAdmin/QuizAdminHome';
import QuizPage from '../components/quizAdmin/quizes/QuizPage';
import Questions from '../components/quizAdmin/questions/Questions';
import QuizMetrics from '../components/quizAdmin/questions/metrics/QuizMetrics';

export default [
  {
    path: '/quiz-admin',
    component: QuizAdminApp,
    meta: {
      requiresAuth: true,
      nonAdmin: true,
    },
    children: [{
      name: 'QuizAdminHome',
      path: '',
      component: QuizAdminHome,
      meta: {
        requiresAuth: true,
        nonAdmin: true,
      },
    }],
  },
  {
    path: '/quiz-admin/quizzes/:quizId',
    component: QuizPage,
    meta: { requiresAuth: true },
    children: [{
      name: 'Questions',
      path: '',
      component: Questions,
      meta: {
        requiresAuth: true,
        reportSkillId: 'VisitSubjects',
      },
    }, {
      name: 'QuizMetrics',
      path: 'metrics',
      component: QuizMetrics,
      meta: {
        requiresAuth: true,
        reportSkillId: 'VisitSubjects',
      },
    }],
  },
];
