import axios from 'axios'

class AutenticacaoApiClient {
    async autenticarUsuario(token) {
        return await axios.get('/pregao/api/v1/criptografia/decodetoken',{
            params: {
                token: token
            }
        })
    }

}

export default new AutenticacaoApiClient()