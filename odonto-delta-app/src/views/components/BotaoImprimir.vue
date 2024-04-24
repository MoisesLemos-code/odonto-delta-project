<template>
<div>
  <v-tooltip top>
    <template v-slot:activator={on}>
      <v-btn
          v-on="on"
          id="botaoImprimir"
          :outlined="outlined"
          :disabled="disabled"
          icon
          :color="color"
          @click="$emit('imprimirRelatorio')">
        <v-icon>mdi-file-document</v-icon>
      </v-btn>
    </template>
    {{ texto }}
  </v-tooltip>
</div>
</template>

<script>
export default {
    name: 'BotaoImprimir',
    props:{
        disabled: {
            type: Boolean,
            default: false
        },
        outlined: {
            type: Boolean,
            default: false
        },
        color: {
            type: String,
            default: 'white'
        },
        texto:{
            type: String,
            default: 'Imprimir Relatório'
        }
    }
}
</script>

<style lang="stylus" scoped>

</style>
