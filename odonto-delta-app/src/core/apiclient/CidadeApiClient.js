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

    async buscarTodosSemPaginacao(dados) {
        return axios.get(`/municipio/all/estado=${dados.idEstado}`, dados)
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