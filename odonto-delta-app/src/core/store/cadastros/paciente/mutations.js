import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.CADASTROS.PACIENTE.SET_FILTROS_BUSCA_TODOS_PACIENTES](state, filtros) {
        state.resultadoBuscaTodosPacientes.filtros = filtros
    },

    [mutationTypes.CADASTROS.PACIENTE.SET_PAGINACAO_BUSCA_TODOS_PACIENTES](state, paginacao) {
        state.resultadoBuscaTodosPacientes.paginacao = paginacao
    },

    [mutationTypes.CADASTROS.PACIENTE.SET_DADOS_GERAIS](state, dadosGerais) {
        state.dadosGerais = dadosGerais
    },

    [mutationTypes.CADASTROS.PACIENTE.RESETA_PAGE](state) {
        state.resultadoBuscaTodosPacientes.paginacao.page = 1
    },

    [mutationTypes.CADASTROS.PACIENTE.SET_FILTROS_BUSCA_TODOS_PACIENTES_ORTODONTIA](state, filtros) {
        state.resultadoBuscaTodosPacientesOrtodontia.filtros = filtros
    },

    [mutationTypes.CADASTROS.PACIENTE.SET_PAGINACAO_BUSCA_TODOS_PACIENTES_ORTODONTIA](state, paginacao) {
        state.resultadoBuscaTodosPacientesOrtodontia.paginacao = paginacao
    },

    [mutationTypes.CADASTROS.PACIENTE.RESETA_PAGE_ORTODONTIA](state) {
        state.resultadoBuscaTodosPacientesOrtodontia.paginacao.page = 1
    }
}