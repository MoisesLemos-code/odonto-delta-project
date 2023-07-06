import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.SERVICO.BUSCAR_TODOS_SERVICOS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosServicos
        const {data} = await api.servico.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO.CADASTRAR_SERVICO](context, dados) {
        const {data} = await api.servico.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO.EDITAR_SERVICO](context, dados) {
        const {data} = await api.servico.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO.BUSCAR_SERVICO_POR_ID](context, id) {
        const {data} = await api.servico.buscarId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO.BUSCAR_TODOS_SERVICOS_SEM_PAGINACAO](context) {
        const {data} = await api.servico.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.ORCAMENTO.SERVICO.EXCLUIR_SERVICO](context, id) {
        await api.servico.excluir(id)
    },
}