<template>
  <div>
    <modal-solicitar-cancelamento
        v-if="modalSolicitarCancelamento"
        v-model="modalSolicitarCancelamento"
        :item="itemSelecionado"
        :fornecedor-id="fornecedorId"
        :pregao-id="pregaoId"
        @fecharModalCancelamento="fecharModalCancelamento"/>
    <modal-detalhes-lote
        v-if="modalDetalheItem"
        v-model="modalDetalheItem"
        :item="itemSelecionado"
        :pregao-id="pregaoId"
        @fecharModalDetalhes="fecharModalDetalhes"
    />
    <modal-melhor-lance-lote
        v-if="modalMelhorLance"
        v-model="modalMelhorLance"
        :lote="itemSelecionado"
        :fornecedor-id="fornecedorId"
        :pregao-id="pregaoId"
        @fecharModalMelhorLance="fecharModalMelhorLance"
    />
    <v-data-table :headers="colunas" :items="itens"
                  v-model="itemSelecionadoTabela"
                  show-select
                  @input="selecionarItens"
                  @update:items-per-page="buscarLotes"
                  @update:page="buscarLotes"
                  :single-select="false"
                  item-key="idLote"
                  class="az-table-list tabela-lotes-fornecedor"
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
        <span class="texto-tabela"> {{ item.sequencia }}</span><br/>
        <span>{{item.apelido}}</span>
      </template>
      <template v-slot:item.descricao="{ item }">
        <div style="display: flex;">
          <span class="texto-tabela d-inline-block text-truncate max-13">{{ item.descricao }}</span>
          <span class="item-lote-ver-mais"
                @click="abrirModalDetalhes(item)">ver mais</span>
        </div>
      </template>
      <template v-slot:item.melhorLance="{ item }">
        <div v-if="item.dadoSigiloso">
          <span class="texto-tabela">Sigiloso</span>
        </div>
        <div v-else style="display: flex; justify-content: space-between">
          <span class="texto-tabela">{{ item.melhorLance | valorParaReal(casasDecimais) }}</span>
          <v-icon small @click="abrirModalMelhorLance(item)">add_circle</v-icon>
        </div>
      </template>
      <template v-slot:item.ultimoLance="{ item }">
        <div v-if="!item.isHabilitado || !item.isClassificado" style="display: flex; justify-content: space-between; align-items: center">
          <span v-if="!item.isHabilitado" class="texto-tabela">Inabilitado</span>
          <span v-else class="texto-tabela">Desclassificado</span>
        </div>
        <div v-else style="display: flex; justify-content: space-between; align-items: center">
          <div v-if="item.dadoSigiloso || item.aguardandoReinicio">
            <span class="texto-tabela">Sigiloso</span>
          </div>
          <div v-else style="display: flex; align-items: center">
            <span class="texto-tabela">{{ item.ultimoLance | valorParaReal(casasDecimais) }}</span>
            <v-tooltip top max-width="500" v-if="item.situacao !== 'ETAPA_FECHADA'">
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" icon small @click="abrirModalCancelarUltimoLance(item)">
                  <v-icon small>cancel</v-icon>
                </v-btn>
              </template>
              <span>Solicitar Cancelamento</span>
            </v-tooltip>
          </div>
          <div class="mr-2" v-if="item.situacao !== 'ETAPA_FECHADA' && !item.aguardandoReinicio">
            <v-icon small color="green" v-if="item.lanceAprovado">fa-thumbs-up</v-icon>
            <v-icon small color="red" v-else>fa-thumbs-down</v-icon>
          </div>
        </div>
      </template>
      <template v-slot:item.valorLance="{ item }">
        <div v-if="item.situacao === 'ETAPA_FECHADA' && item.lanceSecretoEnviado" style="width: 250px">
          <span class="texto-tabela">Lance Final Enviado</span>
        </div>
        <div v-else-if="item.situacao === 'ETAPA_FECHADA' && item.dadoSigiloso">
          <v-tooltip top max-width="500">
            <template v-slot:activator="{ on }">
              <span v-on="on">
                Disputa Fechada
              </span>
            </template>
            <span class="texto-tabela">De acordo com Decreto Nº 10.024, de 20 de Setembro de 2019, Art.33. § 2º</span>
          </v-tooltip>
        </div>
        <div v-else style="display: flex; align-items: center" class="mt-1">
          <campo-moeda
              v-model="item.valorLance"
              :maxLength="30"
              :event-submit="'blur'"
              class="item-lote-valor-lance"
              placeholder="Informe o valor"
              :precision="casasDecimais"
              @blur="(valor) => item.valorLance = valor"
              :disabled="item.tempoRestanteNaEtapa <= 0 || !item.isHabilitado || !item.isClassificado"
              @keyup.enter="enviarLance(item)"/>
          <v-btn fab color="#3a6861" x-small depressed
                 :loading="carregarEnviarLance == item.idLote"
                 :disabled="item.tempoRestanteNaEtapa <= 0 || !item.isHabilitado || !item.isClassificado"
                 @click="enviarLance(item)">
            <v-icon color="white">send</v-icon>
          </v-btn>
        </div>
      </template>
      <template v-slot:item.situacao="{ item }">
        <div v-if="item.tempoRestanteNaEtapa > 0">
          <div v-if="item.situacao === 'ETAPA_ABERTA'">
            <span class="texto-tabela mt-1">Etapa Aberta: </span>
            <contador-tempo :tempo.sync="item.tempoRestanteNaEtapa"
                            @timerZero="item.tempoRestanteNaEtapa = 0"
                            decrescente recebeSegundos/>
          </div>
          <div v-else-if="item.situacao === 'TEMPO_RANDOMICO'">
            <span class="texto-tabela mt-1">Tempo Randômico: </span>
            <contador-tempo :tempoDecorrido.sync="item.tempoRandomico" recebeSegundos/>
          </div>
          <div v-else-if="item.situacao === 'ETAPA_FECHADA'">
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
                <span v-if="item.situacao === 'ETAPA_ABERTA'" class="texto-tabela">
                  Randômico será iniciado.
                </span>
                <span v-if="item.situacao === 'TEMPO_RANDOMICO'" class="texto-tabela">
                  Etapa fechada será iniciada.
                </span>
                <span v-if="item.situacao === 'ETAPA_FECHADA'" class="texto-tabela">
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
                <span class="texto-tabela">
                  Aguardando Reinício.
                </span>
              </div>
            </template>
            <span class="texto-tabela">Aguardando reinício da etapa fechada.</span>
          </v-tooltip>
        </div>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import ModalSolicitarCancelamento from '@/views/pages/fornecedor/lances/ModalSolicitarCancelamento'
