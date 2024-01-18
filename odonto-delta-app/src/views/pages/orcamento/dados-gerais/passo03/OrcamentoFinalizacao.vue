<template>
    <div>
        <v-form class="form-content pb-0 altura-componente-container" ref="form">
            <v-container class="white" fluid>
                <v-row>
                    <v-col cols="12" md="12" sm="12" xs="12">
                        <servicos-realizados-tabela :itens="dadosDeEntrada.servicosItens"
                                                    :valorTotal="dadosDeEntrada.servicosItensTotal"/>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="12" sm="12" xs="12">
                        <pecas-realizadas-tabela :itens="dadosDeEntrada.pecasItens"
                                                 :valorTotal="dadosDeEntrada.pecasItensTotal"/>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="12" sm="12" xs="12">
                        <span class="orcamento-valor-total">Total: {{ dadosDeEntrada.valorTotal | valorParaReal}}</span>
                    </v-col>
                </v-row>

            </v-container>
        </v-form>
        <acoes-orcamento :final="true" :controleContinuar="podeContinuar" @voltar="tratarEventoVoltar"
                         @continuar="tratarEventoContinuar" @finalizarOrcamento="finalizar"/>
    </div>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import AcoesOrcamento from '@/views/pages/orcamento/dados-gerais/registro-orcamento/AcoesOrcamento'
import ServicosRealizadosTabela from './servicosRealizados/ServicosRealizadosTabela'
import PecasRealizadasTabela from './pecasRealizadas/PecasRealizadasTabela'

export default {
    name: 'OrcamentoFinalizacao',
    components: {PecasRealizadasTabela, ServicosRealizadosTabela, AcoesOrcamento},
    data() {
        return {
            dadosDeEntrada: {
                id: null
            },
            orcamentoId: null,
        }
    },
    computed: {
        podeContinuar() {
            return this.dadosDeEntrada.id
        }
    },
    async mounted() {
        this.setOrcamentoId()
        await this.buscarOrcamento()
        this.verificarPasso2()
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.FINALIZAR_ORCAMENTO,
            actionTypes.ORCAMENTO.BUSCAR_ORCAMENTO_POR_ID,
        ]),
        setOrcamentoId() {
            if (this.$route.params.orcamentoId) {
                this.orcamentoId = this.$route.params.orcamentoId
            }
        },
        async buscarOrcamento() {
            this.dadosDeEntrada = await this.buscarOrcamentoPorId(this.orcamentoId)
        },
        async finalizar() {
            this.setMensagemLoading('Finalizando o orçamento...')
            await this.finalizarOrcamento(this.orcamentoId)
            await this.$router.push({name: 'OrcamentoListagem'})
        },
        verificarPasso2() {
            if (this.podeContinuar) {
                this.$emit('habilitaPasso2')
            } else {
                this.$emit('desabilitaPasso2')
            }
        },
        tratarEventoVoltar() {
            this.$router.push({
                name: 'OrcamentoOdontograma',
                params: {orcamentoId: this.orcamentoId}
            })
        },
        tratarEventoContinuar() {
            this.$router.push({
                name: 'OrcamentoPaciente',
                params: {orcamentoId: this.orcamentoId}
            })
        }
    }
}
</script>

<style lang="stylus">

    .orcamento-valor-total
        font-size 20px
        font-weight bold
        color #757575

</style>
