<template>
    <v-dialog v-model="exibirModal" max-width="990" scrollable @click:outside="fecharModal">
      <v-card id="modal-detalhes-proposta">
        <v-toolbar dark color="#3a6861" elevation="0">
          <v-toolbar-title><h4>Itens da Proposta</h4></v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="fecharModal" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="modal-detalhes-proposta-text">
          <div class="container-detalhes-propostas">
            <div class="container-detalhes-propostas-header">
              <span>Participante: {{participante.apelido}}</span>
              <span>Data Envio: {{ participante.dataEnvio | azDate('DD/MM/YYYY - HH:mm')}}</span>
            </div>
            <v-simple-table class="table-detalhe-proposta">
              <template v-slot:default>
                <thead class="table-detalhes-proposta-header">
                <tr>
                  <th class="text-left">
                    Item
                  </th>
                  <th class="text-left">
                    Qtd
                  </th>
                  <th class="text-left">
                    Unidade
                  </th>
                  <th class="text-left" style="width: 320px">
                    Descrição
                  </th>
                  <th class="text-left">
                    Marca/Modelo
                  </th>
                  <th class="text-left">
                    Valor Unitário
                  </th>
                  <th class="text-left" v-if="!detalhesProposta.pregaoPercentual">
                    Total
                  </th>
                </tr>
                </thead>
                <tbody style="color: #666">
                <tr v-for="item in detalhesProposta.items" :key="item.id">
                  <td>{{ item.numSequencia}}</td>
                  <td>{{ item.quantidade }}</td>
                  <td>{{ item.unidade }}</td>
                  <td>
                    <v-tooltip top max-width="500">
                      <template v-slot:activator="{ on }">
                        <span v-on="on" class="d-inline-block text-truncate max-w" style="width: 320px">
                          {{ item.descricao }}
                        </span>
                      </template>
                      {{ item.descricao }}
                    </v-tooltip>
                  </td>
                  <td>
                    <v-tooltip top max-width="500">
                      <template v-slot:activator="{ on }">
                        <span v-on="on" class="d-inline-block text-truncate max-w" style="width: 90px">
                         {{item.marca | textoSemValor}} / {{item.modelo | textoSemValor}}
                        </span>
                      </template>
                      {{item.marca | textoSemValor}} / {{item.modelo | textoSemValor}}
                    </v-tooltip>
                  </td>
                  <td>{{ item.valorUnitario | valorParaReal(casasDecimais) }}</td>
                  <td v-if="!detalhesProposta.pregaoPercentual">{{ item.valorTotal | valorParaReal(casasDecimais) }}</td>
                </tr>
                </tbody>
              </template>
            </v-simple-table>
            <div class="table-detalhes-proposta-footer">
              <span>Total Unitário: {{detalhesProposta.totalUnitario | valorParaReal(casasDecimais)}}</span>
              <span v-if="!detalhesProposta.pregaoPercentual">Total Global: {{detalhesProposta.totalGlobal | valorParaReal(casasDecimais)}}</span>
            </div>
          </div>
        </v-card-text>
      </v-card>
    </v-dialog>
</template>

<script>

import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'ModalDetalhesProposta',
    props: {
        exibirModal:{
            type: Boolean,
            value: false
        },
        participante: Object
    },
    data(){
        return{
            detalhesProposta:{
                totalUnitario: 0,
                totalGlobal: 0,
                pregaoPercentual: false,
                items:[]
            },
            idPregao: null,
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    async mounted() {
        this.setPregaoId()
        await this.buscarItensProposta()
        this.ajustarBarraLateral('modal-detalhes-proposta')
    },
    methods: {
        ...mapActions([
            actionTypes.PREGOEIRO.BUSCAR_ITENS_PROPOSTA_PREGAO
        ]),
        setPregaoId() {
            if (this.$route.params.idPregao) {
                this.idPregao = this.$route.params.idPregao
            }
        },
        async buscarItensProposta(){
            const resultado = await this.buscarItensPropostaPregao({idProposta: this.participante.idProposta, idPregao: this.idPregao})
            if(resultado){
                this.detalhesProposta.items = resultado.items
                this.detalhesProposta.totalUnitario = resultado.totalUnitario
                this.detalhesProposta.totalGlobal = resultado.totalGlobal
            }
        },
        fecharModal() {
            this.$emit('fecharModalDetalhesProposta')
        },
    }
}
</script>

<style scoped lang="stylus">

.max-w
  max-width 95%

.modal-detalhes-proposta-text
  padding 0px !important
  overflow-x hidden
  min-height 300px
  height auto

.container-detalhes-propostas
  margin 20px 10px

.container-detalhes-propostas-header
  display flex
  flex-direction row
  justify-content space-between
  font-weight bold
  color #5F5F5F

.table-detalhes-proposta-footer
  margin-top 15px
  font-weight bold
  color #5F5F5F
  float right
  display flex
  flex-direction column
  text-align right

.table-detalhe-proposta
  margin-top 10px
  border 1px solid #ccc

.table-detalhes-proposta-header
  background-color #eee
  tr th
    font-size 14px !important

</style>
