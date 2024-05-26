<template>
  <div>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
      <v-card>
        <v-toolbar dark color="primary" elevation="0">
          <v-toolbar-title>Adicionar Cobrança
          </v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="fecharModal" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pt-5">
          <v-row wrap align-center white class="pl-5 pr-5">
            <v-col cols="12" md="6" sm="6" xs="12">
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
            <v-col cols="12" md="6" sm="6" xs="12">
              <v-textarea
                  v-model="dadosGerais.observacao"
                  name="observacao"
                  label="Observação"
                  placeholder="Informe a observação"
                  v-validate="{max: 350}"
                  :error-messages="errors.collect('observacao')"
                  counter
                  filled
                  auto-grow
              />
            </v-col>
          </v-row>
        </v-card-text>
        <v-container>
          <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
            <botao-cancelar @cancelar="fecharModal"/>
            <div style="display: flex; flex-direction: row">
              <botao-salvar @salvar="tratarEventoCadastrar"/>
            </div>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'

export default {
    name: 'PacienteFichaCobrancaModal',
    components: {BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
    },
    data() {
        return {
            dadosGerais: {
                pacienteId: null,
                valorTotal: null,
                valorPago: null,
                status: 'PENDENTE',
                descricao: null,
                observacao: null,
                dataVencimento: null,
            },
            modalExcluir: false,
            permissoes: [],
        }
    },
    mounted() {
        this.setarPacienteId()  
    },
    methods: {
        setarPacienteId(){
            if (this.$route.params.id) {
                this.dadosGerais.pacienteId = this.$route.params.id
            }
        },
        async tratarEventoCadastrar() {
            if (await this.validarDadosFormulario()) {
                this.setMensagemLoading('Cadastrando registro de cobrança...')
                await this.$store.dispatch(actionTypes.PERFIL.EDITAR_PERFIL, this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        fecharModal() {
            this.$emit('fecharModalCadastro')
        },
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

</style>