import PregoeiroApiClient from './PregoeiroApiClient'

let url, mockRetornoApi, verboHttp

jest.mock('axios', ()=>({
    get(_url){
        return new Promise(resolve =>{
            verboHttp = 'get'
            url = _url
            resolve({data:mockRetornoApi})
        })
    },
    put(_url){
        return new Promise(resolve =>{
            verboHttp = 'put'
            url = _url
            resolve({data:mockRetornoApi})
        })
    },
    post(_url){
        return new Promise(resolve =>{
            verboHttp = 'post'
            url = _url
            resolve({data:mockRetornoApi})
        })
    }
}))

describe('PregoeiroApiClient',()=>{
    beforeEach(()=>{
        verboHttp = ''
        url = ''
        mockRetornoApi = true
    })

    it('buscarDadosGerais',async()=>{
        const dados = 120
        const {data} = await PregoeiroApiClient.buscarDadosGerais(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/120/execucao?pregaoId=120')
    })

    it('buscarLancesDoLote',async()=>{
        const dados = {idLote: 520, idPregao: 120}
        const {data} = await PregoeiroApiClient.buscarLancesDoLote(dados.idLote, dados.idPregao)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/lances/buscarLancesPorLotePregaoDetalhe?idLote=520&idPregao=120')
    })

    it('cancelarLance',async()=>{
        const dados = {idLote: 520, idPregao: 120, idLance: 32, usuarioId: 1}
        await PregoeiroApiClient.cancelarLance(dados.idLote, dados.idPregao, dados.idLance, dados.usuarioId)

        expect(verboHttp).toEqual('put')
        expect(url).toEqual('/pregao/api/lances/32/cancelar?lanceId=32&loteId=520&pregaoId=120&usuarioId=1')
    })

    it('buscarProspostasCancelamentoSolicitado',async()=>{
        const dados = {idPregao: 120}
        const {data} = await PregoeiroApiClient.buscarProspostasCancelamentoSolicitado(dados.idPregao)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/propostasPregao/buscarPropostasCancelamentoSolicitado?idPregao=120')
    })

    it('reiniciarEtapaFechada',async()=>{
        const dados = {loteId: 520, idPregao: 120, idFornecedor: 2}
        const {data} = await PregoeiroApiClient.reiniciarEtapaFechada(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('post')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/520/lotes/reiniciarEtapaFechada')
    })

    it('ativarInativarChat',async()=>{
        const dados = {loteId: 520, pregaoId: 120, idFornecedor: 2, usuarioId: 1}
        const {data} = await PregoeiroApiClient.ativarInativarChat(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/520/lotes/ativarinativarchat?loteId=520&pregaoId=120&usuarioId=1')
    })

    it('buscarLotesPropostas',async()=>{
        const dados = {loteId: 520, pregaoId: 120, idFornecedor: 2}
        const {data} = await PregoeiroApiClient.buscarLotesPropostas(dados.pregaoId)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/120/lotes/buscarLotesPorPregao?idPregao=120')
    })

    it('buscarPropostasDetalhePorLote',async()=>{
        const dados = {loteId: 520, pregaoId: 120, idFornecedor: 2}
        const {data} = await PregoeiroApiClient.buscarPropostasDetalhePorLote(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/propostasPregao/buscarPropostasDetalhe?idLote=520&idPregao=120')
    })

    it('buscarItensPropostaPregao',async()=>{
        const dados = {loteId: 520, idPregao: 120, idProposta: 2}
        const {data} = await PregoeiroApiClient.buscarItensPropostaPregao(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/itemPropostaPregao/buscarItensPropostaPregao?idProposta=2&idPregao=120')
    })

})
