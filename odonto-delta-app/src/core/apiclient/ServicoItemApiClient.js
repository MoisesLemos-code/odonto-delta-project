import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class ServicoItemApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/servicoitem/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosPorDenteItem(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/servicoitem/pageByDenteItem',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/servicoitem/all')
    }

    async cadastrar(dados) {
        return axios.post('/servicoitem/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/servicoitem/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/servicoitem/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/servicoitem/delete/${id}`)
    }

}

export default new ServicoItemApiClient()