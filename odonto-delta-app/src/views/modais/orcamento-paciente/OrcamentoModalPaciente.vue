<template>
  <v-dialog v-model="value" max-width="720" scrollable persistent @click:outside="fecharModal">
    <v-card>
      <v-toolbar dark color="primary" elevation="0">
        <v-toolbar-title>Selecionar Paciente</v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text class="modal-pacientes-text">
        <v-container fluid grid-list-xl white>
          <v-row wrap align-center white class="pl-10 pr-10">
            <v-col cols="12" md="12" sm="12" xs="12">
          <v-autocomplete
              v-model="pacienteSelecionado"
              name="paciente"
              placeholder="Selecione o paciente"
              :items="pacientes"
              item-text="nome"
              return-object
              :filter="filtroComboAutoComplete"
              >
            <template v-slot:item="data">
              <label class="auto-complete-item-text">{{ data.item.nome }}</label>
            </template>
          </v-autocomplete>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn @click="fecharModal" depressed>Cancelar</v-btn>
        <v-btn @click="selecionarPaciente" color="primary" depressed>Selecionar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
    import {actionTypes} from '@/core/constants'
    import {mapActions} from 'vuex'

    export default {
        name: 'OrcamentoModalPaciente',
        props: {
            value: Boolean
        },
        data() {
            return {
                pacientes: {},
                pacienteSelecionado: null
            }
        },
        async mounted(){
            await this.buscarTodosPacientes()
        },
        methods:{
            ...mapActions([
                actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO
            ]),
            async buscarTodosPacientes(){
                this.pacientes = await this.buscarTodosPacientesSemPaginacao()
            },
            fecharModal() {
                this.$emit('fechar')
            },
            selecionarPaciente(){
                this.$emit('selecionar', this.pacienteSelecionado)
            },
            filtroComboAutoComplete(item, queryText) {
                const text = item.nome.toLowerCase()
                const searchText = queryText.toLowerCase()
                return text.indexOf(searchText) > -1
            },
        }
    }
</script>

<style scoped lang="stylus">

.modal-pacientes-text
  padding 0px !important
  overflow-x hidden
  height auto

</style>