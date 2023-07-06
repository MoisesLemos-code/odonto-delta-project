export default [
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/pages/login/Login'),
        meta: {
            requiresAuth: false,
            guest: true,
            page: {
                title: 'Login'
            }
        }
    },
]