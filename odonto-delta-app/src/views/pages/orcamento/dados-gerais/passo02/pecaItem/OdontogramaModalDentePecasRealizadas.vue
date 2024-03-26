<template>
    <div>
        <modal-dente-item-peca
                v-if="modalPeca"
                v-model="modalPeca"
                :face="face"
                @fecharModalPeca="fecharModalAdicionarPeca"
                @selecionarPeca="adicionarPeca"
        />
        <modal-peca-item-editar
                v-if="modalPecaEditar"
                v-model="modalPecaEditar"
                :item="pecaSelecionada"
                @cancelarAcaoEditar="fecharModalEditarServicoItem"
        />
        <div class="header-secao-dente-modal">
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <v-btn v-on="on" depressed @click="abrirModalSelecionarPeca">
                        <v-icon color="#487B9C">add_circle</v-icon>
                    </v-btn>
                </template>
                Adicionar Peça
            </v-tooltip>
        </div>
        <pecas-listagem-tabela
                :itens="itens"
                :paginas="paginas"
                :total-itens="totalItens"
                :paginacao="$store.state.pecaItem.resultadoBuscaTodasPecasItem.paginacao"
                @editarPeca="tratarEventoEditar"
                @paginar="tratarEventoPaginar"
        />
    </div>
</template>

<script>
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PecasListagemTabela from './PecasListagemTabela'
import ModalDenteItemPeca from './ModalDenteItemPeca'
import ModalPecaItemEditar from './ModalPecaItemEditar'

export default {
    name: 'OdontogramaModalDentePecasRealizadas',
    props: ['face', 'denteItem'],
    components: {PecasListagemTabela, ModalPecaItemEditar, ModalDenteItemPeca},
    data() {
        return {
            itens: [],
            paginas: 0,
            totalItens: 0,
            modalPeca: false,
            modalPecaEditar: false,
            pecaSelecionada: {}
        }
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.PECA_ITEM.BUSCAR_PECAS_ITEM_POR_DENTE_ITEM,
            actionTypes.ORCAMENTO.DENTE_ITEM.CADASTRAR_DENTE_ITEM,
            actionTypes.ORCAMENTO.PECA_ITEM.CADASTRAR_PECA_ITEM
        ]),
        ...mapMutations([
            mutationTypes.ORCAMENTO.PECA_ITEM.SET_PAGINACAO_BUSCA_TODAS_PECAS_POR_DENTE_ITEM
        ]),
        async buscar() {
            if (this.denteItem.id) {
                const resultado = await this.buscarPecasItemPorDenteItem(this.denteItem.id)
                if (resultado) {
                    this.itens = resultado.content
                    this.paginas = resultado.totalPages
                    this.totalItens = resultado.totalElements
                    this.calcularValorTotal()
                }
            }
        },
        calcularValorTotal() {
            let valorTotal = 0.0
            this.itens.forEach(item => {
                valorTotal = parseFloat(valorTotal) + parseFloat(item.valor)
            })
            this.$emit('atualizarValorPeca', valorTotal)
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodasPecasPorDenteItem(paginacao)
            this.buscar()
        },
        tratarEventoEditar(item) {
            this.pecaSelecionada = item
            this.abrirModalEditarServicoItem()
        },
        abrirModalSelecionarPeca() {
            this.modalPeca = true
        },
        fecharModalAdicionarPeca() {
            this.modalPeca = false
        },
        abrirModalEditarServicoItem() {
            this.modalPecaEditar = true
        },
        async fecharModalEditarServicoItem() {
            await this.buscar()
            this.modalPecaEditar = false
        },
        async adicionarPeca(item) {
            if (this.denteItem.id && this.$route.params.orcamentoId) {
                await this.salvarPecaItem(item, this.denteItem.id)
            } else {
                const resposta = await this.salvarDenteItem()
                await this.salvarPecaItem(item, resposta.id)
            }
            await this.buscar()
        },
        async salvarDenteItem() {
            let resposta
            if (this.$route.params.orcamentoId) {
                this.setMensagemLoading('Salvando dados do dente...')
                const denteItem = {
                    orcamento_id: this.$route.params.orcamentoId,
                    dente_id: this.denteItem.dente.id,
                    possui: true
                }
                resposta = await this.cadastrarDenteItem(denteItem)
                this.denteItem.id = resposta.id
                this.$emit('atualizarDadosOdontograma')
            } else {
                this.mostrarNotificacaoErro('Não foi possível salvar as informações do dente.')
                return null
            }

            return resposta
        },
        async salvarPecaItem(item, denteId) {
            const pecaItem = {
                peca_id: item.id,
                orcamento_id: this.$route.params.orcamentoId,
                denteItem_id: denteId,
                localizacao: item.localizacao,
                valor: item.valor
            }
            this.setMensagemLoading('Salvando a peça...')
            await this.cadastrarPecaItem(pecaItem)
            this.mostrarNotificacaoSucessoDefault()
        }
    }
}
</script>

<style scoped lang="stylus">

    .header-secao-dente-modal
        width 100%
        height 50px
        display flex
        align-items center
        padding 0px 10px
        background-color #487B9C

</style>