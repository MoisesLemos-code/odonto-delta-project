import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class PecaItemApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/pecaitem/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosPorDenteItem(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/pecaitem/pageByDenteItem',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/pecaitem/all')
    }

    async cadastrar(dados) {
        return axios.post('/pecaitem/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/pecaitem/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/pecaitem/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/pecaitem/delete/${id}`)
    }

}

export default new PecaItemApiClient()