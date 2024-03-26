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
        <p class="mb-0 text-truncate">{{ paciente.cidade.nome | textoSemValor }} - {{ paciente.cidade.estado.sigla | textoSemValor}}</p>
      </v-col>
      <v-col class="headerCabecalho" md="5" sm="5" xs="6">
        <div class="ficha-header-actions">
          <botao-editar outlined :disabled="!paciente.id" @editar="editarPaciente"/>

          <botao-excluir :disabled="!paciente.id" @excluir="abrirModalExcluir" class="btn-excluir"/>
        </div>
      </v-col>
    </v-row>
  </v-row>
</template>

<script>
import BotaoEditar from '@/views/components/BotaoEditar'
import BotaoExcluir from '@/views/components/BotaoExcluir'

export default {
    name: 'PacienteFichaCabecalho',
    components: { BotaoExcluir, BotaoEditar},
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
  text-align end

.btn-excluir
  margin-left 15px

</style>
