import axios from 'axios'

class FornecedorApiClient {

    async buscarLotesPorFornecedor(dados) {
        return await axios.get(`/pregao/api/v1/pregoeiro/pregao/${dados.idPregao}/lotes`+
        `/buscarLotesComPropostaPorFornecedor?idFornecedor=${dados.idFornecedor}&idPregao=${dados.idPregao}`)
    }

    async buscarLancesFornecedor(dados) {
        return await axios.get(`/pregao/api/lances/buscarLancesPorLotePregao?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    }

    async buscarItensLoteFornecedor(dados) {
        return await axios.get(`/pregao/api/v1/licitante/itemLotePregao/buscarItensDeLotePregao?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    }

    async efetuarLance(dados) {
        return await axios.post('/pregao/api/lances/efetuarLancePregao', dados)
    }

    async solicitarCancelamentoLance(dados) {
        return await axios.put('/pregao/api/lances/solicitarCancelamentoLance', dados)
    }

    async buscarLoteUnicoFornecedor(dados) {
        return await axios.get(`/pregao/api/v1/pregoeiro/pregao/${dados.idPregao}/lotes`+
            `/buscarLoteUnicoFornecedor?idFornecedor=${dados.idFornecedor}&idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    }

}

export default new FornecedorApiClient()
