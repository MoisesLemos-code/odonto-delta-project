import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERFIL_USUARIO.BUSCAR_PERFIS_DO_USUARIO](context, usuarioId) {
        const {data} = await api.perfilUsuario.buscarPerfisDoUsuario(usuarioId)
        return data
    },

    async [actionTypes.PERFIL_USUARIO.ATUALIZAR_PERFIS_DO_USUARIO](context, dados) {
        const {data} = await api.perfilUsuario.atualizarPerfisDoUsuario(dados)
        return data
    },

}
