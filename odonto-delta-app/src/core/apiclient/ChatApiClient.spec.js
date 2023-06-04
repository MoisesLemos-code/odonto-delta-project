import ChatApiClient from './ChatApiClient'

let url, mockRetornoApi, verboHttp

jest.mock('axios', ()=>({
    get(_url){
        return new Promise(resolve =>{
            verboHttp = 'get'
            url = _url
            resolve({data:mockRetornoApi})
        })
    }
}))

describe('ChatApiClient',()=>{
    beforeEach(()=>{
        verboHttp = ''
        url = ''
        mockRetornoApi = true
    })

    it('Buscar mensagens da ata para o chat',async()=>{
        const dados = {idLote: 501, idPregao: 120}
        const {data} = await ChatApiClient.buscarMensagensAta(dados)

        expect(data).toBeTruthy()
        expect(verboHttp).toEqual('get')
        expect(url).toEqual('/pregao/api/v1/pregoeiro/pregao/eventoAta?idLote=501&idPregao=120')
    })

})
