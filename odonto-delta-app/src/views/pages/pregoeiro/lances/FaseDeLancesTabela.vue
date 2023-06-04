<template>
  <div>
    <div align="right">
      <v-btn
          class="btn-ver-propostas"
          style="font-size: 12px"
          depressed
          tile
          small
          @click="abrirModalProposta"
      >
        Propostas
      </v-btn>
      <v-btn
          class="btn-chat"
          style="font-size: 12px"
          depressed
          tile
          small
          @click="ativarInativarChat"
      >
        Chat
      </v-btn>
    </div>
    <detalhamento-itens-tabela
        v-if="exibirDetalhesItens"
        :exibir-modal="exibirDetalhesItens"
        :titulo-lote="tituloLote"
        :lote-id="loteId"
        :pregao-id="pregaoId"
        @fechar="fecharModal"/>

    <ModalDetalhesLances
        v-if="exibirModalLances"
        :exibir-modal="exibirModalLances"
        :titulo-lote="tituloLote"
        :lances="lances"
        @fechar="fecharModalLances"
        @cancelarLance="tratarEventoCancelarLance"
    />
    <ModalReiniciarEtapaFechada
        v-if="exibirModalReinicioEtapa"
        :exibir-modal="exibirModalReinicioEtapa"
        :esperar-reiniciar-etapa-fechada="esperarReiniciarEtapaFechada"
        @fecharModalReinicio="fecharModalReinicio"
        @reiniciarEtapa="reiniciarEtapaFechadaLote"
      />
    <ModalVisualizarPropostas
        v-if="exibirModalProposta"
        :exibir-modal="exibirModalProposta"
        @fecharModalVisualizarPropostas="fecharModalProposta"
      />
    <v-data-table :headers="colunas" :items="itens"
                  v-model="selecionado"
                  show-select
                  :single-select="false"
                  @input="selecionarItem"
                  @update:items-per-page="buscarDadosGerais"
                  @update:page="buscarDadosGerais"
                  item-key="idLote"
                  class="az-table-list tabela-lotes"
                  no-data-text="Não há lotes nessa fase.">
      <template v-slot:item.sequencia="{ item }">
        <v-tooltip top max-width="500">
          <template v-slot:activator="{ on }">
            <v-icon v-on="on" small class="mr-2" :color="item.chatHabilitado? 'green' : 'red'">chat_bubble</v-icon>
          </template>
          <span>
            <span v-if="item.chatHabilitado">
              Chat ativo
            </span>
            <span v-else>
              Chat inativo
            </span>
          </span>
        </v-tooltip>
        <span class="texto-tabela"> {{ item.sequencia }}</span>
      </template>
      <template v-slot:item.descricao="{ item }">
        <div style="display: flex;">
          <span class="texto-tabela d-inline-block text-truncate max-13">{{ item.lote.descricao }}</span>
          <span class="item-lote-ver-mais"
                @click="abrirModalDetalhes(item.lote.id, item.lote.descricao)">ver mais</span>
        </div>
      </template>
      <template v-slot:item.tempoFase="{ item }">
        <contador-tempo :tempoDecorrido.sync="item.tempoFase" recebeSegundos/>
      </template>
      <template v-slot:item.participante="{ item }">
        <span class="texto-tabela">{{item.etapaFechada ? 'Sigiloso' : item.participante }}</span>
      </template>
      <template v-slot:item.situacao="{ item }">
        <div v-if="item.tempoRestanteNaEtapa > 0">
          <div v-if="item.etapaLote === 'ETAPA_ABERTA'">
            <span class="texto-tabela mt-1">Etapa Aberta: </span>
            <contador-tempo :tempo.sync="item.tempoRestanteNaEtapa"
                            @timerZero="item.tempoRestanteNaEtapa = 0"
                            decrescente recebeSegundos/>
          </div>
          <div v-else-if="item.etapaLote === 'TEMPO_RANDOMICO'">
            <span class="texto-tabela mt-1">Tempo Randômico: </span>
            <contador-tempo :tempoDecorrido.sync="item.tempoRandomico" recebeSegundos/>
          </div>
          <div v-else-if="item.etapaLote === 'ETAPA_FECHADA'">
            <span class="texto-tabela mt-1">Etapa Fechada: </span>
            <contador-tempo :tempo.sync="item.tempoRestanteNaEtapa"
                            @timerZero="item.tempoRestanteNaEtapa = 0"
                            decrescente recebeSegundos/>
          </div>
        </div>
        <div v-else-if="item.tempoRestanteNaEtapa<=0 && !item.aguardandoReinicio">
          <v-tooltip top max-width="500">
            <template v-slot:activator="{ on }">
              <div v-on="on">
                Aguarde...
                <span v-if="item.etapaLote === 'ETAPA_ABERTA'" class="texto-tabela">
                  Randômico será iniciado.
                </span>
                <span v-if="item.etapaLote === 'TEMPO_RANDOMICO'" class="texto-tabela">
                  Etapa fechada será iniciada.
                </span>
                <span v-if="item.etapaLote === 'ETAPA_FECHADA'" class="texto-tabela">
                  Finalizando.
                </span>
              </div>
            </template>
            <span class="texto-tabela">O tempo na etapa foi encerrado, o lote está sendo processado.</span>
          </v-tooltip>
        </div>
        <div v-else-if="item.tempoRestanteNaEtapa<=0 && item.aguardandoReinicio">
          <v-tooltip top max-width="500">
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                    class="btn-reinicio"
                    style="font-size: 12px"
                    depressed
                    tile
                    small
                    @click="reiniciarEtapaFechada(item)"
                >
                  Reiniciar.
                </v-btn>
              </div>
            </template>
            <span class="texto-tabela">Reiniciar etapa fechada.</span>
          </v-tooltip>
        </div>
      </template>
      <template v-slot:item.melhorLance="{ item } ">
        <div v-if="item.dadoSigiloso">
          <span class="texto-tabela">Sigiloso</span>
        </div>
        <div style="display: flex; justify-content: space-between" v-else>
            <span class="texto-tabela">{{ item.etapaFechada ? 'Sigiloso' : item.melhorLance | valorParaReal(casasDecimais) }}
        </span>
          <div v-if="!item.dadoSigiloso">
            <div v-if="item.solicitacaoCancelamento">
              <v-tooltip top max-width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-icon v-on="on" @click="abrirModalLances(item, item.lote.id)"
                          size="12px"
                          class="mr-1"
                          color="#f54748">fas fa-exclamation-circle</v-icon>
                </template>
                <span>Cancelamento Solicitado</span>
              </v-tooltip>
            </div>
            <v-icon v-else @click="abrirModalLances(item, item.lote.id)" size="12px">
              fas fa-plus-circle</v-icon>
          </div>
        </div>
      </template>
      <template v-slot:item.economia="{ item }">
        <div v-if="item.dadoSigiloso">
          <span class="texto-tabela">Sigiloso</span>
        </div>
        <span v-else :class="estilizarEconomia(item.economia)">{{ item.economia }} </span>
        <v-tooltip top v-if="!item.dadoSigiloso">
          <template v-slot:activator="{ on, attrs }">
            <v-icon
                v-bind="attrs"
                v-on="on"
                size="10"
                style="align:right; vertical-align: middle">
              percent
            </v-icon>
          </template>
          <span>Val. Ref.: {{ item.valorReferencia | azCurrency(2) }}</span>
        </v-tooltip>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import DetalhamentoItensTabela from '../../commons/DetalhamentoItensTabela'
