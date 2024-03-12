import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'


class perfilApiClient {

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.build(
            '/perfil',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarId(id){
        return await axios.get(`/perfil/${id}`)
    }

    async cadastrar(dados){
        return await axios.post('/perfil/inserir', dados)
    }

    async editar(dados) {
        return await axios.put(`/perfil/${dados.id}`, dados)
    }

    async remover(id){
        return await axios.delete(`/perfil/${id}`)
    }
}

export default new perfilApiClient()
