import axios from 'axios'

class PermissaoApiClient {

    async buscarTodos(id){
        return axios.get(`/permissao/all/user/${id}`)
    }
}

export default new PermissaoApiClient()