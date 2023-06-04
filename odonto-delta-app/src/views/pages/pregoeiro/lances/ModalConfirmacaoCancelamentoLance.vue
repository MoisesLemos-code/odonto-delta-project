<template>
  <v-dialog v-model="value" max-width="390" persistent>
    <v-card id="modal-confirmacao-cancelamento">
      <v-toolbar dark color="#3a6861" elevation="0">
        <v-toolbar-title><h4>Cancelar Lance</h4></v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <div class="modal-cancelamento-lance-container">
        <div class="cancelamento-lance-container-body">
          <v-icon size="70" color="#ccc">help_outline</v-icon>
          <span>Tem certeza que deseja cancelar o lance do <b>{{ lance.participante }}</b>, com o valor de <b>{{ lance.valorLance | valorParaReal(casasDecimais) }}</b>?</span>
        </div>
        <div class="row-bottom">
          <v-btn outlined depressed @click="fecharModal" class="mr-5" color="#777">Não</v-btn>
          <v-btn depressed color="#3a6861" class="btn-cancelar-lance" @click="cancelarItem(lance)">Sim</v-btn>
        </div>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
    name: 'ModalCancelamentoLance',
    props: {
        value: Boolean,
        lance: {
            type: Object
        }
    },
    data(){
        return {
            lanceSelecionado: this.lance
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    mounted() {
        this.ajustarBarraLateral('modal-confirmacao-cancelamento')
    },
    methods: {
        fecharModal() {
            this.$emit('fecharModalCancelamento')
        },
        cancelarItem(lance) {
            this.$emit('cancelarLance', lance)
        },
    }
}
</script>

<style scoped lang="stylus">

.modal-cancelamento-lance-container
  height auto
  overflow-x hidden

.cancelamento-lance-container-body
  display flex
  flex-direction column
  text-align center
  padding 80px 20px
  color #777

.row-bottom
  border-top-width 1px
  border-top-style solid
  border-top-color #ccc
  box-sizing border-box
  padding-top 10px
  padding-bottom 10px
  text-align center

.btn-cancelar-lance
  color white

</style>
