<template>
  <div style="background-color: #EEEEEE; height: 100vh;">
    <div class="container-background">
      <div class="container-fase">
      <fase-de-lances-tabela
          :itens="pregao.lotes"
          :data-abertura="pregao.dataAbertura"
          @itemSelecionado="tratarSelecaoLote"
          @atualizarDadosGerais="buscarDadosGerais"
          @atualizarSolicitacoesProposta="buscarPropostasComCancelamentoSolicitado"
      />
      </div>
    </div>
    <div class="chat-legado-container-fase">
      <ChatLegado :lotes="pregao.lotes"
                  :lotesSelecionados="loteSelecionado"
                  :eh-pregoeiro="true"/>
    </div>
  </div>
</template>

<script>
import FaseDeLancesTabela from './FaseDeLancesTabela'
import ChatLegado from '@/views/components/ChatLegado'
import {actionTypes} from '@/core/constants'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
    name: 'FaseDeLancesPregoeiro',
    components: {ChatLegado, FaseDeLancesTabela},
    data() {
        return {
            pregaoId: null,
            pregao: {
                lotes: [],
                dataAbertura: null
            },
            propostasParaCancelar: [],
            socket: null,
            stompClient: null,
            loteSelecionado:[]
        }
    },
    async mounted() {
        this.setPregaoId()
        await this.buscarDadosGerais()
        await this.buscarPropostasComCancelamentoSolicitado()
        this.conectar()
    },
    methods: {
        async buscarDadosGerais() {
            this.pregao = await this.$store.dispatch(actionTypes.PREGOEIRO.BUSCAR_DADOS_GERAIS, this.pregaoId)
        },
        tratarSelecaoLote(lotesSelecionados) {
            this.loteSelecionado = lotesSelecionados
        },
        async buscarPropostasComCancelamentoSolicitado(){
            const response = await this.$store.dispatch(actionTypes.PREGOEIRO.BUSCAR_SOLICITACAO_CANCELAMENTO, this.pregaoId)
            if(response){
                this.propostasParaCancelar = response.items
                this.atualizarLotesComSolicitacaoCancelamento()
            }
        },
        atualizarLotesComSolicitacaoCancelamento(){
            this.pregao.lotes = this.pregao.lotes.map(item =>{
                const encontrado = this.propostasParaCancelar.find(prop => item.lote.id === prop.lotePregao.loteId)
                if(encontrado)
                    item.solicitacaoCancelamento = true
                else
                    item.solicitacaoCancelamento = false
                return item
            })
        },
        setPregaoId() {
            if (this.$route.params.idPregao) {
                this.pregaoId = this.$route.params.idPregao
            }
        },
        conectar() {
            this.socket = new SockJS(this.getSocketURL())
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.debug = () => {}
            this.stompClient.connect(
                {},
                () => {
                    this.stompClient.subscribe(`/topic/ouvirSolicitacaoCancelamento=${this.pregaoId}`, tick => {
                        let response = JSON.parse(tick.body)
                        this.propostasParaCancelar = response.items
                        this.atualizarLotesComSolicitacaoCancelamento()
                    })
                    this.stompClient.subscribe(`/topic/atualizarInformacoes=${this.pregaoId}`, async () => {
                        await this.buscarDadosGerais()
                    })
                    this.stompClient.subscribe(`/topic/mudancaEtapa=${this.pregaoId}`, async () => {
                        await this.buscarDadosGerais()
                    })
                    this.stompClient.subscribe(`/topic/aberturaVistas=${this.pregaoId}`, async () => {
                        await this.buscarDadosGerais()
                    })
                    this.stompClient.subscribe(`/topic/mudancaChat=${this.pregaoId}`, async () => {
                        await this.buscarDadosGerais()
                    })
                },
                error => {
                    console.log('--------- ERROR')
                    console.log(error)
                }
            )
        },
        getSocketURL() {
            return window.location.protocol + '//' + window.location.hostname + '/pregao/api/sockjs'
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