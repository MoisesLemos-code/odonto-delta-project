import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class OrcamentoApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/orcamento/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosPorPaciente(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/orcamento/page/paciente',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/orcamento/all')
    }

    async cadastrar(dados) {
        return axios.post('/orcamento/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/orcamento/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/orcamento/find/${id}`)
    }

    async buscaSimples(id) {
        return axios.get(`/orcamento/buscaSimples/${id}`)
    }

    async finalizar(id) {
        return axios.put(`/orcamento/finalizar/${id}`)
    }

    async buscarDentesItensPorOrcamento(id){
        return axios.get(`/denteitem/findbyorcamento/${id}`)
    }

    async buscarModalId(id) {
        return axios.get(`/orcamento/find/modal/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/orcamento/delete/${id}`)
    }

}

export default new OrcamentoApiClient()