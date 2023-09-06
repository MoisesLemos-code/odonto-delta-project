import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERFIL.BUSCAR_TODOS_PERFIS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosPerfis
        const {data} = await api.usuario.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.PERFIL.CADASTRAR_PERFIL]({commit}, dados) {
        await api.usuario.cadastrar(dados)
    },

    async [actionTypes.PERFIL.EDITAR_PERFIL]({commit}, dados) {
        const { data } = await api.usuario.editar(dados)
        return data
    },

    async [actionTypes.PERFIL.BUSCAR_PERFIL_POR_ID](context, id) {
        const {data} = await api.usuario.buscarId(id)
        return data
    },

    async [actionTypes.PERFIL.REMOVER_PERFIL](context, id) {
        const {data} = await api.usuario.buscarId(id)
        return data
    }
}
