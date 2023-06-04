import axios from 'axios'

class PregoeiroApiClient {
    async buscarDadosGerais(pregaoId) {
        return await axios.get(`/pregao/api/v1/pregoeiro/${pregaoId}/execucao?pregaoId=${pregaoId}`)
    }

    async buscarLancesDoLote(loteId, pregaoId) {
        return await axios.get(`/pregao/api/lances/buscarLancesPorLotePregaoDetalhe?idLote=${loteId}&idPregao=${pregaoId}`)
    }

    async cancelarLance(loteId, pregaoId, idLance, usuarioId) {
        await axios.put(`/pregao/api/lances/${idLance}/cancelar?lanceId=${idLance}&loteId=${loteId}&pregaoId=${pregaoId}&usuarioId=${usuarioId}`)
    }

    async buscarProspostasCancelamentoSolicitado(pregaoId){
        return await axios.get(`/pregao/api/propostasPregao/buscarPropostasCancelamentoSolicitado?idPregao=${pregaoId}`)
    }

    async reiniciarEtapaFechada(dados) {
        return await axios.post(`/pregao/api/v1/pregoeiro/pregao/${dados.loteId}/lotes/reiniciarEtapaFechada`,dados)
    }

    async ativarInativarChat(dados) {
        // eslint-disable-next-line max-len
        return await axios.get(`/pregao/api/v1/pregoeiro/pregao/${dados.loteId}/lotes/ativarinativarchat?loteId=${dados.loteId}&pregaoId=${dados.pregaoId}&usuarioId=${dados.usuarioId}`)
    }

    async buscarLotesPropostas(pregaoId){
        return await axios.get(`/pregao/api/v1/pregoeiro/pregao/${pregaoId}/lotes/buscarLotesPorPregao?idPregao=${pregaoId}`)
    }

    async buscarPropostasDetalhePorLote(dados){
        return await axios.get(`/pregao/api/propostasPregao/buscarPropostasDetalhe?idLote=${dados.loteId}&idPregao=${dados.pregaoId}`)
    }

    async buscarItensPropostaPregao(dados){
        return await axios.get(`/pregao/api/itemPropostaPregao/buscarItensPropostaPregao?idProposta=${dados.idProposta}&idPregao=${dados.idPregao}`)
    }
}

export default new PregoeiroApiClient()