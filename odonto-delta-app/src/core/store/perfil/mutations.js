import {mutationTypes} from '@/core/constants'

export default {
    [mutationTypes.PERFIL.SET_FILTROS_BUSCA_TODOS_PERFIS](state, filtros) {
        state.resultadoBuscaTodosPerfis.filtros = filtros
    },

    [mutationTypes.PERFIL.SET_PAGINACAO_BUSCA_TODOS_PERFIS](state, paginacao) {
        state.resultadoBuscaTodosPerfis.paginacao = paginacao
    },

    [mutationTypes.PERFIL.RESETA_PAGE](state) {
        state.resultadoBuscaTodosPerfis.paginacao.page = 1
    },
}
