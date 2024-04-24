import axios from 'axios'

class FichaApiClient {

    async editarFichaPaciente(dados) {
        return axios.put(`/fichaPaciente/atualizar/${dados.id}`, dados)
    }

    async buscarPorPaciente(id){
        return axios.get(`/fichaPaciente/buscarPorPacienteId/${id}`)
    }

}

export default new FichaApiClient()