import ModalDetalhesLote from '@/views/pages/fornecedor/lances/ModalDetalhesLote'
import ModalMelhorLanceLote from '@/views/pages/fornecedor/lances/ModalMelhorLanceLote'
import ContadorTempo from '@/views/components/ContadorTempo'
import CampoMoeda from '@/views/components/CampoMoeda.vue'
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'FaseDeLancesTabelaFornecedor',
    components: {
        CampoMoeda,
        ContadorTempo,
        ModalMelhorLanceLote,
        ModalDetalhesLote, ModalSolicitarCancelamento
    },
    props: ['itens', 'fornecedorId', 'pregaoId'],
    computed: {
        colunas() {
            return this.criarColunas(6,
                ['Sequência', 'Descrição', 'Melhor Lance', 'Meu Último Lance', 'Efetuar Lance', 'Situação'],
                ['sequencia', 'descricao', 'melhorLance', 'ultimoLance', 'valorLance', 'situacao'],
                [false, false, false, false, false, false],
                ['left', 'left', 'left', 'left', 'left', 'left'],
                ['10%', '20%', '15%', '20%', '20%', '15%'],
                'gray--text')
        },
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    data() {
        return {
            modalSolicitarCancelamento: false,
            modalDetalheItem: false,
            modalMelhorLance: false,
            carregarEnviarLance: null,
            itemSelecionado: null,
            itemSelecionadoTabela: []
        }
    },
    watch: {
        itens: {
            handler: function (newValue) {
                this.itens = newValue
            },
            deep: true,
            immediate: true
        }
    },
    methods: {
        ...mapActions([
            actionTypes.FORNECEDOR.EFETUAR_LANCE,
        ]),
        buscarLotes(){
            this.$emit('buscarLotesPaginacao')
        },
        async enviarLance(item) {
            if (isNaN(item.valorLance) || item.valorLance === null || item.valorLance < 0 || item.valorLance === '') {
                this.mostrarNotificacaoErro('O lance deve ser um valor numérico válido')
            } else {
                try {
                    this.carregarEnviarLance = item.idLote
                    const dados = {
                        idFornecedor: parseInt(this.fornecedorId),
                        idPregao: parseInt(this.pregaoId),
                        idLote: item.idLote,
                        valorLance: parseFloat(item.valorLance),
                        ignorarLanceCache: false
                    }
                    console.log('NAN', this.pregaoId)
                    const resposta = await this.efetuarLance(dados)
                    item.ultimoLance = resposta.ultimoLanceFornecedor
                    item.lanceSecretoEnviado = resposta.lanceSecretoEnviado
                    this.mostrarNotificacaoSucesso('Lance enviado com sucesso!')
                    this.$emit('zerarValorLance', item)
                    this.carregarEnviarLance = null
                } catch (err) {
                    this.mostrarNotificacaoErro(err.message)
                    this.carregarEnviarLance = null
                }
            }
        },
        async abrirModalMelhorLance(item) {
            this.modalMelhorLance = true
            this.itemSelecionado = item
        },
        fecharModalMelhorLance() {
            this.modalMelhorLance = false
            this.itemSelecionado = null
        },
        selecionarItens() {
            this.$emit('itemSelecionado', this.itemSelecionadoTabela)
        },
        abrirModalCancelarUltimoLance(item) {
            this.modalSolicitarCancelamento = true
            this.itemSelecionado = item
        },
        fecharModalCancelamento() {
            this.modalSolicitarCancelamento = false
            this.itemSelecionado = null
        },
        abrirModalDetalhes(item) {
            this.modalDetalheItem = true
            this.itemSelecionado = item
        },
        fecharModalDetalhes() {
            this.modalDetalheItem = false
            this.itemSelecionado = null
        },
    }
}
</script>

<style lang="stylus">

.tabela-lotes-fornecedor

  .max-13
    max-width 13vw

  .item-lote-valor-lance
    max-width 150px
    margin-right 10px !important
    margin-top -10px !important

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

  .tabela-lotes-fornecedor.ranking-normal
    .v-table tbody tr:hover:not(.v-datatable__expand-row)
      background-color #f3f3f3 !important

  .item-lote-ver-mais
    color #3a6861
    font-size 14px
    font-family "Roboto", sans-serif
    text-decoration underline
    margin-left 5px

  .item-lote-ver-mais:hover
    cursor pointer

  .texto-tabela
    font-size 14px
    font-family "Roboto", sans-serif

@media (max-width 720px)
  .tabela-lotes-fornecedor
    padding 0

    td:nth-of-type(1):before
      content "Sequência:"

    td:nth-of-type(2):before
      content "Descrição:"

    td:nth-of-type(3):before
      content "Melhor Lance:"

    td:nth-of-type(4):before
      content "Meu Último Lance:"

    td:nth-of-type(5):before
      content "Efetuar Lance:"

    td:nth-of-type(6):before
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
