<template>
  <v-dialog v-model="exibirModal" max-width="390" persistent>
    <v-card id="modal-reiniciar-etapa">
      <v-toolbar dark color="#3a6861" elevation="0">
        <v-toolbar-title><h4>Reiniciar Etapa Fechada.</h4></v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <div class="modal-reiniciar-etapa-fechada">
        <div class="modal-reiniciar-etapa-container-body">
          <span align="left">Justificativa:</span>
          <v-textarea v-model="justificativa" rows="1"/>
        </div>
        <div class="row-bottom">
          <v-btn outlined depressed @click="fecharModal" class="mr-5" color="#777">Cancelar</v-btn>
          <v-btn depressed color="#3a6861" class="btn-reiniciar-etapa" @click="reiniciarLote" :loading="esperarReiniciarEtapaFechada">Iniciar etapa fechada</v-btn>
        </div>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
    name: 'ModalReinicio',
    props: {
        exibirModal:{
            type: Boolean,
            value: false
        },
        esperarReiniciarEtapaFechada:{
            type: Boolean,
            value: false
        },
    },
    data(){
        return {
            lanceSelecionado: this.lance,
            justificativa:''
        }
    },
    mounted() {
        this.ajustarBarraLateral('modal-reiniciar-etapa')
    },
    methods: {
        fecharModal() {
            this.$emit('fecharModalReinicio')
        },
        reiniciarLote() {
            if(this.justificativa == ''){
                this.mostrarNotificacaoErro('Preencha a justificativa.')
            }
            this.$emit('reiniciarEtapa', this.justificativa)
        }
    }
}
</script>

<style scoped lang="stylus">

.modal-reiniciar-etapa-fechada
  height auto
  overflow-x hidden

.modal-reiniciar-etapa-container-body
  display flex
  flex-direction column
  text-align center
  padding 10px 20px
  color #777

.row-bottom
  border-top-width 1px
  border-top-style solid
  border-top-color #ccc
  box-sizing border-box
  padding-top 10px
  padding-bottom 10px
  text-align center

.btn-reiniciar-etapa
  color white

</style>