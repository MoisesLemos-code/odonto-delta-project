import axios from 'axios'
import SearchUrlBuilder from '@/core/utils/SearchUrlBuilder'


class UsuarioApiClient {

    async efetuarLogin(dados) {
        return await axios.post('/login', dados)
    }

    async verificarLogin(){
        return await axios.get('/auth/userData')
    }

    async buscarTodos(filtros, paginacao) {
        const url = SearchUrlBuilder.build(
            '/usuario/page',
            filtros,
            paginacao
        )
        return axios.get(url)
    }

    async buscarId(id){
        return await axios.get(`/usuario/${id}`)
    }

    async cadastrar(dados){
        return await axios.post('/usuario/insert', dados)
    }

    async editar(dados) {
        return await axios.put(`/usuario/${dados.id}`, dados)
    }

    async remover(id){
        return await axios.delete(`/usuario/delete/${id}`)
    }
}

export default new UsuarioApiClient()
