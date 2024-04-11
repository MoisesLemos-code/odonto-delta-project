import Orcamento from '@/views/pages/orcamento/Orcamento'
import OrcamentoListagem from '@/views/pages/orcamento/listagem/OrcamentoListagem'
import OrcamentoVisualizacao from '@/views/pages/orcamento/visualizacao/OrcamentoVisualizacao'
import OrcamentoDadosGerais from '@/views/pages/orcamento/dados-gerais/OrcamentoDadosGerais'
import OrcamentoPaciente from '@/views/pages/orcamento/dados-gerais/passo01/OrcamentoPaciente'
import OrcamentoOdontograma from '@/views/pages/orcamento/dados-gerais/passo02/OrcamentoOdontograma'
import OrcamentoFinalizacao from '@/views/pages/orcamento/dados-gerais/passo03/OrcamentoFinalizacao'

export default [
    {
        path: '/orcamentos',
        name: 'OrcamentoListagem',
        component: OrcamentoListagem,
        meta: {
            requiresAuth: true,
            page: {
                title: 'Orçamentos',
                subtitle: 'Lista geral de orçamentos'
            }
        }
    },
    {
        path: '/orcamento',
        name: 'Orcamento',
        component: Orcamento,
        children: [
            {
                path: '/orcamento/:id/visualizacao',
                name: 'OrcamentoVisualizacao',
                component: OrcamentoVisualizacao,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Orçamento',
                        subtitle: 'Visualização de Orçamento'
                    }
                }
            },
        ]
    },
    {
        path: '/orcamento/:orcamentoId',
        name: 'OrcamentoDadosGerais',
        component: OrcamentoDadosGerais,
        children: [
            {
                path: 'paciente',
                name: 'OrcamentoPaciente',
                component: OrcamentoPaciente,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Orçamento',
                        subtitle: 'Dados do paciente'
                    },
                    perfis: ['ADMINISTRADOR', 'ORCAMENTO_INSERT', 'ORCAMENTO_UPDATE', 'ORCAMENTO_DELETE'],
                }
            },
            {
                path: 'odontograma',
                name: 'OrcamentoOdontograma',
                component: OrcamentoOdontograma,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Orçamento',
                        subtitle: 'Odontograma'
                    },
                    perfis: ['ADMINISTRADOR', 'ORCAMENTO_INSERT', 'ORCAMENTO_UPDATE', 'ORCAMENTO_DELETE'],
                }
            },
            {
                path: 'finalizacao',
                name: 'OrcamentoFinalizacao',
                component: OrcamentoFinalizacao,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Orçamento',
                        subtitle: 'Finalização'
                    },
                    perfis: ['ADMINISTRADOR', 'ORCAMENTO_INSERT', 'ORCAMENTO_UPDATE', 'ORCAMENTO_DELETE'],
                }
            },
        ]
    }
]