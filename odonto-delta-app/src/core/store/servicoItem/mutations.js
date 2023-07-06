import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.ORCAMENTO.SERVICO_ITEM.SET_PAGINACAO_BUSCA_TODOS_SERVICOS_POR_DENTE_ITEM](state, paginacao) {
        state.resultadoBuscaTodosServicosItem.paginacao = paginacao
    },

}