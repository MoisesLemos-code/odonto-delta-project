import axios from 'axios'

class FichaApiClient {

    async editar(dados) {
        return axios.put(`/ficha/update/${dados.id}`, dados)
    }

    async buscarPorPaciente(id){
        return axios.get(`/ficha/paciente/find/${id}`)
    }

}

export default new FichaApiClient()
