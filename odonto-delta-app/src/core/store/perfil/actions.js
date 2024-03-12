import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERFIL.BUSCAR_TODOS_PERFIS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosPerfis
        const {data} = await api.perfil.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.PERFIL.CADASTRAR_PERFIL](context, dados) {
        await api.perfil.cadastrar(dados)
    },

    async [actionTypes.PERFIL.EDITAR_PERFIL](context, dados) {
        const { data } = await api.perfil.editar(dados)
        return data
    },

    async [actionTypes.PERFIL.BUSCAR_PERFIL_POR_ID](context, id) {
        const {data} = await api.perfil.buscarId(id)
        return data
    },

    async [actionTypes.PERFIL.REMOVER_PERFIL](context, id) {
        const {data} = await api.perfil.remover(id)
        return data
    }
}
