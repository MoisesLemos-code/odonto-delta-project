<template>
  <v-row justify="center">
    <v-dialog
        v-model="value"
        scrollable
        @click:outside="fecharModal"
        :persistent="persistencia"
        :max-width="maxWidth"
        style="padding: 0px">
      <v-card class="card-component">
        <v-card-title>{{ titulo }}</v-card-title>
        <v-card-text style="color: black">
          <p>{{ mensagemInicial }} <b>{{ mensagemMeio }}</b> {{ mensagemFinal }}</p>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="cancelar" :color="corCancelar">{{ textCancelar }}</v-btn>
          <v-btn @click="confirmar" text :color="corConfirmar">{{ textConfirmar }}</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
    name: 'Confirm-modal',
    props: {
        value: Boolean,
        mensagemInicial: String,
        mensagemMeio: String,
        mensagemFinal: String,
        persistencia: {
            type: Boolean,
            default: true
        },
        titulo: {
            type: String,
            default: 'Atenção!'
        },
        textCancelar: {
            type: String,
            default: 'Cancelar'
        },
        textConfirmar: {
            type: String,
            default: 'Excluir'
        },
        corCancelar: {
            type: String,
            default: 'black'
        },
        corConfirmar: {
            type: String,
            default: 'red'
        },
        maxWidth: {
            type: Number,
            default: 400
        }
    },
    methods: {
        fecharModal(){
            if(!this.persistencia){
                this.cancelar()
            }
        },
        cancelar() {
            this.$emit('modalAcaoCancelar')
        },
        confirmar() {
            this.$emit('modalAcaoConfirmar', this.produto)
        }
    }

}
</script>

<style scoped lang="stylus">
.card-component
  text-align start
  background-color #FFFFFF
  color #000000

</style>