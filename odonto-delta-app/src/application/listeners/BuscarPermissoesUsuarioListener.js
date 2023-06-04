import store from '@/core/store'
import {actionTypes} from '@/core/constants'

class BuscarPermissoesUsuarioListener {
    async execute() {
        return store.dispatch(actionTypes.COMUM.BUSCAR_PERMISSOES_USUARIO)
    }
}

export default new BuscarPermissoesUsuarioListener()