import axios from 'axios'

class PermissaoApiClient {

    async buscarTodasPermissoesPorUsuario(){
        return axios.get('/permissao/buscarTodasPermissoesPorUsuario')
    }
}

export default new PermissaoApiClient()