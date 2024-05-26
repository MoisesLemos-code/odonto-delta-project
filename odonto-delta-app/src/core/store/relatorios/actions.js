import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.RELATORIOS.GERAR_RELATORIO_FICHA_PACIENTE](context, dados) {
        await api.relatorios.gerarRelatorioFichaPaciente(dados)
    },

    async [actionTypes.RELATORIOS.GERAR_RELATORIO_ORCAMENTOS](context, dados) {
        const {data} = await api.relatorios.gerarRelatorioOrcamentos(dados)
        return data
    },


}