<template>
    <v-dialog v-model="value" max-width="390" persistent>
        <v-card id="modal-solicitar-cancelamento">
            <v-toolbar dark color="#3a6861" elevation="0">
                <v-toolbar-title><h4>Solicitar Cancelamento</h4></v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <div class="modal-cancelamento-container">
                <div class="cancelamento-container-body">
                    <v-icon size="70" color="#ccc">help_outline</v-icon>
                    <span>Tem certeza que deseja solicitar cancelamento do último lance no valor de <b>{{ item.ultimoLance | valorParaReal(casasDecimais) }}</b>?</span>
                </div>
                <div class="row-bottom">
                    <v-btn outlined depressed @click="fecharModal" class="mr-5" color="#777">Não</v-btn>
                    <v-btn :loading="carregarCancelarLance" depressed color="#3a6861" class="btn-cancelar" @click="cancelarItem">Sim</v-btn>
                </div>
            </div>
        </v-card>
    </v-dialog>
</template>

<script>
import {mapActions, mapState} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'ModalSolicitarCancelamento',
    props: {
        value: Boolean,
        item: {
            type: Object
        },
        fornecedorId: String,
        pregaoId: String
    },
    data(){
        return{
            carregarCancelarLance: false
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    mounted() {
        this.ajustarBarraLateral('modal-solicitar-cancelamento')
    },
    methods: {
        ...mapState(['licitacao']),
        ...mapActions([
            actionTypes.FORNECEDOR.SOLICITAR_CANCELAMENTO_LANCE
        ]),
        fecharModal() {
            this.$emit('fecharModalCancelamento')
        },
        async cancelarItem() {
            try {
                this.carregarCancelarLance = true
                const dados = {
                    idFornecedor: parseInt(this.fornecedorId),
                    idPregao: parseInt(this.pregaoId),
                    idLote: this.item.idLote,
                    valorLance: this.item.ultimoLance
                }
                await this.solicitarCancelamentoLance(dados)
                this.mostrarNotificacaoSucesso('Solicitação de cancelamento enviada!')
                this.carregarCancelarLance = false
                this.fecharModal()
            }catch (err){
                this.mostrarNotificacaoErro(err.message)
                this.carregarCancelarLance = false
            }
        },
    }
}
</script>

<style scoped lang="stylus">

    .modal-cancelamento-container
        height auto
        overflow-x hidden

    .cancelamento-container-body
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

    .btn-cancelar
        color white

</style>
