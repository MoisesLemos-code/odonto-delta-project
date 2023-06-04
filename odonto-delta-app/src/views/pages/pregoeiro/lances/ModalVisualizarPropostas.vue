<template>
  <div>
    <modal-detalhes-proposta
        v-if="exibirModalDetalhesProposta"
        :exibir-modal="exibirModalDetalhesProposta"
        :participante="participanteSelecionado"
        @fecharModalDetalhesProposta="fecharModalDetalhesProposta"
      />
  <v-dialog v-model="exibirModal" max-width="920" persistent scrollable>
    <v-card id="modal-visualizar-propostas">
      <v-toolbar dark color="#3a6861" elevation="0">
        <v-toolbar-title><h4>Visualização de Propostas</h4></v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text class="modal-propostas-text">
        <div class="container-table-propostas">
          <v-expansion-panels flat >
            <v-expansion-panel :key="lote.id" v-for="lote in lotes" class="expansion-container" @click="buscarPropostasDetalhe(lote.id)">
              <v-expansion-panel-header class="expansion-header">
                <span class="d-inline-block text-truncate max-w">{{ lote.resumoDescricao }}</span>
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-simple-table class="table-participante-proposta">
                  <template v-slot:default>
                    <thead class="table-participante-proposta-header">
                    <tr>
                      <th class="text-left" style="width: 70%">
                        Participante
                      </th>
                      <th class="text-left">
                        Valor
                      </th>
                      <th class="text-right">
                        Ações
                      </th>
                    </tr>
                    </thead>
                    <tbody style="color: #666">
                    <tr v-for="item in participantes" :key="item.idFornecedor">
                      <td style="width: 70%">{{ item.apelido }}<span v-if="item.microEmpresa" class="mei-me-epp">MEI/ME/EPP</span></td>
                      <td>{{ item.valorLanceFinal | valorParaReal(casasDecimais) }}</td>
                      <td class="text-right">
                        <span class="participante-proposta-detalhes"
                              @click="abrirModalDetalhesPropostas(item)">Ver detalhes</span>
                      </td>
                    </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </div>
      </v-card-text>
    </v-card>
  </v-dialog>
  </div>
</template>

<script>
import ModalDetalhesProposta from '@/views/pages/pregoeiro/lances/ModalDetalhesProposta'
import {actionTypes} from '@/core/constants'
import {mapActions} from 'vuex'

export default {
    name: 'ModalVisualizarPropostas',
    components: {ModalDetalhesProposta},
    props: {
        exibirModal:{
            type: Boolean,
            value: false
        },
    },
    data(){
        return{
            exibirModalDetalhesProposta: false,
            participanteSelecionado: {},
            lotes: [],
            participantes: [],
            idPregao: null,
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    async mounted() {
        this.ajustarBarraLateral('modal-visualizar-propostas')
        this.setPregaoId()
        await this.buscarLotes()
    },
    methods: {
        ...mapActions([
            actionTypes.PREGOEIRO.BUSCAR_LOTES_PROPOSTAS,
            actionTypes.PREGOEIRO.BUSCAR_PROPOSTAS_DETALHE_POR_LOTE
        ]),
        setPregaoId() {
            if (this.$route.params.idPregao) {
                this.idPregao = this.$route.params.idPregao
            }
        },
        async buscarLotes(){
            const resultado = await this.buscarLotesPropostas(this.idPregao)
            if(resultado){
                this.lotes = resultado.items
            }
        },
        async buscarPropostasDetalhe(loteId){
            const resultado = await this.buscarPropostasDetalhePorLote({loteId: loteId, pregaoId: this.idPregao})
            if(resultado){
                this.participantes = resultado.items
            }
        },
        fecharModal() {
            this.$emit('fecharModalVisualizarPropostas')
        },
        abrirModalDetalhesPropostas(item){
            this.participanteSelecionado = item
            this.exibirModalDetalhesProposta = true
        },
        fecharModalDetalhesProposta(){
            this.exibirModalDetalhesProposta = false
        }
    }
}
</script>

<style scoped lang="stylus">
.max-w
  max-width 90%

.modal-propostas-text
  padding 0px !important
  overflow-x hidden
  min-height 300px
  height auto

.container-table-propostas
  margin 20px

.expansion-container
  border 1px solid #DDD
  margin-bottom 10px

.expansion-header
  background-color #EEEEEE
  border-bottom 1px solid #DDD

.table-participante-proposta
  margin-top 10px
  border 1px solid #ccc

.table-participante-proposta-header
  background-color #eee
  tr th
    font-size 14px !important

.participante-proposta-detalhes
  color #3a6861
  font-size 14px
  font-family "Roboto", sans-serif
  margin-left 5px

.participante-proposta-detalhes:hover
    cursor pointer
    color #284547

.mei-me-epp
  color #3a6861
  border 1px solid #3a6861
  border-radius 2px
  font-weight bold
  padding 0px 10px
  margin-left 10px

</style>
