import Router from 'vue-router'
import interno from './routes/interno'
import guards from './guards'
import { routesNames } from '@/core/constants'
import NaoAutorizado from '@/views/pages/NaoAutorizado'
import NaoEncontrado from '@/views/pages/NaoEncontrado'

const index = new Router({
    routes: [
        ...interno,
        {
            path: '*',
            name: routesNames.PAGINA_NAO_ENCONTRADA,
            component: NaoEncontrado,
            meta: {
                independente: true
            }
        },
        {
            path: '/401',
            name: routesNames.PAGINA_NAO_AUTORIZADO,
            component: NaoAutorizado,
            meta: {
                independente: true
            }
        }
    ]
})

index.beforeEach((to, from, next) => guards.condicoes(to, from, next))

export default index
