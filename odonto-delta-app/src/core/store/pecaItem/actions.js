import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.PECA_ITEM.BUSCAR_TODAS_PECAS_ITEM]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosServicosItem
        const {data} = await api.pecaItem.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.BUSCAR_PECAS_ITEM_POR_DENTE_ITEM]({state}, id) {
        const {filtros, paginacao} = state.resultadoBuscaTodasPecasItem
        filtros.idDenteItem.value = id
        const {data} = await api.pecaItem.buscarTodosPorDenteItem(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.CADASTRAR_PECA_ITEM](context, dados) {
        const {data} = await api.pecaItem.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.EDITAR_PECA_ITEM](context, dados) {
        const {data} = await api.pecaItem.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.BUSCAR_PECA_ITEM_POR_ID](context, id) {
        const {data} = await api.pecaItem.buscarId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.BUSCAR_TODAS_PECAS_ITEM_SEM_PAGINACAO]() {
        const {data} = await api.pecaItem.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.ORCAMENTO.PECA_ITEM.EXCLUIR_PECA_ITEM](context, id) {
        await api.pecaItem.excluir(id)
    },
}