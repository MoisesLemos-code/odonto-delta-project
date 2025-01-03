import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.COBRANCA.BUSCAR_TODAS_COBRANCAS]({state}, pacienteId) {
        const {filtros, paginacao} = state.resultadoBuscaTodasCobrancas
        filtros.pacienteId.value = pacienteId
        const {data} = await api.cobranca.buscarTodasCobrancas(filtros, paginacao)
        return data
    },

    async [actionTypes.COBRANCA.BUSCAR_TODAS_COBRANCAS_INTERNO]({state}, pacienteId) {
        const {filtros, paginacao} = state.resultadoBuscaTodasCobrancasInterno
        filtros.pacienteId.value = pacienteId
        const {data} = await api.cobranca.buscarTodasCobrancas(filtros, paginacao)
        return data
    },

    async [actionTypes.COBRANCA.CADASTRAR_COBRANCA](context, dados) {
        const {data} = await api.cobranca.cadastrar(dados)
        return data
    },

    async [actionTypes.COBRANCA.EDITAR_COBRANCA](context, dados) {
        const {data} = await api.cobranca.editar(dados)
        return data
    },

    async [actionTypes.COBRANCA.BUSCAR_COBRANCA_POR_ID](context, id) {
        const {data} = await api.cobranca.buscarId(id)
        return data
    },

    async [actionTypes.COBRANCA.EXCLUIR_COBRANCA](context, id) {
        await api.cobranca.excluir(id)
    },

    async [actionTypes.COBRANCA.ESTORNAR_COBRANCA](context, id) {
        await api.cobranca.estornar(id)
    },
}