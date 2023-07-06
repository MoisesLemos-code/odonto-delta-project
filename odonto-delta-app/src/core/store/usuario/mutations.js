import {mutationTypes} from '@/core/constants'

export default {
    [mutationTypes.USUARIO.SET_FILTROS_BUSCA_TODOS_USUARIOS](state, filtros) {
        state.resultadoBuscaTodosUsuarios.filtros = filtros
    },

    [mutationTypes.USUARIO.SET_PAGINACAO_BUSCA_TODOS_USUARIOS](state, paginacao) {
        state.resultadoBuscaTodosUsuarios.paginacao = paginacao
    },

    [mutationTypes.USUARIO.RESETA_PAGE](state) {
        state.resultadoBuscaTodosUsuarios.paginacao.page = 1
    },
}
