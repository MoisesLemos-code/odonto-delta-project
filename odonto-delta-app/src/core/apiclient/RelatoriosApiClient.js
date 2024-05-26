import axios from 'axios'

class RelatoriosApiClient {

    async gerarRelatorioFichaPaciente(pacienteId) {
        axios({
            url: `/relatorios/fichaPaciente/${pacienteId}`,
            method: 'GET',
            responseType: 'blob',
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]))
            var fileLink = document.createElement('a')

            fileLink.href = fileURL
            fileLink.setAttribute('download', 'FichaPaciente.pdf')
            document.body.appendChild(fileLink)

            fileLink.click()
        })
    }

    async gerarRelatorioOrcamentos(dados) {
        return axios.post('/relatorios/orcamentos', dados)
    }

}

export default new RelatoriosApiClient()