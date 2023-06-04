import axios from 'axios'

class ChatApiClient {

    async buscarMensagensAta(dados) {
        return await axios.get(`/pregao/api/v1/pregoeiro/pregao/eventoAta?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    }

}

export default new ChatApiClient()
