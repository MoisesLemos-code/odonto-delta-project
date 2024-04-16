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
        return axios.put(`/paciente/atualizar/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/paciente/buscarPorId/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/paciente/deletar/${id}`)
    }

}

export default new PacienteApiClient()
