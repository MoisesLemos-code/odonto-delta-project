import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.RELATORIOS.GERAR_RELATORIO_PACIENTES](context, dados) {
        const {data} = await api.relatorios.gerarRelatorioPacientes(dados)
        return data
    },

    async [actionTypes.RELATORIOS.GERAR_RELATORIO_ORCAMENTOS](context, dados) {
        const {data} = await api.relatorios.gerarRelatorioOrcamentos(dados)
        return data
    },


}