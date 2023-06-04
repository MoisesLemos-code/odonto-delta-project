<template>
  <div style="background-color: #EEEEEE; height: 100vh;">
    <div class="container-background">
      <div class="container-fase">
        <FaseDeLancesTabelaFornecedor
            :itens="lotes"
            :fornecedor-id="idFornecedor"
            :pregao-id="idPregao"
            @zerarValorLance="zerarValorLance"
            @itemSelecionado="tratarEventoSelecaoLote"
            @buscarLotesPaginacao="buscarLotes"
          />
      </div>
    </div>
    <div class="chat-legado-container-fase">
      <ChatLegado :lotes="lotes" :lotesSelecionados="lotesSelecionados"/>
    </div>
  </div>
</template>

<script>
import ChatLegado from '@/views/components/ChatLegado'
import FaseDeLancesTabelaFornecedor from '@/views/pages/fornecedor/lances/FaseDeLancesTabelaFornecedor'
import {actionTypes} from '@/core/constants'
import {mapActions} from 'vuex'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
    name: 'FaseDeLances',
    components: {
        FaseDeLancesTabelaFornecedor, ChatLegado
    },
    data() {
        return {
            lotes: [],
            idPregao: null,
            idFornecedor: null,
            socket: null,
            stompClient: null,
            ultimoLanceEfetuado: null,
            carregarEnviarLance: null,
            lances: null,
            lotesSelecionados:[],
            licitacao: {}
        }
    },
    async mounted() {
        this.setPregaoId()
        this.setFornecedorId()
        await this.buscarDadosGerais()
        await this.buscarLotes()
        this.conectar()
    },
    methods: {
        ...mapActions([
            actionTypes.FORNECEDOR.BUSCAR_LOTES,
            actionTypes.FORNECEDOR.BUSCAR_LOTE_UNICO
        ]),
        setPregaoId() {
            if (this.$route.params.idPregao) {
                this.idPregao = this.$route.params.idPregao
            }
        },
        setFornecedorId() {
            if (this.$route.params.idFornecedor) {
                this.idFornecedor = this.$route.params.idFornecedor
            }
        },
        tratarEventoSelecaoLote(lotesSelecionados) {
            this.lotesSelecionados = lotesSelecionados
        },
        async buscarDadosGerais() {
            this.licitacao = await this.$store.dispatch(actionTypes.PREGOEIRO.BUSCAR_DADOS_GERAIS, this.idPregao)
        },
        async buscarLotes() {
            const resposta = await this.buscarLotesDoFornecedor({idPregao: this.idPregao, idFornecedor: this.idFornecedor})
            if(resposta){
                this.lotes = resposta.items
            }
        },
        zerarValorLance(lote){
            const loteAtualizado = this.lotes.find(item => item.idLote === lote.idLote)
            loteAtualizado.valorLance = null
            const index = this.lotes.findIndex(item => item.idLote === lote.idLote)
            this.lotes[index] = loteAtualizado
        },
        atualizarDadosLote(){
            const loteAtualizado = this.lotes.find(item => item.idLote === this.ultimoLanceEfetuado.idLote)
            loteAtualizado.melhorLance = this.ultimoLanceEfetuado.melhorLance
            if(this.ultimoLanceEfetuado.idFornecedorMelhorLance == this.idFornecedor){
                loteAtualizado.lanceAprovado = true
            }else{
                loteAtualizado.lanceAprovado = false
            }
            if(this.ultimoLanceEfetuado.idFornecedorUltimoLance == this.idFornecedor)
                loteAtualizado.ultimoLance = this.ultimoLanceEfetuado.ultimoLanceFornecedor
            const index = this.lotes.findIndex(item => item.idLote === this.ultimoLanceEfetuado.idLote)
            this.lotes[index] = loteAtualizado
        },
        conectar() {
            var headers = {
                fornecedorId: this.idFornecedor
            }
            this.socket = new SockJS(this.getSocketURL())
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.debug = () => {}
            this.stompClient.connect(headers,
                () => {
                    this.stompClient.subscribe(`/topic/ouvirLanceEfetuadoFornecedor=${this.idPregao}`, tick => {
                        let response = JSON.parse(tick.body)
                        this.ultimoLanceEfetuado = response
                        this.atualizarDadosLote()
                    })
                    this.stompClient.subscribe(`/topic/lancecanceladopregao=${this.idPregao}`, async tick => {
                        let response = JSON.parse(tick.body)
                        await this.setarLoteUnico(response.lotePregao.loteId, this.idPregao)
                        this.verificarLanceCanceladoEhDoFornecedor(response)
                    })
                    this.stompClient.subscribe('/topic/verificarFornecedoresAtivos', () => {
                        var transporturl = this.socket._transport.url
                        var sessaoId = this.buscarSessaoId(transporturl)
                        var conexao = {fornecedorId: this.idFornecedor, sessaoId: sessaoId}
                        this.stompClient.send('/topic/conexaofornecedor', JSON.stringify(conexao), {})
                    })
                    this.stompClient.subscribe(`/topic/mudancaEtapa=${this.idPregao}`, async tick => {
                        let response = JSON.parse(tick.body)
                        await this.setarLoteUnico(response, this.idPregao)
                    })
                    this.stompClient.subscribe(`/topic/reinicio=${this.idPregao}`, async tick => {
                        let response = JSON.parse(tick.body)
                        await this.setarLoteUnico(response, this.idPregao)
                        this.verificarLoteReiniciado(response)
                    })
                    this.stompClient.subscribe(`/topic/mudancaChat=${this.idPregao}`, async tick => {
                        let response = JSON.parse(tick.body)
                        await this.setarLoteUnico(response, this.idPregao)
                        await this.setarLoteUnicoSelecionado(response, this.idPregao)
                    })
                    this.stompClient.subscribe(`/topic/aberturaVistas=${this.idPregao}`, async () => {
                        await this.buscarLotes()
                    })
                },
                error => {
                    console.log('--------- ERROR')
                    console.log(error)
                }
            )
        },
        async setarLoteUnico(loteId, pregaoId){
            const dados = {
                idFornecedor: this.idFornecedor,
                idLote: loteId,
                idPregao: pregaoId,
            }
            const loteUnico = await this.buscarLoteUnico(dados)
            const index = this.lotes.findIndex(item => item.idLote === loteUnico.idLote)
            if(index != -1){
                this.lotes.splice(index, 1, loteUnico)
            } else {
                this.lotes.push(loteUnico)
            }
        },
        async setarLoteUnicoSelecionado(loteId, pregaoId){
            const dados = {
                idFornecedor: this.idFornecedor,
                idLote: loteId,
                idPregao: pregaoId,
            }
            const loteUnico = await this.buscarLoteUnico(dados)
            const index = this.lotesSelecionados.findIndex(item => item.idLote === loteUnico.idLote)
            if(index != -1){
                this.lotesSelecionados[index] = loteUnico
            } else {
                this.lotesSelecionados.push(loteUnico)
            }
        },
        verificarLanceCanceladoEhDoFornecedor(lance) {
            if(this.idFornecedor == lance.fornecedor.id) {
                this.mostrarNotificacaoSucesso('Seu lance no valor de R$ ' + lance.valorLance
                +' foi cancelado com sucesso')
            }
        },
        verificarLoteReiniciado(response) {
            const index = this.lotes.findIndex(item => item.idLote === response)
            this.mostrarNotificacaoSucesso(`Reiniciada etapa fechada para o lote: ${this.lotes[index].sequencia}`)
        },
        getSocketURL() {
            return window.location.protocol + '//' + window.location.hostname + '/pregao/api/sockjs'
        },
        buscarSessaoId(transporturl) {
            var stringurl = transporturl.split('/')
            var sessaoId = stringurl[stringurl.length - 1]
            return sessaoId
        }
    }
}
</script>

<style scoped lang="stylus">

.container-background
  background-color #EEEEEE
  font-family 'Open Sans', Verdana, sans-serif
  line-height 1.5
  margin-top 30px
  display flex
  align-items center
  justify-content center

.container-fase
  margin 0px 20px
  background-color white
  border-radius 5px
  padding 20px
  padding-bottom 5px
  min-width 95%

.chat-legado-container-fase
  margin-top 30px
  display flex
  justify-content center

</style>
