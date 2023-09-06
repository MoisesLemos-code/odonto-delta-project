import Configuracao from '@/views/pages/configuracao/Configuracao'
import MeuUsuario from '@/views/pages/configuracao/meuUsuario/MeuUsuario'
import GerenciarUsuario from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuario'
import GerenciarUsuarioCadastro from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuarioCadastro'
import GerenciarPerfis from '@/views/pages/configuracao/gerenciarPerfis/GerenciarPerfis.vue'

export default [
    {
        path: '/configuracao',
        name: 'Configurações',
        component: Configuracao,
        children: [
            {
                path: '/configuracao/meu-usuario',
                name: 'MeuUsuario',
                component: MeuUsuario,
                meta: {
                    requiresAuth: true,
                    containerProprio: true,
                    page: {
                        title: 'Meu Usuário',
                        subtitle: 'Perfil'
                    }
                }
            },
            {
                path: '/configuracao/gerenciar-usuarios',
                name: 'GerenciarUsuarios',
                component: GerenciarUsuario,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Gerenciar Usuários'
                    },
                    perfis: ['ADMINISTRADOR', 'USER_FIND'],
                }
            },
            {
                path: '/configuracao/gerenciar-perfis',
                name: 'GerenciarPerfis',
                component: GerenciarPerfis,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Gerenciar Perfis'
                    },
                    perfis: ['ADMINISTRADOR'],
                }
            },
            {
                path: '/configuracao/gerenciar-usuarios/cadastro',
                name: 'CadastrarUsuario',
                component: GerenciarUsuarioCadastro,
                meta: {
                    requiresAuth: true,
                    page: {
                        title: 'Gerenciar Usuários',
                        subtitle: 'Cadastrar usuário'
                    },
                    perfis: ['ADMINISTRADOR'],
                    containerProprio: true
                }
            }
        ]
    },
]