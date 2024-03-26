<template>
    <div>
        <modal-dente-item-servico
                v-if="modalServico"
                v-model="modalServico"
                :face="face"
                @fecharModalServico="fecharModalAdicionarServico"
                @selecionarServico="adicionarServico"
        />
        <modal-servico-item-editar
                v-if="modalServicoEditar"
                v-model="modalServicoEditar"
                :item="servicoSelecionado"
                @cancelarAcaoEditar="fecharModalEditarServicoItem"
        />
        <div class="header-secao-dente-modal">
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <v-btn v-on="on" depressed @click="abrirModalSelecionarServico">
                        <v-icon color="#487B9C">add_circle</v-icon>
                    </v-btn>
                </template>
                Adicionar Serviço
            </v-tooltip>
        </div>
        <servicos-listagem-tabela
                :itens="itens"
                :paginas="paginas"
                :total-itens="totalItens"
                :paginacao="$store.state.servicoItem.resultadoBuscaTodosServicosItem.paginacao"
                @editarServico="tratarEventoEditar"
                @paginar="tratarEventoPaginar"
        />
    </div>
</template>

<script>
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import ServicosListagemTabela from './ServicosListagemTabela'
import ModalDenteItemServico from './ModalDenteItemServico'
import ModalServicoItemEditar from './ModalServicoItemEditar'

export default {
    name: 'OdontogramaModalDenteServicosRealizados',
    props: ['face', 'denteItem'],
    components: {ModalServicoItemEditar, ModalDenteItemServico, ServicosListagemTabela},
    data() {
        return {
            itens: [],
            paginas: 0,
            totalItens: 0,
            modalServico: false,
            modalServicoEditar: false,
            servicoSelecionado: {}
        }
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.SERVICO_ITEM.BUSCAR_SERVICOS_ITEM_POR_DENTE_ITEM,
            actionTypes.ORCAMENTO.DENTE_ITEM.CADASTRAR_DENTE_ITEM,
            actionTypes.ORCAMENTO.SERVICO_ITEM.CADASTRAR_SERVICO_ITEM
        ]),
        ...mapMutations([
            mutationTypes.ORCAMENTO.SERVICO_ITEM.SET_PAGINACAO_BUSCA_TODOS_SERVICOS_POR_DENTE_ITEM,
        ]),
        async buscar() {
            if (this.denteItem.id) {
                const resultado = await this.buscarServicosItemPorDenteItem(this.denteItem.id)
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
            this.$emit('atualizarValorServico', valorTotal)
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodosServicosPorDenteItem(paginacao)
            this.buscar()
        },
        tratarEventoEditar(item) {
            this.servicoSelecionado = item
            this.abrirModalEditarServicoItem()
        },
        abrirModalSelecionarServico() {
            this.modalServico = true
        },
        fecharModalAdicionarServico() {
            this.modalServico = false
        },
        abrirModalEditarServicoItem() {
            this.modalServicoEditar = true
        },
        async fecharModalEditarServicoItem() {
            await this.buscar()
            this.modalServicoEditar = false
        },
        async adicionarServico(item) {
            if (this.denteItem.id && this.$route.params.orcamentoId) {
                await this.salvarServicoItem(item, this.denteItem.id)
            } else {
                const resposta = await this.salvarDenteItem()
                await this.salvarServicoItem(item, resposta.id)
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
        async salvarServicoItem(item, denteId) {
            const servicoItem = {
                servico_id: item.id,
                orcamento_id: this.$route.params.orcamentoId,
                denteItem_id: denteId,
                localizacao: item.localizacao,
                valor: item.valor
            }
            this.setMensagemLoading('Salvando o serviço...')
            await this.cadastrarServicoItem(servicoItem)
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