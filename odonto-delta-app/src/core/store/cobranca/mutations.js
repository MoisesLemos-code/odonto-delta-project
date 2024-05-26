import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.COBRANCA.SET_FILTROS_BUSCA_TODAS_COBRANCAS](state, filtros) {
        console.log('---SET_FILTROS_BUSCA_TODAS_COBRANCAS')
        console.log(filtros)
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
}