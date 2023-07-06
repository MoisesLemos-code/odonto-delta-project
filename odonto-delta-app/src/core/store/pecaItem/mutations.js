import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.PECA_ITEM.SET_PAGINACAO_BUSCA_TODAS_PECAS_POR_DENTE_ITEM](state, paginacao) {
        state.resultadoBuscaTodasPecasItem.paginacao = paginacao
    },

}