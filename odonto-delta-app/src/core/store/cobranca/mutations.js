import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.COBRANCA.SET_FILTROS_BUSCA_TODAS_COBRANCAS](state, filtros) {
        state.resultadoBuscaTodasCobrancas.filtros = filtros
    },

    [mutationTypes.COBRANCA.SET_PAGINACAO_BUSCA_TODAS_COBRANCAS](state, paginacao) {
        state.resultadoBuscaTodasCobrancas.paginacao = paginacao
    },

    [mutationTypes.COBRANCA.RESETA_PAGE](state) {
        state.resultadoBuscaTodasCobrancas.paginacao.page = 1
    },

    [mutationTypes.COBRANCA.SET_ROTA_ORIGEM](state, rotaOrigem) {
        state.rotaOrigem = rotaOrigem
    },

    [mutationTypes.COBRANCA.SET_FILTROS_BUSCA_TODAS_COBRANCAS_INTERNO](state, filtros) {
        state.resultadoBuscaTodasCobrancasInterno.filtros = filtros
    },

    [mutationTypes.COBRANCA.SET_PAGINACAO_BUSCA_TODAS_COBRANCAS_INTERNO](state, paginacao) {
        state.resultadoBuscaTodasCobrancasInterno.paginacao = paginacao
    },

    [mutationTypes.COBRANCA.RESETA_PAGE_INTERNO](state) {
        state.resultadoBuscaTodasCobrancasInterno.paginacao.page = 1
    },


}