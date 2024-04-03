import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERMISSAO.BUSCAR_TODAS_PERMISSOES_USUARIO](context, id) {
        const {data} = await api.permissao.buscarTodasPermissoesPorUsuario(id)
        return data
    },

}