import ModalDetalhesLances from './ModalDetalhesLances'
import ModalReiniciarEtapaFechada from './ModalReiniciarEtapaFechada'
import ModalVisualizarPropostas from '@/views/pages/pregoeiro/lances/ModalVisualizarPropostas'
import {actionTypes} from '@/core/constants'
import moment from 'moment'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
    name: 'FaseDeLancesTabela',
    components: {ModalVisualizarPropostas, DetalhamentoItensTabela, ModalDetalhesLances, ModalReiniciarEtapaFechada},
    props: {
        itens: {
            required: true,
            type: Array,
            default: () => {
                return []
            }
        },
        dataAbertura: {
            type: String
        },
    },
    data() {
        return {
            selecionado: [],
            lances: null,
            exibirDetalhesItens: false,
            exibirModalLances: false,
            exibirModalReinicioEtapa:false,
            exibirModalProposta: false,
            tituloLote: null,
            loteReinicio: null,
            pregaoId: null,
            loteId: null,
            socket: null,
            stompClient: null,
            esperarReiniciarEtapaFechada: false
        }
    },
    computed: {
        colunas() {
            return this.criarColunas(7,
                ['Sequência', 'Descrição', 'Participante', 'Melhor Lance', 'Economia', 'Tempo Fase', 'Situação'],
                ['sequencia', 'descricao', 'participante', 'melhorLance', 'economia', 'tempoFase', 'situacao'],
                [false, false, false, false, false, false, false],
                ['left', 'left', 'left', 'left', 'left', 'left'],
                ['5%', '25%', '10%', '25%', '10%', '15%', '10%'],
                'gray--text')
        },
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    mounted() {
        this.setarPregaoId()
        this.conectar()
    },
    methods: {
        setarPregaoId() {
            if (this.$route.params.idPregao) {
                this.pregaoId = this.$route.params.idPregao
            }
        },
        selecionarItem() {
            this.$emit('itemSelecionado', this.selecionado)
        },
        async abrirModalDetalhes(loteId, titulo) {
            this.loteId = loteId
            this.tituloLote = titulo
            this.exibirDetalhesItens = true
        },
        abrirModalProposta(){
            this.exibirModalProposta = true
        },
        fecharModal() {
            this.exibirDetalhesItens = false
        },
        reiniciarEtapaFechada(lote) {
            this.loteReinicio = lote
            this.exibirModalReinicioEtapa = true
        },
        async abrirModalLances(item, loteId) {
            this.loteId = loteId
            await this.buscarLances()
            this.tituloLote = item.lote.descricao
            this.exibirModalLances = true
        },
        async buscarLances() {
            if(this.loteId){
                const resposta = await this.$store.dispatch(actionTypes.PREGOEIRO.BUSCAR_LANCES_LOTE, {
                    loteId: this.loteId,
                    pregaoId: this.pregaoId
                })
                if(resposta){
                    this.lances = resposta.items
                    if (this.stompClient && this.stompClient.connected) {
                        this.stompClient.send('/topic/verificarFornecedoresAtivos', 'fornecedoresAtivos', {})
                    }
                }
            }
        },
        fecharModalLances() {
            this.exibirModalLances = false
            this.lances = []
            this.loteId = null
            this.$emit('atualizarSolicitacoesProposta')
        },
        fecharModalReinicio() {
            this.loteReinicio = null
            this.exibirModalReinicioEtapa = false
        },
        fecharModalProposta(){
            this.exibirModalProposta = false
        },
        async reiniciarEtapaFechadaLote(justificativa) {

            this.esperarReiniciarEtapaFechada = true
            const dados = {loteId: this.loteReinicio.lote.id,
                pregaoId: this.pregaoId,
                justificativa:justificativa,
                usuarioId: this.$store.state.loki.user.id
            }
            await this.$store.dispatch(actionTypes.PREGOEIRO.REINICIAR_ETAPA_FECHADA, dados)
            this.esperarReiniciarEtapaFechada = false
            this.exibirModalReinicioEtapa = false
        },
        estilizarEconomia(economia) {
            if (parseFloat(economia) > 0) {
                return 'economia-positiva'
            } else {
                return 'economia-negativa'
            }
        },
        format_date(value) {
            if (value) {
                return moment(String(value)).format('hh:mm:ss')
            }
        },
        async tratarEventoCancelarLance(lance) {
            await this.$store.dispatch(actionTypes.PREGOEIRO.CANCELAR_LANCE,
                {loteId: this.loteId, pregaoId: this.pregaoId, lanceId: lance.idLance, usuarioId: this.$store.state.loki.user.id})
            this.mostrarNotificacaoSucessoDefault()
            await this.buscarLances()
            this.buscarDadosGerais()
        },
        buscarDadosGerais() {
            this.$emit('atualizarDadosGerais')
        },
        conectar() {
            this.socket = new SockJS(this.getSocketURL())
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.debug = () => {}
            this.stompClient.connect(
                {},
                () => {
                    this.stompClient.subscribe(`/topic/ouvirSolicitacaoCancelamento=${this.pregaoId}`, async () => {
                        await this.buscarLances()
                    })
                    this.stompClient.subscribe(`/topic/atualizarInformacoes=${this.pregaoId}`, async () => {
                        await this.buscarLances()
                    })
                    this.stompClient.subscribe('/topic/conexaofornecedor', tick => {
                        let response = JSON.parse(tick.body)
                        this.setarFornecedorConectado(response)
                    })
                    this.stompClient.subscribe('/topic/desconexaofornecedor', tick => {
                        let response = JSON.parse(tick.body)
                        this.setarFornecedorDesconectado(response)
                    })
                    this.stompClient.subscribe(`/topic/statusChat=${this.pregaoId}`, tick => {
                        console.log(tick)
                    })
                },
                error => {
                    console.log('--------- ERROR')
                    console.log(error)
                }
            )
        },
        setarFornecedorConectado(resposta) {
            if(this.lances === undefined || this.lances === null) {
                return
            }
            const indices = this.buscarTodosOsIndices(this.lances, resposta.fornecedorId)
            for(let indice in indices) {
                const lanceModificar = indices[indice]
                this.lances[lanceModificar].ativo = true
                this.lances[lanceModificar].sessaoId = resposta.sessaoId
            }
        },
        setarFornecedorDesconectado(resposta) {
            if(this.lances === undefined || this.lances === null) {
                return
            }
            const indices = this.buscarTodosOsIndicesDesconectar(this.lances, resposta.sessaoId)
            for(let indice in indices) {
                const lanceModificar = indices[indice]
                this.lances[lanceModificar].ativo = false
                this.lances[lanceModificar].sessaoId = null
            }
        },
        buscarTodosOsIndices(arr, val) {
            const indexes = arr
                .map((lance, i) => lance.fornecedor.id == val ? i : null)
                .filter(i => i !== null)
            return indexes
        },
        buscarTodosOsIndicesDesconectar(arr, val) {
            const indexes = arr
                .map((lance, i) => lance.sessaoId == val ? i : null)
                .filter(i => i !== null)
            return indexes
        },
        async ativarInativarChat() {
            if(this.selecionado.length == 0) {
                this.mostrarNotificacaoErro('Selecione um lote.')
            }
            for(var lote of this.selecionado){
                const dados = {loteId: lote.idLote, pregaoId:this.pregaoId, usuarioId: this.$store.state.loki.user.id}
                await this.$store.dispatch(actionTypes.PREGOEIRO.ATIVAR_INATIVAR_CHAT, dados)
            }
        },
        getSocketURL() {
            return window.location.protocol + '//' + window.location.hostname + '/pregao/api/sockjs'
        }
    }
}
</script>

