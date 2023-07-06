import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.ORCAMENTO.BUSCAR_TODOS_ORCAMENTOS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosOrcamentos
        const {data} = await api.orcamento.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.BUSCAR_ORCAMENTOS_POR_PACIENTE]({state}, idPaciente) {
        const {filtros, paginacao} = state.resultadoBuscaTodosOrcamentosPorPaciente
        filtros.idPaciente.value = idPaciente
        const {data} = await api.orcamento.buscarTodosPorPaciente(filtros, paginacao)
        return data
    },

    async [actionTypes.ORCAMENTO.CADASTRAR_ORCAMENTO]({getters}, dados) {
        const usuarioLogado = {id: getters.getUsuarioLogado.userId}
        dados = {...dados, usuario: usuarioLogado}
        const {data} = await api.orcamento.cadastrar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.EDITAR_ORCAMENTO]({commit}, dados) {
        dados.usuario = null
        const {data} = await api.orcamento.editar(dados)
        return data
    },

    async [actionTypes.ORCAMENTO.BUSCAR_ORCAMENTO_POR_ID](context, id) {
        const {data} = await api.orcamento.buscarId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.BUSCAR_ORCAMENTO_POR_ID_SIMPLES](context, id) {
        const {data} = await api.orcamento.buscaSimples(id)
        return data
    },

    async [actionTypes.ORCAMENTO.FINALIZAR_ORCAMENTO](context, id) {
        await api.orcamento.finalizar(id)
    },

    async [actionTypes.ORCAMENTO.BUSCAR_DENTES_ITENS_ORCAMENTO](context, id) {
        const {data} = await api.orcamento.buscarDentesItensPorOrcamento(id)
        return data
    },

    async [actionTypes.ORCAMENTO.BURCAR_ORCAMENTO_MODAL_POR_ID](context, id) {
        const {data} = await api.orcamento.buscarModalId(id)
        return data
    },

    async [actionTypes.ORCAMENTO.EXCLUIR_ORCAMENTO](context, id) {
        await api.orcamento.excluir(id)
    },
}