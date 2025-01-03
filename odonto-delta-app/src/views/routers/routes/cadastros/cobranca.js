import CobrancaListagem from '@/views/pages/cadastros/cobranca/CobrancaListagem.vue'


export default [
    {
        path: '/cobrancas',
        name: 'CobrancaListagem',
        component: CobrancaListagem,
        meta: {
            requiresAuth: true,
            page: {
                title: 'Cobrança',
                subtitle: 'Lista geral cobranças'
            },
            perfis: ['ADMINISTRADOR', 'PACIENTE_ATUALIZAR'],
        }
    },
]