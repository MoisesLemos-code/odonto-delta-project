import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.PECA.SET_FILTROS_BUSCA_TODAS_PECAS](state, filtros) {
        state.resultadoBuscaTodasPecas.filtros = filtros
    },

    [mutationTypes.ORCAMENTO.PECA.SET_PAGINACAO_BUSCA_TODAS_PECAS](state, paginacao) {
        state.resultadoBuscaTodasPecas.paginacao = paginacao
    },

    [mutationTypes.ORCAMENTO.PECA.RESETA_PAGE](state) {
        state.resultadoBuscaTodasPecas.paginacao.page = 1
    }
}