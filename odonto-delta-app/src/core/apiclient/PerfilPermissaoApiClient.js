import axios from 'axios'

class PermissaoApiClient {

    async cadastrar(dados){
        return await axios.post('/perfilPermissao/inserir', dados)
    }

    async editar(dados) {
        return await axios.put('/perfilPermissao/atualizar', dados)
    }

    async remover(id){
        return await axios.delete(`/perfilPermissao/${id}`)
    }

    async buscarPorPerfil(perfilId) {
        return await axios.get(`/perfilPermissao/buscarPerfilPermissaoPorPerfil/perfilId=${perfilId}`)
    }
}

export default new PermissaoApiClient()