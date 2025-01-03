import axios from 'axios'
import SearchUrlBuilder from '../utils/SearchUrlBuilder'

class PacienteApiClient {

    async buscarTodosSemPaginacao(conteudo) {
        let url = '/paciente/pesquisaPaciente'
        if(conteudo)
            url = `/paciente/pesquisaPaciente?conteudo=${conteudo}`
        return axios.get(url)
    }

    async buscarTodosPacientes(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/paciente',
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
