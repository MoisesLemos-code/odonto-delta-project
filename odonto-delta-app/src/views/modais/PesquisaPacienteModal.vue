<template>
  <div>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
      <v-card>
        <v-toolbar dark color="primary" elevation="0">
          <v-toolbar-title>Selecionar paciente
          </v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="fecharModal" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pt-5">
          <v-row wrap align-center white class="pl-5 pr-5">
              <div v-if="!pacienteSelecionado" class="container-selecao container-nao-selecionado" >
                <label>Nenhum paciente selecionado!</label>
              </div>
            <div v-else class="container-selecao container-selecionado">
              <div class="selecionado-label">
                <span class="primary--text" style="font-size: 14px">Nome</span>
                <p>{{ pacienteSelecionado.nome }}</p>
              </div>
              <div class="selecionado-label" style="margin-top: 5px">
                <span class="primary--text" style="font-size: 14px">CPF/CNPJ</span>
                <p>{{ pacienteSelecionado.cnpjCpf | formatarCpfCnpj }}</p>
              </div>
            </div>
          </v-row>
          <v-row wrap align-center white class="pl-5 pr-5">
            <v-autocomplete
                v-model="pacienteSelecionado"
                name="selectPaciente"
                placeholder="Pesquise por nome..."
                no-data-text="Nenhum paciente encontrado..."
                :items="listaPacientes"
                item-text="nome"
                return-object
                :loading="estaBuscandoPacientes"
                :search-input.sync="buscarPacientesDinamicamente"
                class="select-pacientes"
            >
              <template v-slot:item="{item}">
                <label class="auto-complete-item-text">{{ item.nome }}<label class="paciente-documento">({{ item.cnpjCpf | formatarCpfCnpj}})</label></label>
              </template>
            </v-autocomplete>
          </v-row>
        </v-card-text>
        <v-container>
          <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
            <botao-cancelar @cancelar="fecharModal"/>
            <div style="display: flex; flex-direction: row">
              <botao-acao
                  hideBorder
                  :disabled="!pacienteSelecionado"
                  @click="confimarSelecaoPaciente">
                <v-icon>mdi-magnify</v-icon>
                Confirmar
              </botao-acao>
            </div>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>
import {actionTypes} from '@/core/constants'
import BotaoCancelar from '@/views/components/BotaoCancelar'
import BotaoAcao from '@/views/components/BotaoAcao.vue'

export default {
    name: 'PesquisaPacienteModal',
    components: {BotaoAcao, BotaoCancelar},
    props: {
        value: Boolean,
    },
    data() {
        return {
            pacienteSelecionado: null,
            listaPacientes: [],
            estaBuscandoPacientes: false,
            buscarPacientesDinamicamente: null,
        }
    },
    watch: {
        buscarPacientesDinamicamente(val) {
            if (val) {
                if (this.estaBuscandoPacientes) return

                if (val.length > 4) {
                    this.buscarPacientes(val)
                }
            } else {
                this.buscarPacientes()
            }
        },
    },
    async mounted() {
        await this.buscarPacientes()
    },
    methods: {
        async buscarPacientes(conteudo){
            this.estaBuscandoPacientes = true
            const resposta = await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO, conteudo)
            if (resposta) {
                this.listaPacientes = resposta.items
                console.log(this.listaPacientes)
            }
            this.estaBuscandoPacientes = false
        },
        confimarSelecaoPaciente() {
            if(!this.pacienteSelecionado){
                this.mostrarNotificacaoErro('Selecione um paciente!')
                return
            }
            this.$emit('confirmarPacienteSelecionado', this.pacienteSelecionado)
        },
        fecharModal() {
            this.$emit('fecharModalPesquisaPaciente')
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

.container-selecao
  background-color #E0E0E0
  height 150px
  width 100%
  display flex
  flex-direction column
  justify-content center
  align-items center
  border-radius 5px

.container-nao-selecionado
  font-weight bold
  font-size 24px

.container-selecionado
  align-items start
  padding 10px
  font-size 20px

.selecionado-label
  font-weight bold

.auto-complete-item-text
  font-size 14px !important
  color #555 !important

.paciente-documento
  background-color #E0E0E0
  margin-left 5px
  border-radius 5px
  padding 2px

</style>