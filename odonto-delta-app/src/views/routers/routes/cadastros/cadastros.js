import paciente from './paciente'

export default [
    {
        path: '/cadastro',
        name: 'cadastros',
        component: () => import ('@/views/pages/cadastros/Cadastros'),
        children: [
            ...paciente
        ]
    }
]
