import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.SET_FILTROS_BUSCA_TODOS_ORCAMENTOS](state, filtros) {
        state.resultadoBuscaTodosOrcamentos.filtros = filtros
    },

    [mutationTypes.ORCAMENTO.SET_PAGINACAO_BUSCA_TODOS_ORCAMENTOS](state, paginacao) {
        state.resultadoBuscaTodosOrcamentos.paginacao = paginacao
    },

    [mutationTypes.ORCAMENTO.SET_PAGINACAO_BUSCA_ORCAMENTOS_POR_PACIENTE](state, paginacao) {
        state.resultadoBuscaTodosOrcamentosPorPaciente.paginacao = paginacao
    },

    [mutationTypes.ORCAMENTO.RESETA_PAGE](state) {
        state.resultadoBuscaTodosOrcamentos.paginacao.page = 1
    },

    [mutationTypes.ORCAMENTO.RESETA_PAGE_POR_PACIENTE](state) {
        state.resultadoBuscaTodosOrcamentosPorPaciente.paginacao.page = 1
    }
}