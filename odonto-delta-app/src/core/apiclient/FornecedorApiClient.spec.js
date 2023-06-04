import FornecedorApiClient from './FornecedorApiClient'

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

describe('FornecedorApiClient',()=>{
    beforeEach(()=>{
        verboHttp = ''
        url = ''
        mockRetornoApi = true
    })

    it('buscarLotesPorFornecedor',async()=>{
        const dados = {idFornecedor: 1, idPregao: 120}
        const {data} = await FornecedorApiClient.buscarLotesPorFornecedor(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/120/lotes/buscarLotesComPropostaPorFornecedor?idFornecedor=1&idPregao=120')
    })

    it('buscarLancesFornecedor',async()=>{
        const dados = {idLote: 520, idPregao: 120}
        const {data} = await FornecedorApiClient.buscarLancesFornecedor(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/lances/buscarLancesPorLotePregao?idLote=520&idPregao=120')
    })

    it('buscarItensLoteFornecedor',async()=>{
        const dados = {idLote: 520, idPregao: 120}
        const {data} = await FornecedorApiClient.buscarItensLoteFornecedor(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/licitante/itemLotePregao/buscarItensDeLotePregao?idLote=520&idPregao=120')
    })

    it('efetuarLance',async()=>{
        const dados = {idLote: 520, idPregao: 120}
        const {data} = await FornecedorApiClient.efetuarLance(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('post')
        expect(url).toEqual('/pregao/api/lances/efetuarLancePregao')
    })

    it('solicitarCancelamentoLance',async()=>{
        const dados = {idLote: 520, idPregao: 120}
        const {data} = await FornecedorApiClient.solicitarCancelamentoLance(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('put')
        expect(url).toEqual('/pregao/api/lances/solicitarCancelamentoLance')
    })

    it('buscarLoteUnicoFornecedor',async()=>{
        const dados = {idLote: 520, idPregao: 120, idFornecedor: 2}
        const {data} = await FornecedorApiClient.buscarLoteUnicoFornecedor(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/120/lotes/buscarLoteUnicoFornecedor?idFornecedor=2&idLote=520&idPregao=120')
    })

})
