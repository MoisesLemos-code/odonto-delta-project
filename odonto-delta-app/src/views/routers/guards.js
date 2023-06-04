import store from '@/core/store'
import { Alert } from '@/core/utils'
import { routesNames } from '@/core/constants'

const alert = new Alert(store)

class RouterGuards {
    async condicoes(to, from, next) {
        this.validarPermissaoAdministrador(to, from, next)
    }

    validarPermissaoAdministrador(to, from, next) {
        if (to.meta.requiresAuth) {
            let possuiPermissao = true

            if (possuiPermissao) {
                next()
            } else {
                alert.showError('O seu usuário não tem acesso a esta página.')
                next({ name: routesNames.PAGINA_NAO_AUTORIZADO })
            }
        } else {
            next()
        }
    }
}

export default new RouterGuards()
