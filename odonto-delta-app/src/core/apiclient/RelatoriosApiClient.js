import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class RelatoriosApiClient {

    async gerarRelatorioPacientes(dados) {
        return axios.post('/relatorios/pacientes', dados)
    }

    async gerarRelatorioOrcamentos(dados) {
        return axios.post('/relatorios/orcamentos', dados)
    }

}

export default new RelatoriosApiClient()