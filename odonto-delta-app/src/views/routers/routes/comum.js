export default [
    {
        path: '/perfil-sem-acesso',
        name: 'perfilSemAcesso',
        component: () => import('@/views/pages/commons/perfil/PerfilSemAcesso'),
        meta: {
            page: {
                title: 'Perfil sem acesso!',
                subtitle: null
            },
            requiresAuth: true
        }
    },
    {
        path: '*',
        name: 'PaginaNaoEncontrada',
        component: () => import('@/views/pages/commons/pagina-nao-encontrada/PaginaNaoEncontrada'),
        meta: {
            page: {
                title: 'Ooops',
                subtitle: 'Página não encontrada'
            }
        }
    },
]