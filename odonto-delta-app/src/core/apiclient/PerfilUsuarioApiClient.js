import axios from 'axios'


class perfilUsuarioApiClient {

    async buscarPerfisDoUsuario(usuarioId){
        return await axios.get('/perfilUsuario/buscarPerfisDoUsuario/idUsuario=' + usuarioId)
    }

    async atualizarPerfisDoUsuario(dados){
        return await axios.put('/perfilUsuario/atualizar/idUsuario=' + dados.usuarioId, dados)
    }
}

export default new perfilUsuarioApiClient()
