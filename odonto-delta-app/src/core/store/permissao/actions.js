import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERMISSAO.BUSCAR_TODAS_PERMISSOES]({context}, id) {
        const {data} = await api.permissao.buscarTodos(id)
        return data
    },

}
