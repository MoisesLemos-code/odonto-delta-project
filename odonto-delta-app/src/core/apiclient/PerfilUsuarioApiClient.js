import axios from 'axios'


class perfilUsuarioApiClient {

    async buscarPerfisDoUsuario(){
        return await axios.get('/perfilUsuario/buscarPerfisDoUsuario')
    }

    async atualizarPerfisDoUsuario(dados){
        return await axios.put('/perfilUsuario/atualizar', dados)
    }
}

export default new perfilUsuarioApiClient()
