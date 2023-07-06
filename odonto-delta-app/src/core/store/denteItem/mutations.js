import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.DENTE_ITEM.SET_PAGINACAO_BUSCA_TODOS_DENTE_ITEM](state, paginacao) {
        state.resultadoBuscaTodosDenteItem.paginacao = paginacao
    },

}