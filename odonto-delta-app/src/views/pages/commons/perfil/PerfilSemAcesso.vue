<template>
  <v-container fluid white class="altura-componente-container">
    <v-row>
      <v-col cols="12">
        <v-row align="center" justify="center" class="altura-componente-container">
          <div class="text-center">
            <p class="text-info">Você não possui <b>acesso</b> a esta funcionalidade :(</p>
            <p style="color: #777">Contate o administrador do seu sistema!</p>
          </div>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

    export default {
        name: 'PerfilSemAcesso'
    }
</script>

<style scoped lang="stylus">
  .text-info
    color #777 !important
    font-size 24px
</style>