<style lang="stylus">

.economia-positiva
  color green

.economia-negativa
  color red

.tabela-lotes

  .max-13
    max-width 13vw

  table
    border 1px solid #AAAAAA
    border-right none
    border-radius 6px

  tr:last-child
    border-bottom 0 !important

  thead tr th
    font-size 14px !important
    color #777777;
    font-weight bold

  thead th
    background-color: #eee;

  thead th:first-child
    border-radius: 6px 0 0 0;

  thead th:last-child
    border-radius: 0 6px 0 0;

  .v-datatable__expand-col
    border-bottom 1px solid #ddd
    padding-top 0 !important

  thead
    th
      border-right 1px solid #AAAAAA
      border-bottom none !important

  tbody
    td
      border-right 1px solid #AAAAAA
      border-top 1px solid #AAAAAA

.tabela-lotes.ranking-normal
  .v-table tbody tr:hover:not(.v-datatable__expand-row)
    background-color #f3f3f3 !important

.btn-reinicio
  background-color #3a6861 !important
  color white !important
  text-transform none
  margin-bottom 2px
  margin-top 2px
  border-radius: 2px;

.btn-chat
  background-color #3a6861 !important
  color white !important
  text-transform none
  margin 2px 2px 5px 2px
  border-radius: 2px;

.btn-ver-propostas
  background-color white !important
  text-transform none
  margin 2px 2px 5px 2px
  border: 1px solid #ccc;
  border-radius: 2px;

.item-lote-ver-mais
  color #3a6861
  font-size 14px
  text-decoration underline
  font-family "Roboto", sans-serif
  margin-left 5px

.item-lote-ver-mais:hover
  cursor pointer

.texto-tabela
  font-size 14px
  font-family "Roboto", sans-serif

@media (max-width 720px)
  .tabela-lotes
    padding 0

    td:nth-of-type(1):before
      content "Sequência:"

    td:nth-of-type(2):before
      content "Descrição:"

    td:nth-of-type(3):before
      content "Participante:"

    td:nth-of-type(4):before
      content "Melhor Lance:"

    td:nth-of-type(5):before
      content "Economia:"

    td:nth-of-type(6):before
      content "Tempo Fase:"

    td:nth-of-type(7):before
      content "Situação:"

    td:nth-of-type(1)
      text-align left

    td:nth-of-type(2)
      text-align left

    td:nth-of-type(3)
      text-align left

    td:nth-of-type(4)
      text-align left

    td:nth-of-type(5)
      text-align left

</style>
