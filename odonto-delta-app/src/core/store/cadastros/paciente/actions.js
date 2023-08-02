import api from '@/core/apiclient'
import {actionTypes, mutationTypes} from '@/core/constants'

export default {

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO]({commit}) {
        const {data} = await api.paciente.buscarTodosSemPaginacao()
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES]({state}, empresaId) {
        const {filtros, paginacao} = state.resultadoBuscaTodosPacientes
        paginacao.empresaId = empresaId
        const {data} = await api.paciente.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_ORTODONTIA]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosPacientesOrtodontia
        const {data} = await api.paciente.buscarTodosOrtodontia(filtros, paginacao)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.CADASTRAR_PACIENTE]({commit}, dados) {
        const {data} = await api.paciente.cadastrar(dados)
        commit(mutationTypes.CADASTROS.PACIENTE.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.CADASTROS.PACIENTE.EDITAR_PACIENTE]({commit}, dados) {
        const {data} = await api.paciente.editar(dados)
        commit(mutationTypes.CADASTROS.PACIENTE.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID](context, id) {
        const {data} = await api.paciente.buscarId(id)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.EXCLUIR_PACIENTE](context, id) {
        await api.paciente.excluir(id)
    },
}