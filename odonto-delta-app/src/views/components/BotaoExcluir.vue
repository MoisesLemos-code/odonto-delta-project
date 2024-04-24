<template>
<div>
  <v-tooltip top>
    <template v-slot:activator={on}>
      <v-btn
          v-on="on"
          id="botaoExcluir"
          outlined
          :disabled="disabled"
          icon
          :color="color"
          class="botao-excluir"
          @click="$emit('excluir')">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </template>
    Excluir
  </v-tooltip>
</div>
</template>

<script>
export default {
    name: 'BotaoExcluir',
    props: {
        disabled: {
            type: Boolean,
            default: false
        },
        color: {
            type: String,
            default: 'white'
        }
    }
}
</script>

<style lang="stylus" scoped>

    .botao-excluir:hover
        color red !important
</style>
