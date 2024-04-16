<template>
    <div>
        <paciente-ficha-orcamento-modal
                v-if="orcamentoModal"
                v-model="orcamentoModal"
                :item="orcamentoSelecionado"
                @fecharEdicao="fecharModalOrcamento"
        />
        <v-row class="white pl-3 pr-3 ml-0 mr-0 pb-5">
            <v-col cols="12">
                <v-card class="dados-gerais">
                    <v-expansion-panels v-model="exibirPanel" active-class="" flat>
                        <v-expansion-panel>
                            <v-expansion-panel-header class="dados-gerais-panel-header">
                                <h4 class="titulo">Orçamentos</h4>
                            </v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <paciente-ficha-orcamentos-tabela
                                        :itens="itens"
                                        :paginas="paginas"
                                        :total-itens="totalItens"
                                        :paginacao="$store.state.orcamento.resultadoBuscaTodosOrcamentosPorPaciente.paginacao"
                                        @acessar="tratarEventoAcessar"
                                        @paginar="tratarEventoPaginar"
                                />
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-card>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import {mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PacienteFichaOrcamentosTabela from './PacienteFichaOrcamentosTabela'
import PacienteFichaOrcamentoModal from './PacienteFichaOrcamentoModal'

export default {
    name: 'PacienteFichaOrcamentos',
    components: {PacienteFichaOrcamentoModal, PacienteFichaOrcamentosTabela},
    data() {
        return {
            exibirPanel: false,
            idPaciente: null,
            itens: [],
            paginas: 0,
            totalItens: 0,
            orcamentoSelecionado: {},
            orcamentoModal: false,
        }
    },
    mounted() {
        this.setarIdPaciente()
    },
    methods: {
        ...mapMutations([mutationTypes.ORCAMENTO.SET_PAGINACAO_BUSCA_ORCAMENTOS_POR_PACIENTE]),
        setarIdPaciente() {
            if (this.$route.params.id) {
                this.idPaciente = this.$route.params.id
            }
        },
        async buscaTodosOrcamentosPorPaciente() {
            const resultado = await this.$store.dispatch(actionTypes.ORCAMENTO.BUSCAR_ORCAMENTOS_POR_PACIENTE, this.idPaciente)
            if (resultado) {
                this.itens = resultado.items
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        tratarEventoAcessar(item) {
            this.orcamentoSelecionado = item
            this.orcamentoModal = true
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaOrcamentosPorPaciente(paginacao)
            this.buscaTodosOrcamentosPorPaciente()
        },
        fecharModalOrcamento() {
            this.orcamentoSelecionado = null
            this.orcamentoModal = false
        }
    }
}
</script>

<style scoped lang="stylus">
    .dados-gerais
        border solid 1px #e7e7e7 !important
        border-radius 5px !important

    .dados-gerais-panel-header
        border-bottom solid 1px #e7e7e7 !important
        background-color #F6F6F6
        border-radius 5px !important
        min-height 20px !important
        padding-bottom 10px
        padding-top 10px

    .titulo
        font-size 15px
        font-weight bold
        color #777 !important

    .label
        font-size 13px
        font-weight bold
        color #777 !important

    .texto
        font-size 13px
        color #777 !important

</style>
