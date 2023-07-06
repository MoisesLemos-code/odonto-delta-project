import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class CidadeApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/cidade/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/cidade/all')
    }

    async cadastrar(dados) {
        return axios.post('/cidade/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/cidade/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/cidade/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/cidade/delete/${id}`)
    }

}

export default new CidadeApiClient()