export default {
    asideClosed: false,
    asideHide: true,
    avatarActions: {},
    menuActions: [
        // {
        //     path: '/orcamentos',
        //     icon: 'mdi-account-cash',
        //     name: 'Orçamentos'
        // },
        {
            path: '/cadastros',
            icon: 'mdi-book',
            name: 'Cadastros',
            children: [
                {
                    path: '/pacientes',
                    icon: 'mdi-account-box',
                    name: 'Pacientes',
                },
                {
                    path: '/cobrancas',
                    icon: 'mdi-cash-multiple',
                    name: 'Cobranças',
                },
            ]
        },
        // {
        //     path: '/relatorios',
        //     icon: 'mdi-clipboard-outline',
        //     name: 'Relatórios',
        //     children: [
        //         {
        //             path: '/relatorios/pacientes',
        //             icon: 'mdi-clipboard-account-outline',
        //             name: 'Relatório de Pacientes'
        //         },
        //         {
        //             path: '/relatorios/orcamentos',
        //             icon: 'mdi-clipboard-pulse-outline',
        //             name: 'Relatório de Orçamentos'
        //         },
        //     ]
        // },
        {
            path: '/configuracao',
            icon: 'settings',
            name: 'Configurações',
            children: [
                {
                    path: '/configuracao/meu-usuario',
                    icon: 'mdi-account',
                    name: 'Meu Usuário'
                },
                {
                    path: '/configuracao/gerenciar-usuarios',
                    icon: 'mdi-account-settings',
                    name: 'Gerenciar Usuários'
                },
                {
                    path: '/configuracao/gerenciar-perfis',
                    icon: 'mdi-key',
                    name: 'Gerenciar Perfis'
                },
            ]
        },

    ]
}
