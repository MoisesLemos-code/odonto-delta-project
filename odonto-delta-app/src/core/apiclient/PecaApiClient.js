import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class PecaApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/peca/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/peca/all')
    }

    async cadastrar(dados) {
        return axios.post('/peca/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/peca/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/peca/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/peca/delete/${id}`)
    }

}

export default new PecaApiClient()