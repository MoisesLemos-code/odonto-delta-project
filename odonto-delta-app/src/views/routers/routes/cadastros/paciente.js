import Paciente from '@/views/pages/cadastros/paciente/Paciente'
import PacienteListagem from '@/views/pages/cadastros/paciente/listagem/PacienteListagem'
import PacienteFicha from '@/views/pages/cadastros/paciente/ficha/PacienteFicha'
import PacienteDadosGeraisCadastro from '@/views/pages/cadastros/paciente/cadastro/PacienteDadosGeraisCadastro'
import PacienteDadosGeraisEdicao from '@/views/pages/cadastros/paciente/edicao/PacienteDadosGeraisEdicao'

export default [
    {
        path: '/pacientes',
        name: 'PacienteListagem',
        component: PacienteListagem,
        meta: {
            requiresAuth: true,
            page: {
                title: 'Pacientes',
                subtitle: 'Lista geral pacientes'
            }
        }
    },
    {
        path: '/paciente',
        name: 'Paciente',
        component: Paciente,
        children: [
            {
                path: '/paciente/:id/ficha',
                name: 'PacienteFicha',
                component: PacienteFicha,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Paciente',
                        subtitle: 'Ficha de Paciente'
                    },
                }
            },
            {
                path: '/orcamento/:orcamentoId/paciente/:id/ficha',
                name: 'PacienteFichaOrcamento',
                component: PacienteFicha,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Orçamento',
                        subtitle: 'Ficha de Paciente'
                    },
                }
            },
            {
                path: '/paciente/cadastro',
                name: 'PacienteCadastro',
                component: PacienteDadosGeraisCadastro,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Paciente',
                        subtitle: 'Cadastro de Paciente'
                    },
                }
            },
            {
                path: '/paciente/:id/edicao',
                name: 'PacienteEdicao',
                component: PacienteDadosGeraisEdicao,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Paciente',
                        subtitle: 'Edição de Paciente'
                    },
                    perfis: ['ADMINISTRADOR', 'PACIENTE_UPDATE'],
                }
            }
        ]
    }
]