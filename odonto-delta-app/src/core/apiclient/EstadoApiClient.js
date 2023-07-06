import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class EstadoApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/estado/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/estado/all')
    }

}

export default new EstadoApiClient()