import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class DenteItemApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/denteitem/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/denteitem/all')
    }

    async cadastrar(dados) {
        return axios.post('/denteitem/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/denteitem/update/${dados.id}`, dados)
    }

    async buscarId(dados) {
        return axios.get(`/denteitem/find/${dados.id}/${dados.orcamentoId}`)
    }

    async excluir(id) {
        return axios.delete(`/denteitem/delete/${id}`)
    }

}

export default new DenteItemApiClient()