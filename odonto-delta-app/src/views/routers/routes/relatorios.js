import Relatorios from '@/views/pages/relatorios/Relatorios'
import RelatorioPaciente from '@/views/pages/relatorios/paciente/RelatorioPaciente'
import RelatorioOrcamento from '@/views/pages/relatorios/orcamento/RelatorioOrcamento'

export default [
    {
        path: '/relatorios',
        name: 'Relatorios',
        component: Relatorios,
        children: [
            {
                path: 'pacientes',
                name: 'RelatorioPaciente',
                component: RelatorioPaciente,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Relatórios',
                        subtitle: 'Pacientes'
                    },
                    perfis: ['ADMINISTRADOR', 'RELATORIO_PACIENTE'],
                    containerProprio: true
                }
            },
            {
                path: 'orcamentos',
                name: 'RelatorioOrcamento',
                component: RelatorioOrcamento,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Relatórios',
                        subtitle: 'Orçamento'
                    },
                    perfis: ['ADMINISTRADOR', 'RELATORIO_ORCAMENTO'],
                    containerProprio: true
                }
            },
        ]
    },
]