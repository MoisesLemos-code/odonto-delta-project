<template>
  <v-container fluid white>
    <v-row>
      <v-col cols="12">
        <v-row align="center" justify="center" class="altura-componente-container">
          <div class="text-center">
            <p class="text-info">Nenhum <b>paciente</b> selecionado, clique para adicionar</p>
            <botao-acao
                active
                slot="actions"
                @click="adicionarPaciente">
              <v-icon>add_circle</v-icon>
              Adicionar Paciente
            </botao-acao>
          </div>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    name: 'OrcamentoPacienteVazio',
    methods:{
        adicionarPaciente(){
            this.$emit('adicionarPaciente')
        }
    }
}
</script>

<style scoped>

</style>