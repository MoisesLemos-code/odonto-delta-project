<template>
    <div>
        <v-form class="form-content pb-0 altura-componente-container" ref="form">
            <v-container class="pa-0" fluid>
                <odontograma-header :coresQuadrante="coresQuadrante" @alterarCoresQuadrantes="alterarCoresQuadrantes"/>
                <odontograma-component :coresQuadrante="coresQuadrante"/>
            </v-container>
        </v-form>
        <acoes-orcamento :controleContinuar="podeContinuar" @voltar="tratarEventoVoltar"
                         @continuar="tratarEventoContinuar"/>
    </div>
</template>

<script>
    import AcoesOrcamento from '@/views/pages/orcamento/dados-gerais/registro-orcamento/AcoesOrcamento'
    import OdontogramaHeader from './OdontogramaHeader'
    import OdontogramaComponent from './OdontogramaComponent'

    export default {
        name: 'OrcamentoOdontograma',
        components: {OdontogramaComponent, OdontogramaHeader, AcoesOrcamento},
        data() {
            return {
                coresQuadrante: false,
                orcamentoId: null,
                podeContinuar: true,
            }
        },
        mounted() {
            this.setOrcamentoId()
            this.verificarPasso3()
        },
        methods: {
            setOrcamentoId() {
                if (this.$route.params.orcamentoId) {
                    this.orcamentoId = this.$route.params.orcamentoId
                }
            },
            alterarCoresQuadrantes() {
                this.coresQuadrante = !this.coresQuadrante
            },
            verificarPasso3() {
                if (this.podeContinuar) {
                    this.$emit('habilitaPasso3')
                } else {
                    this.$emit('desabilitaPasso3')
                }
            },
            tratarEventoVoltar() {
                this.$router.push({
                    name: 'OrcamentoPaciente',
                    params: {orcamentoId: this.orcamentoId}
                })
            },
            tratarEventoContinuar() {
                this.$router.push({
                    name: 'OrcamentoFinalizacao',
                    params: {orcamentoId: this.orcamentoId}
                })
            }
        }
    }
</script>

<style lang="stylus">

</style>
