import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'

class ServicoApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/servico/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarTodosSemPaginacao() {
        return axios.get('/servico/all')
    }

    async cadastrar(dados) {
        return axios.post('/servico/insert/', dados)
    }

    async editar(dados) {
        return axios.put(`/servico/update/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/servico/find/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/servico/delete/${id}`)
    }

}

export default new ServicoApiClient()