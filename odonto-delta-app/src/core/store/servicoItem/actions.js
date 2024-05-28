import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.BUSCAR_TODOS_SERVICOS_ITEM]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosServicosItem
        const {data} = await api.servicoItem.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.BUSCAR_SERVICOS_ITEM_POR_DENTE_ITEM]({state}, id) {
        const {filtros, paginacao} = state.resultadoBuscaTodosServicosItem
        filtros.idDenteItem.value = id
        const {data} = await api.servicoItem.buscarTodosPorDenteItem(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.CADASTRAR_SERVICO_ITEM](context, dados) {
        const {data} = await api.servicoItem.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.EDITAR_SERVICO_ITEM](context, dados) {
        const {data} = await api.servicoItem.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.BUSCAR_SERVICO_ITEM_POR_ID](context, id) {
        const {data} = await api.servicoItem.buscarId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.BUSCAR_TODOS_SERVICOS_ITEM_SEM_PAGINACAO]() {
        const {data} = await api.servicoItem.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO_ITEM.EXCLUIR_SERVICO_ITEM](context, id) {
        await api.servicoItem.excluir(id)
    },
}