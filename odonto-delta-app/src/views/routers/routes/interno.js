import FaseDeLances from '@/views/pages/fornecedor/lances/FaseDeLances'
import FaseDeLancesPregoeiro from '@/views/pages/pregoeiro/lances/FaseDeLances'
import Login from '@/views/pages/autenticacao/login'

import {routesNames} from '@/core/constants'

export default [
    {
        path: '/lances/pregao=:idPregao/fornecedor=:idFornecedor',
        name: routesNames.FASE_DE_LANCES_FORNECEDOR,
        component: FaseDeLances,
        meta: {
            independente: true
        }
    },
    {
        path: '/pregoeiro/pregao=:idPregao/lances',
        name: routesNames.FASE_DE_LANCES_PREGOEIRO,
        component: FaseDeLancesPregoeiro,
        meta: {
            independente: true
        }
    },
    {
        path: '/acesso',
        name: routesNames.ACESSO,
        component: Login,
        meta: {
            emptyTemplate: true,
            independente: true
        }
    }
]
