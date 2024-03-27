import axios from 'axios'


class perfilUsuarioApiClient {

    async buscarPerfisDoUsuario(){
        return await axios.get('/perfilUsuario/buscarPerfisDoUsuario')
    }
}

export default new perfilUsuarioApiClient()
