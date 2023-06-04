import api from '@/core/apiclient'
import { actionTypes, mutationTypes, produto } from '@/core/constants'

export default {

    async [actionTypes.COMUM.BUSCAR_PRODUTO_POR_NOME]({ commit }) {
        const { data } = await api.produto.buscarPorNome(produto.NOME)
        commit(mutationTypes.COMUM.SET_PRODUTO, data)
    },

    async [actionTypes.COMUM.BUSCAR_USUARIO_LOGADO]({ commit, state }) {
        const produtoId = state.loki.product.id
        const { data } = await api.usuario.buscarLogado(produtoId)
        commit(mutationTypes.COMUM.SET_USUARIO_LOGADO, data)
    },

    async [actionTypes.COMUM.BUSCAR_PERMISSOES_USUARIO]({ commit }) {
        const { data } = await api.usuario.buscarPermissoesUsuario(900)
        commit(mutationTypes.COMUM.SET_PERMISSOES_USUARIO, data)
    },

    async [actionTypes.COMUM.BUSCAR_LINK_EDITAR_USUARIO]({ state }) {
        const payload = {
            uri: window.location.origin + window.location.pathname,
            produto: state.loki.product.name
        }
        const { data } = await api.usuario.editar(payload)
        return data
    },

    async [actionTypes.BUSCAR_NUMERO_INTEIRO]() {
        const { data } = await api.teste.buscarInteiro()
        return data
    },

    async [actionTypes.PREGOEIRO.BUSCAR_DADOS_GERAIS]({commit}, pregaoId) {
        const {data} = await api.pregoeiro.buscarDadosGerais(pregaoId)
        commit(mutationTypes.LICITACAO.SET_LICITACAO, data)
        return data
    },

    async [actionTypes.PREGOEIRO.BUSCAR_LANCES_LOTE](context, dadosBusca) {
        const {data} = await api.pregoeiro.buscarLancesDoLote(dadosBusca.loteId, dadosBusca.pregaoId)
        return data
    },

    async [actionTypes.FORNECEDOR.BUSCAR_LOTES](context, dados){
        const { data } = await api.fornecedor.buscarLotesPorFornecedor(dados)
        return data
    },

    async [actionTypes.FORNECEDOR.BUSCAR_LANCES_LOTE](context, dados){
        const { data } = await api.fornecedor.buscarLancesFornecedor(dados)
        return data
    },

    async [actionTypes.FORNECEDOR.BUSCAR_ITENS_LOTE](context, dados){
        const { data } = await api.fornecedor.buscarItensLoteFornecedor(dados)
        return data
    },

    async [actionTypes.FORNECEDOR.EFETUAR_LANCE](context, dados){
        const { data } = await api.fornecedor.efetuarLance(dados)
        return data
    },

    async [actionTypes.FORNECEDOR.SOLICITAR_CANCELAMENTO_LANCE](context, dados){
        const { data } = await api.fornecedor.solicitarCancelamentoLance(dados)
        return data
    },

    async [actionTypes.PREGOEIRO.CANCELAR_LANCE](context, {loteId, pregaoId, lanceId, usuarioId}) {
        await api.pregoeiro.cancelarLance(loteId, pregaoId, lanceId, usuarioId)
    },

    async [actionTypes.PREGOEIRO.BUSCAR_SOLICITACAO_CANCELAMENTO](context, pregaoId) {
        const { data } = await api.pregoeiro.buscarProspostasCancelamentoSolicitado(pregaoId)
        return data
    },

    async [actionTypes.FORNECEDOR.BUSCAR_LOTE_UNICO](context, dados){
        const { data } = await api.fornecedor.buscarLoteUnicoFornecedor(dados)
        return data
    },

    async [actionTypes.CHAT.BUSCAR_MENSAGENS_ATA](context, dados){
        const { data } = await api.chat.buscarMensagensAta(dados)
        return data
    },

    async [actionTypes.PREGOEIRO.REINICIAR_ETAPA_FECHADA](context, dados) {
        const { data } = await api.pregoeiro.reiniciarEtapaFechada(dados)
        return data
    },

    async [actionTypes.PREGOEIRO.ATIVAR_INATIVAR_CHAT](context, dados) {
        const { data } = await api.pregoeiro.ativarInativarChat(dados)
        return data
    },

    async [actionTypes.PREGOEIRO.BUSCAR_LOTES_PROPOSTAS](context, pregaoId) {
        const { data } = await api.pregoeiro.buscarLotesPropostas(pregaoId)
        return data
    },

    async [actionTypes.PREGOEIRO.BUSCAR_PROPOSTAS_DETALHE_POR_LOTE](context, dados) {
        const { data } = await api.pregoeiro.buscarPropostasDetalhePorLote(dados)
        return data
    },

    async [actionTypes.PREGOEIRO.BUSCAR_ITENS_PROPOSTA_PREGAO](context, dados) {
        const { data } = await api.pregoeiro.buscarItensPropostaPregao(dados)
        return data
    },
}
