import axios from 'axios'
import SearchUrlBuilder from '../utils/SearchUrlBuilder'

class PacienteApiClient {

    async buscarTodosSemPaginacao() {
        return axios.get('/paciente/all')
    }

    async buscarTodosPacientes(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/paciente',
            filtros,
            paginacao
        )
        console.log(url)
        return axios.get(url)
    }

    async buscarTodosOrtodontia(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/paciente/page/ortodontia',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async cadastrar(dados) {
        return axios.post('/paciente/inserir/', dados)
    }

    async editar(dados) {
        return axios.put(`/paciente/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/paciente/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/paciente/delete/${id}`)
    }

}

export default new PacienteApiClient()
