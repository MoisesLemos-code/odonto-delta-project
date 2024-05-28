import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.PECA.BUSCAR_TODAS_PECAS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodasPecas
        const {data} = await api.peca.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA.CADASTRAR_PECA](context, dados) {
        const {data} = await api.peca.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA.EDITAR_PECA](context, dados) {
        const {data} = await api.peca.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA.BUSCAR_PECA_POR_ID](context, id) {
        const {data} = await api.peca.buscarId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.PECA.BUSCAR_TODAS_PECAS_SEM_PAGINACAO]() {
        const {data} = await api.peca.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.ORCAMENTO.PECA.EXCLUIR_PECA](context, id) {
        await api.peca.excluir(id)
    },
}