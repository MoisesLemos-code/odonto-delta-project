import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.CADASTROS.CIDADE.SET_FILTROS_BUSCA_TODAS_CIDADES](state, filtros) {
        state.resultadoBuscaTodasCidades.filtros = filtros
    },

    [mutationTypes.CADASTROS.CIDADE.SET_PAGINACAO_BUSCA_TODAS_CIDADES](state, paginacao) {
        state.resultadoBuscaTodasCidades.paginacao = paginacao
    },

    [mutationTypes.CADASTROS.CIDADE.SET_DADOS_GERAIS](state, dadosGerais) {
        state.dadosGerais = dadosGerais
    },

    [mutationTypes.CADASTROS.CIDADE.RESETA_PAGE](state) {
        state.resultadoBuscaTodasCidades.paginacao.page = 1
    }
}