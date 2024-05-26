import axios from 'axios'
import SearchUrlBuilder from '../utils/SearchUrlBuilder'

class CobrancaApiClient {

    async buscarTodasCobrancas(filtros, paginacao) {
        const url = SearchUrlBuilder.buildAvanced(
            '/cobranca',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async cadastrar(dados) {
        return axios.post('/cobranca/inserir/', dados)
    }

    async editar(dados) {
        return axios.put(`/cobranca/atualizar/${dados.id}`, dados)
    }

    async buscarId(id) {
        return axios.get(`/cobranca/buscarPorId/${id}`)
    }

    async excluir(id) {
        return axios.delete(`/cobranca/deletar/${id}`)
    }

}

export default new CobrancaApiClient()
