<template>
    <v-dialog v-model="value" max-width="720" scrollable @click:outside="fecharModal">
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Gerenciar Peças</v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <modal-gerenciar-pecas-cadastro
                    v-if="exibirModalCadastro"
                    v-model="exibirModalCadastro"
                    @cancelarAcaoCadastro="tratarEventoCancelarModalCadastro"/>
            <modal-gerenciar-pecas-edicao
                    v-if="exibirModalEdicao"
                    v-model="exibirModalEdicao"
                    :item="itemEdicao"
                    @cancelarAcaoEditar="tratarEventoCancelarModalEdicao"/>
            <v-toolbar flat style="border-bottom: 1px solid #DDDDDD">
                <botao-acao
                        hideBorder
                        @click="cadastrarCidade">
                    <v-icon>add_circle</v-icon>
                    Cadastrar
                </botao-acao>
                <v-spacer/>
                <pesquisa-avancada
                        :filter="filtrosInterno"
                        @remove-filter="tratarEventoRemoverFiltro"
                        :maxlengthInput="maxInputPesquisa"
                        @simple-search="tratarEventoBuscaSimples"
                        simple-search-placeholder="Busque por: descrição"
                />
            </v-toolbar>
            <v-card-text class="modal-peca-text">
                <modal-gerenciar-pecas-tabela
                        :itens="itens"
                        :paginas="paginas"
                        :total-itens="totalItens"
                        :paginacao="$store.state.peca.resultadoBuscaTodasPecas.paginacao"
                        @acessar="tratarEventoAcessar"
                        @paginar="tratarEventoPaginar"
                />
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    import _ from 'lodash'
    import {actionTypes, mutationTypes} from '@/core/constants'
    import {mapActions, mapMutations} from 'vuex'
    import BotaoAcao from '@/views/components/BotaoAcao'
    import PesquisaAvancada from '@/views/components/PesquisaAvancada'
    import ModalGerenciarPecasCadastro from './ModalGerenciarPecasCadastro'
    import ModalGerenciarPecasEdicao from './ModalGerenciadorPecasEdicao'
    import ModalGerenciarPecasTabela from './ModalGerenciarPecasTabela'

    export default {
        name: 'ModalGerenciarPecas',
        components: {
            ModalGerenciarPecasTabela,
            ModalGerenciarPecasEdicao,
            ModalGerenciarPecasCadastro,
            PesquisaAvancada, BotaoAcao
        },
        props: {
            value: Boolean
        },
        data() {
            return {
                filtrosInterno: this.getFiltros(),
                itens: [],
                paginas: 0,
                totalItens: 0,
                maxInputPesquisa: 30,
                exibirModalCadastro: false,
                exibirModalEdicao: false,
                itemEdicao: {}
            }
        },
        methods: {
            ...mapActions([actionTypes.ORCAMENTO.PECA.BUSCAR_TODAS_PECAS]),
            ...mapMutations([
                mutationTypes.ORCAMENTO.PECA.SET_PAGINACAO_BUSCA_TODAS_PECAS,
                mutationTypes.ORCAMENTO.PECA.SET_FILTROS_BUSCA_TODAS_PECAS,
                mutationTypes.ORCAMENTO.PECA.RESETA_PAGE
            ]),
            async buscar() {
                this.setFiltroBuscaTodasPecas(this.getFiltrosInterno())
                await this.buscarPecas()
            },
            async buscarPecas() {
                const resultado = await this.buscarTodasPecas()
                if (resultado) {
                    this.itens = resultado.content
                    this.paginas = resultado.totalPages
                    this.totalItens = resultado.totalElements
                }
            },
            getFiltros() {
                return _.cloneDeep(this.$store.state.peca.resultadoBuscaTodasPecas.filtros)
            },
            getFiltrosInterno() {
                return _.cloneDeep(this.filtrosInterno)
            },
            tratarEventoBuscaSimples(busca) {
                this.resetaPage()
                this.filtrosInterno.conteudo.value = busca
                this.buscar()
            },
            tratarEventoPaginar(paginacao) {
                this.setPaginacaoBuscaTodasPecas(paginacao)
                this.buscar()
            },
            tratarEventoRemoverFiltro(propriedade) {
                if (this.filtrosInterno[propriedade]) {
                    this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
                }
                this.buscar()
            },
            fecharModal() {
                this.$emit('fecharModalPecas')
            },
            cadastrarCidade() {
                this.exibirModalCadastro = true
            },
            async tratarEventoCancelarModalCadastro() {
                this.exibirModalCadastro = false
                await this.buscarPecas()
            },
            async tratarEventoCancelarModalEdicao() {
                this.exibirModalEdicao = false
                await this.buscarPecas()
            },
            tratarEventoAcessar(item) {
                this.itemEdicao = item
                this.exibirModalEdicao = true
            },
        }
    }
</script>

<style scoped lang="stylus">

    .modal-peca-text
        padding 0px !important
        overflow-x hidden
        height auto

</style>