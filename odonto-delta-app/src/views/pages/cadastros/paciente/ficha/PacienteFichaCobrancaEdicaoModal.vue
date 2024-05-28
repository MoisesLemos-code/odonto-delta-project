<template>
  <div>
    <confirm-modal
        v-model="modalEstorno"
        @modalAcaoCancelar="fecharModalEstorno"
        @modalAcaoConfirmar="estornarCobranca"
        :persistencia="false"
        :maxWidth="500"
        textConfirmar="Confirmar"
        mensagemInicial="Deseja realmente estornar o pagamento no valor de "
        :mensagemMeio="dadosGerais.valorPago | valorParaReal"
        mensagemFinal=" dessa cobrança?"/>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
      <v-card>
        <v-toolbar dark color="primary" elevation="0">
          <v-toolbar-title>
            Cobrança <strong>{{ dadosGerais.codigo }}</strong>
          </v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="fecharModal" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pt-5">
          <v-row wrap align-center white class="pl-5 pr-5" v-if="dadosGerais.orcamentoId">
            <v-col cols="12" md="12" sm="12" xs="12">
              <div class="container-cobranca-orcamento">
                <span class="cobranca-codigo-orcamento">{{dadosGerais.codigoOrcamento}}</span>
                <span class="cobranca-status-orcamento">{{dadosGerais.statusOrcamento | filterEnum(statusOrcamento)}}</span>
              </div>
            </v-col>
          </v-row>
          <v-row wrap align-center white class="pl-5 pr-5">
            <v-col cols="12" md="12" sm="12" xs="12">
              <v-text-field
                  v-model="dadosGerais.descricao"
                  name="descricao"
                  placeholder="Informe a descrição"
                  counter="100"
                  maxlength="100"
                  v-validate="{max: 250}"
                  :error-messages="errors.collect('descricao')">
                <template v-slot:label>
                  Descrição
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="12" md="12" sm="12" xs="12">
              <date-input
                  v-model="dadosGerais.dataVencimento"
                  label="Data vencimento"
                  name-date="dataVencimento"
                  placeholderDate="Informe a data de vencimento">
              </date-input>
            </v-col>
            <v-col cols="12" md="12" sm="12" xs="12">
              <v-textarea
                  v-model="dadosGerais.observacao"
                  name="observacao"
                  label="Observação"
                  placeholder="Informe a observação"
                  v-validate="{max: 400}"
                  :error-messages="errors.collect('observacao')"
                  counter
                  filled
                  auto-grow
              />
            </v-col>
          </v-row>
          <v-row wrap align-center white class="pl-5 pr-5">
            <v-col cols="12" md="6" sm="6" xs="12">
              <div class="container-cobranca-valor-total">
                <span class="titulo-valor-cobranca">Valor da Cobrança</span>
                <span class="text-valor-cobranca">{{dadosGerais.valorTotal | valorParaReal}}</span>
              </div>
              <input-valor
                  v-if="!estornar"
                  v-model="dadosGerais.valorPago"
                  label="Valor pago"
                  placeholder="Informe o valor"
                  :event-submit="'blur'"
                  :prefix="prefixo"
                  :suffix="sufixo"
                  :precision="casasDecimais"
                  @blur="(valor) => dadosGerais.valorPago = valor"
              />
              <div v-if="estornar">
                <v-btn
                    id="botaoEstonar"
                    color="alert"
                    class="botao-estornar-cobranca"
                    depressed
                    @click="abrirModalEstorno">
                  Estornar
                </v-btn>
              </div>
            </v-col>
            <v-col cols="12" md="6" sm="6" xs="12">
             <div class="container-cobranca-status">
               <span :class="['status-cobranca-' + dadosGerais.status]">{{dadosGerais.status | filterEnum(statusCobranca)}}</span>
               <div v-if="dadosGerais.status === 'PARCIALMENTE_PAGO'" class="container-cobranca-parcial">
                 <span>Restante</span>
                 <label>{{dadosGerais.valorRestante | valorParaReal}}</label>
               </div>
             </div>
            </v-col>
          </v-row>
        </v-card-text>
        <v-container>
          <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
            <botao-cancelar @cancelar="fecharModal"/>
            <div style="display: flex; flex-direction: row">
              <botao-salvar @salvar="tratarEventoEdicao"/>
            </div>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>
import moment from 'moment/moment'
import {actionTypes} from '@/core/constants'
import {statusOrcamento, statusCobranca} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'
import DateInput from '@/views/components/DateInput.vue'
import InputValor from '@/views/components/InputValor.vue'
import ConfirmModal from '@/views/components/ConfirmModal.vue'

