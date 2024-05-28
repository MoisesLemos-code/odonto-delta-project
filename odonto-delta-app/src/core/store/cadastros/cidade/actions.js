import api from '@/core/apiclient'
import {actionTypes, mutationTypes} from '@/core/constants'

export default {

    async [actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodasCidades
        const {data} = await api.cidade.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES_SEM_PAGINACAO](context, dados) {
        const {data} = await api.cidade.buscarTodosSemPaginacao(dados)
        return data
    },

    async [actionTypes.CADASTROS.CIDADE.CADASTRAR_CIDADE]({commit}, dados) {
        const {data} = await api.cidade.cadastrar(dados)
        commit(mutationTypes.CADASTROS.CIDADE.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.CADASTROS.CIDADE.EDITAR_CIDADE]({commit}, dados) {
        const {data} = await api.cidade.editar(dados)
        commit(mutationTypes.CADASTROS.CIDADE.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.CADASTROS.CIDADE.BUSCAR_CIDADE_POR_ID](context, id) {
        const {data} = await api.cidade.buscarId(id)
        return data
    },

    async [actionTypes.CADASTROS.CIDADE.EXCLUIR_CIDADE](context, id) {
        await api.cidade.excluir(id)
    },
}