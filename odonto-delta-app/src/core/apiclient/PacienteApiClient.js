import axios from 'axios'
import SearchUrlBuilder from '../utils/SearchUrlBuilder'

class PacienteApiClient {

    async buscarTodosSemPaginacao() {
        return axios.get('/paciente/all')
    }

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/paciente/page',
            filtros,
            paginacao
        )
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
        return axios.post('/paciente/insert/', dados)
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
