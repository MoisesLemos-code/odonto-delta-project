import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.SERVICO.SET_FILTROS_BUSCA_TODOS_SERVICOS](state, filtros) {
        state.resultadoBuscaTodosServicos.filtros = filtros
    },

    [mutationTypes.ORCAMENTO.SERVICO.SET_PAGINACAO_BUSCA_TODOS_SERVICOS](state, paginacao) {
        state.resultadoBuscaTodosServicos.paginacao = paginacao
    },

    [mutationTypes.ORCAMENTO.SERVICO.RESETA_PAGE](state) {
        state.resultadoBuscaTodosServicos.paginacao.page = 1
    }

}