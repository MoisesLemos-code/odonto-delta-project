import axios from 'axios'

class PermissaoApiClient {

    async buscarTodos(){
        return axios.get('/permissao/buscarTodas')
    }
}

export default new PermissaoApiClient()