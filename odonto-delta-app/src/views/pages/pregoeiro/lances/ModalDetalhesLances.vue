<template>
  <div>
    <v-dialog v-model="exibirModal" max-width="800" scrollable @click:outside="$emit('fechar')">
      <v-card id="modal-detalhes-lances">
        <v-toolbar dark color="#3a6861" elevation="0">
          <v-toolbar-title>
            <h4>{{ tituloLote }}</h4>
          </v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="$emit('fechar')" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="modal-detalhes-text">
          <div class="container-table-detalhes">
            <v-simple-table class="table-detalhe-lote">
              <template v-slot:default>
                <thead class="table-detalhe-header">
                <tr>
                  <th class="text-left table-detalhe-coluna">
                    Participante
                  </th>
                  <th class="text-left table-detalhe-coluna">
                    Valor
                  </th>
                  <th class="text-left table-detalhe-coluna">
                    Data/Hora
                  </th>
                  <th class="text-center">
                    Cancelar Lance
                  </th>
                </tr>
                </thead>
                <tbody style="color: #666">
                <tr v-for="item in lances" :key="item.name" :class="{'solicitado-cancelamento-lance': item.solicitouCancelamento}">
                  <td class="table-detalhe-coluna">
                    <v-icon v-if="item.ativo"
                      size="12px"
                      color="green"
                      style="align:right; vertical-align: middle">
                      circle
                    </v-icon>
                    <v-icon v-else
                            size="12px"
                            style="align:right; vertical-align: middle">
                      circle
                    </v-icon>
                    {{ item.credenciamento.apelido }}
                  </td>
                  <td class="table-detalhe-coluna">{{ item.valorLance | valorParaReal(casasDecimais) }}</td>
                  <td class="table-detalhe-coluna">{{ format_date(item.dataLance) }}</td>
                  <td align="center">
                    <v-tooltip top>
                      <template v-slot:activator="{ on }">
                        <v-btn
                            v-on="on"
                            icon
                            small
                            size="12"
                            @click="abrirModalCancelamento(item)">
                          <v-icon small>cancel</v-icon>
                        </v-btn>
                      </template>
                      <span>Cancelar Lance</span>
                    </v-tooltip>
                  </td>
                </tr>
                </tbody>
              </template>
            </v-simple-table>
          </div>
        </v-card-text>
      </v-card>
    </v-dialog>
    <ModalConfirmacaoCancelamentoLance
        v-if="modalCancelamento"
        v-model="modalCancelamento"
        :lance="lanceSelecionado"
        @cancelarLance="cancelarLance"
        @fecharModalCancelamento="fecharModalCancelamento"
    />
  </div>
</template>

<script>
import moment from 'moment'
import ModalConfirmacaoCancelamentoLance from '@/views/pages/pregoeiro/lances/ModalConfirmacaoCancelamentoLance'
import {actionTypes} from '@/core/constants'

export default {
    name: 'ModalDetalhesLances',
    components: {ModalConfirmacaoCancelamentoLance},
    props: {
        exibirModal:{
            type: Boolean,
            value: false
        },
        lances:{
            type: Array
        },
        tituloLote:{
            type: String
        },
        apelido: String
    },
    data() {
        return {
            pregaoId: null,
            licitacao: {},
            modalCancelamento: false,
            lanceSelecionado: {
                participante: null
            }
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    async mounted() {
        this.setPregaoId()
        await this.buscarDadosGerais()
        this.ajustarBarraLateral('modal-detalhes-lances')
    },
    methods: {
        format_date(value) {
            if (value) {
                return moment(String(value)).format('DD/MM/YYYY - HH:mm:ss')
            }
        },
        setPregaoId() {
            if (this.$route.params.idPregao) {
                this.pregaoId = this.$route.params.idPregao
            }
        },
        async buscarDadosGerais() {
            this.licitacao = await this.$store.dispatch(actionTypes.PREGOEIRO.BUSCAR_DADOS_GERAIS, this.pregaoId)
        },
        abrirModalCancelamento(lance) {
            this.lanceSelecionado = lance
            this.lanceSelecionado.participante = lance.credenciamento.apelido
            this.modalCancelamento = true
        },
        fecharModalCancelamento() {
            this.modalCancelamento = false
        },
        cancelarLance(lance) {
            this.$emit('cancelarLance', lance)
            this.modalCancelamento = false
        }
    }
}
</script>

<style scoped lang="stylus">

  .solicitado-cancelamento-lance
    color #f54748

  .auto-save-modal
    color white

  .table-detalhe-header
    background-color #eee
    tr th
      font-size 14px !important

</style>
