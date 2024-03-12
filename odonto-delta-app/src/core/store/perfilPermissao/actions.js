import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERFIL_PERMISSAO.BUSCAR_POR_PERFIL](context, id) {
        const {data} = await api.perfilPermissao.buscarPorPerfil(id)
        return data
    },

    async [actionTypes.PERFIL_PERMISSAO.CADASTRAR_PERMISSAO](context, dados) {
        await api.perfilPermissao.cadastrar(dados)
    },

    async [actionTypes.PERFIL_PERMISSAO.EDITAR_PERMISSAO](context, dados) {
        const { data } = await api.perfilPermissao.editar(dados)
        return data
    },

    async [actionTypes.PERFIL_PERMISSAO.REMOVER_PERMISSAO](context, id) {
        const {data} = await api.perfilPermissao.remover(id)
        return data
    }
}
