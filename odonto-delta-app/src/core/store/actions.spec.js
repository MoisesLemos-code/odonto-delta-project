import actions from './actions'
import {actionTypes, mutationTypes} from '@/core/constants'

let url, body, mockResponseData, returnedResponse

jest.mock('axios', () => ({
    post(_url, _body) {
        return new Promise((resolve) => {
            url = _url
            body = _body
            resolve({ data: mockResponseData })
        })
    },
    put(_url, _body) {
        return new Promise(resolve => {
            url = _url
            body = _body
            resolve({ data: mockResponseData })
        })
    },
    get(_url) {
        return new Promise((resolve) => {
            url = _url
            resolve({ data: mockResponseData })
        })
    }
}))

describe('Actions', () => {
    const commit = jest.fn()
    const context = jest.fn()
    let state

    beforeEach(() => {
        url = ''
        body = undefined
        mockResponseData = true
        state = {
            loki: {
                product: {
                    id: 900,
                    name: 'pregao'
                }
            },
            comum:{
                notificacoes: {
                    items:[],
                    page: 0
                }
            }
        }
    })

    it('BUSCAR_PRODUTO_POR_NOME', async () => {
        const packageJson = { name: 'pregao' }
        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_PRODUTO_POR_NOME]({ commit }, packageJson)
        expect(url).toBe('/hal/public/produtos?produtoNome=pregao')
        expect(commit).toHaveBeenCalledWith(mutationTypes.COMUM.SET_PRODUTO, true)
    })

    it('BUSCAR_USUARIO_LOGADO', async () => {
        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_USUARIO_LOGADO]({ commit, state })
        expect(url).toBe(`/hal/usuario/sessao?produtoId=${state.loki.product.id}`)
        expect(commit).toHaveBeenCalledWith(mutationTypes.COMUM.SET_USUARIO_LOGADO, true)
    })

    it('BUSCAR_PERMISSOES_USUARIO', async () => {
        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_PERMISSOES_USUARIO]({commit})
        expect(url).toBe('/hal/usuario/sessao?produtoId=900')
    })

    // it('BUSCAR_DADOS_GERAIS', async () => {
    //     const pregaoId = 129
    //
    //     returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_DADOS_GERAIS](context, pregaoId)
    //     expect(url).toBe(`/pregao/api/v1/pregoeiro/${pregaoId}/execucao?pregaoId=${pregaoId}`)
    // })

    it('BUSCAR_LANCES_LOTE', async () => {
        const dadosBusca = {loteId: 13, pregaoId: 23}
        returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_LANCES_LOTE]({commit},dadosBusca)
        expect(url).toBe(`/pregao/api/lances/buscarLancesPorLotePregaoDetalhe?idLote=${dadosBusca.loteId}&idPregao=${dadosBusca.pregaoId}`)
    })

    it('BUSCAR_LOTES', async () => {
        const dados = { idPregao: 120, idFornecedor: 1}
        returnedResponse = await actions[actionTypes.FORNECEDOR.BUSCAR_LOTES](context, dados)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/120/lotes/buscarLotesComPropostaPorFornecedor?idFornecedor=${dados.idFornecedor}&idPregao=${dados.idPregao}`)
    })

    it('BUSCAR_LANCES_LOTE', async () => {
        const dados = { idPregao: 120, idLote: 1}
        returnedResponse = await actions[actionTypes.FORNECEDOR.BUSCAR_LANCES_LOTE]({commit}, dados)
        expect(url).toBe(`/pregao/api/lances/buscarLancesPorLotePregao?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    })

    it('BUSCAR_ITENS_LOTE', async () => {
        const dados = { idPregao: 120, idLote: 1}
        returnedResponse = await actions[actionTypes.FORNECEDOR.BUSCAR_ITENS_LOTE](context, dados)
        expect(url).toBe(`/pregao/api/v1/licitante/itemLotePregao/buscarItensDeLotePregao?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    })

    it('SOLICITAR_CANCELAMENTO_LANCE', async () => {
        const dados = { idPregao: 120, idLote: 1}
        returnedResponse = await actions[actionTypes.FORNECEDOR.SOLICITAR_CANCELAMENTO_LANCE](context, dados)
        expect(url).toBe('/pregao/api/lances/solicitarCancelamentoLance')
    })

    // it('CANCELAR_LANCE', async () => {
    //     const dados = {loteId: 1, pregaoId: 120, idLance: 55, usuarioId: 1}
    //     returnedResponse = await actions[actionTypes.PREGOEIRO.CANCELAR_LANCE](context, dados)
    //     expect(url).toBe(`/pregao/api/lances/55/cancelar?lanceId=${dados.idLance}&loteId=${dados.loteId}&pregaoId=${dados.pregaoId}&usuarioId=${dados.usuarioId}`)
    // })

    it('BUSCAR_SOLICITACAO_CANCELAMENTO', async () => {
        const pregaoId = 129
        returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_SOLICITACAO_CANCELAMENTO](context, pregaoId)
        expect(url).toBe(`/pregao/api/propostasPregao/buscarPropostasCancelamentoSolicitado?idPregao=${pregaoId}`)
    })

    it('BUSCAR_LOTE_UNICO', async () => {
        const dados = {idPregao: 129, idFornecedor: 1, idLote: 55}
        returnedResponse = await actions[actionTypes.FORNECEDOR.BUSCAR_LOTE_UNICO](context,dados)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/${dados.idPregao}/lotes/` +
        `buscarLoteUnicoFornecedor?idFornecedor=${dados.idFornecedor}&idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    })

    it('BUSCAR_MENSAGENS_ATA', async () => {
        const dados = {idPregao: 129, idFornecedor: 1, idLote: 55}
        returnedResponse = await actions[actionTypes.CHAT.BUSCAR_MENSAGENS_ATA](context,dados)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/eventoAta?idLote=${dados.idLote}&idPregao=${dados.idPregao}`)
    })

    it('REINICIAR_ETAPA_FECHADA', async () => {
        const dados = {loteId: 55}
        returnedResponse = await actions[actionTypes.PREGOEIRO.REINICIAR_ETAPA_FECHADA](context,dados)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/${dados.loteId}/lotes/reiniciarEtapaFechada`)
    })

    it('ATIVAR_INATIVAR_CHAT', async () => {
        const dados = {pregaoId: 129, loteId: 55, usuarioId: 1}
        returnedResponse = await actions[actionTypes.PREGOEIRO.ATIVAR_INATIVAR_CHAT](context,dados)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/${dados.loteId}/lotes/ativarinativarchat?`+
        `loteId=${dados.loteId}&pregaoId=${dados.pregaoId}&usuarioId=${dados.usuarioId}`)
    })

    it('BUSCAR_LOTES_PROPOSTAS', async () => {
        const pregaoId = 129
        returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_LOTES_PROPOSTAS](context,pregaoId)
        expect(url).toBe(`/pregao/api/v1/pregoeiro/pregao/129/lotes/buscarLotesPorPregao?idPregao=${pregaoId}`)
    })

    it('BUSCAR_PROPOSTAS_DETALHE_POR_LOTE', async () => {
        const dados = {pregaoId: 129, loteId: 55}
        returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_PROPOSTAS_DETALHE_POR_LOTE](context,dados)
        expect(url).toBe(`/pregao/api/propostasPregao/buscarPropostasDetalhe?idLote=${dados.loteId}&idPregao=${dados.pregaoId}`)
    })

    it('BUSCAR_ITENS_PROPOSTA_PREGAO', async () => {
        const dados = {idPregao: 129, idProposta: 22}
        returnedResponse = await actions[actionTypes.PREGOEIRO.BUSCAR_ITENS_PROPOSTA_PREGAO](context,dados)
        expect(url).toBe(`/pregao/api/itemPropostaPregao/buscarItensPropostaPregao?idProposta=${dados.idProposta}&idPregao=${dados.idPregao}`)
    })

})
