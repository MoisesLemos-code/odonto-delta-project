import paciente from './paciente'
import cobranca from './cobranca'

export default [
    {
        path: '/cadastro',
        name: 'cadastros',
        component: () => import ('@/views/pages/cadastros/Cadastros'),
        children: [
            ...paciente,
            ...cobranca
        ]
    }
]
