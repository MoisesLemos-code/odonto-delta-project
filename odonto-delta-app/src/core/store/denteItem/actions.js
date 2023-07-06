import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.DENTE_ITEM.BUSCAR_TODOS_DENTES_ITEM]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosServicos
        const {data} = await api.denteItem.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.DENTE_ITEM.CADASTRAR_DENTE_ITEM](context, dados) {
        const {data} = await api.denteItem.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.DENTE_ITEM.EDITAR_DENTE_ITEM](context, dados) {
        const {data} = await api.denteItem.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.DENTE_ITEM.BUSCAR_DENTE_ITEM_POR_ID](context, dados) {
        const {data} = await api.denteItem.buscarId(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.DENTE_ITEM.BUSCAR_TODOS_DENTES_SEM_PAGINACAO_ITEM](context) {
        const {data} = await api.denteItem.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.ORCAMENTO.DENTE_ITEM.EXCLUIR_DENTE_ITEM](context, id) {
        await api.denteItem.excluir(id)
    },
}