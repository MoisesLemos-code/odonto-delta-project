<template>
  <v-row class="primary pa-5 mb-5">
    <v-row class="pl-5" cols="12" md="6">
      <v-col class="headerCabecalho pr-0" md="2" sm="2" xs="6">
        <span class="font-weight-bold">Paciente</span>
        <p class="mb-0 text-truncate">{{ paciente.nome | textoSemValor }}</p>
      </v-col>
      <v-col class="headerCabecalho pl-0 pr-0" md="1" sm="1" xs="6">
        <span class="font-weight-bold">Idade</span>
        <p class="mb-0" v-if="paciente.idade">{{ paciente.idade | textoSemValor }} anos</p>
        <p class="mb-0" v-else>{{ paciente.idade | textoSemValor }}</p>
      </v-col>
      <v-col class="headerCabecalho pl-0" md="1" sm="1" xs="6">
        <span class="font-weight-bold">Ortodontia</span>
        <p class="mb-0 text-truncate">{{ paciente.ortodontia ? 'Sim' : 'Não' }}</p>
      </v-col>
      <v-col class="headerCabecalho pl-0" md="3" sm="3" xs="6">
        <span class="font-weight-bold">Cidade</span>
        <p class="mb-0 text-truncate">{{ paciente.cidade | textoSemValor }} - {{ paciente.estado | textoSemValor}}</p>
      </v-col>
      <v-col class="headerCabecalho" md="5" sm="5" xs="6">
        <div class="ficha-header-actions">
          <botao-imprimir outlined :disabled="!paciente.id" @imprimirRelatorio="relatorioFichaPaciente"/>
          <botao-editar outlined :disabled="!paciente.id" @editar="editarPaciente" class="btn-editar"/>
          <botao-excluir :disabled="!paciente.id" @excluir="abrirModalExcluir" class="btn-excluir"/>
        </div>
      </v-col>
    </v-row>
  </v-row>
</template>

<script>
import BotaoEditar from '@/views/components/BotaoEditar'
import BotaoExcluir from '@/views/components/BotaoExcluir'
import BotaoImprimir from '@/views/components/BotaoImprimir.vue'

export default {
    name: 'PacienteFichaCabecalho',
    components: {BotaoImprimir, BotaoExcluir, BotaoEditar},
    props: {
        paciente: {
            required: true
        }
    },
    methods:{
        abrirModalExcluir(){
            this.$emit('abrirModalExcluir')
        },
        editarPaciente(){
            this.$emit('editarPaciente')
        },
        relatorioFichaPaciente(){
            this.$emit('relatorioFichaPaciente')
        }
    }
}
</script>

<style scoped lang="stylus">
.headerCabecalho
  font-size 0.9em
  color: white
  max-height 90px

.reabrirIncorporacao
  opacity 0.3
  font-size 15px

.ficha-header-actions
  margin-right 5px
  display flex
  flex-direction row
  justify-content end

.btn-excluir, .btn-editar
  margin-left 15px

</style>