export default {
    name: 'PacienteFichaCobrancaEdicaoModal',
    components: {ConfirmModal, InputValor, DateInput, BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        idCobranca: Number
    },
    data() {
        return {
            dadosGerais: {
                id: null,
                codigo: '',
                pacienteId: null,
                valorTotal: null,
                valorPago: null,
                status: 'PENDENTE',
                descricao: null,
                observacao: null,
                dataVencimento: null,
                orcamentoId: null,
                codigoOrcamento: null,
                statusOrcamento: null,
                valorRestante: null,
            },
            prefixo: 'R$',
            sufixo: '',
            casasDecimais: 2,
            statusOrcamento,
            statusCobranca,
            estornar: false,
            modalEstorno: false,
        }
    },
    async mounted() {
        await this.buscarCobranca()
    },
    methods: {
        async buscarCobranca(){
            const resultado = await this.$store.dispatch(actionTypes.COBRANCA.BUSCAR_COBRANCA_POR_ID, this.idCobranca)
            if(resultado){
                this.dadosGerais = resultado
                if(this.dadosGerais.status === 'PAGO'){
                    this.estornar = true
                } else {
                    this.estornar = false
                }
                if(this.dadosGerais.status === 'PARCIALMENTE_PAGO'){
                    this.dadosGerais.valorRestante = parseFloat(this.dadosGerais.valorTotal) - parseFloat(this.dadosGerais.valorPago)
                }
            }
        },
        formatarData() {
            if (this.dadosGerais.dataVencimento) {
                this.dadosGerais.dataVencimento = moment(this.dadosGerais.dataVencimento).add(5, 'hours').format('YYYY-MM-DD')
            }
        },
        async tratarEventoEdicao() {
            if (await this.validarDadosFormulario()) {
                if(!this.dadosGerais.dataVencimento){
                    this.mostrarNotificacaoErro('Informe a data de vencimento!')
                    return
                }
                if(this.dadosGerais.valorPago > this.dadosGerais.valorTotal){
                    this.mostrarNotificacaoErro('O valor pago é maior que o valor cobrado!')
                    return
                }
                this.formatarData()
                this.setMensagemLoading('Editando registro de cobrança...')
                await this.$store.dispatch(actionTypes.COBRANCA.EDITAR_COBRANCA, this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
                await this.buscarCobranca()
            }
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        fecharModal() {
            this.$emit('fecharModalEdicao')
        },
        abrirModalEstorno(){
            this.modalEstorno = true
        },
        fecharModalEstorno(){
            this.modalEstorno = false
        },
        async estornarCobranca(){
            await this.$store.dispatch(actionTypes.COBRANCA.ESTORNAR_COBRANCA, this.idCobranca)
            await this.buscarCobranca()
            this.fecharModalEstorno()
        }
    }
}
</script>

<style scoped lang="stylus">

.row-bottom
  border-top-width 1px
  border-top-style solid
  border-top-color #ccc
  box-sizing border-box
  padding-top 10px
  padding-bottom 10px
  display flex
  justify-content space-between

.header-permissao-crud
  width 100%
  padding-top 10px
  margin-top 50px
  margin-bottom 50px
  border-top-width 1px
  border-top-style solid
  border-top-color #ccc

.container-permissao-list
  padding-top 10px

.permissao-item
  background-color #487B9C
  padding 10px
  margin 10px
  border-radius 20px
  display flex
  align-items center
  justify-content space-between
  >>> label
    color #ffff
    font-size 16px
    font-weight bold
  >>> .v-input__control
    margin 0px !important
    height 30px
  >>> .v-input__slot
    margin 0px !important

.container-cobranca-orcamento
  background-color #E0E0E0
  padding 10px 20px
  border-radius 5px
  display flex
  flex-direction row
  justify-content space-between

.cobranca-codigo-orcamento
  font-weight bold

.cobranca-status-orcamento
  color #f44336

.container-cobranca-valor-total
  display flex
  flex-direction column
  margin-bottom 20px

.titulo-valor-cobranca
  font-weight bold

.container-cobranca-status
  font-weight bold
  font-size 24px
  background-color #E0E0E0
  height 100%
  display flex
  flex-direction column
  justify-content center
  align-items center
  border-radius 5px

  div
    margin-top 10px

.container-cobranca-parcial
  display flex
  flex-direction column

  span
    font-size 15px

.botao-estornar-cobranca
  color #fff

</style>