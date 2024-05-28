<template>
    <div>
        <paciente-ficha-cobranca-modal
            v-if="modalAdicionarCobranca"
            v-model="modalAdicionarCobranca"
            @fecharModalCadastro="fecharModalCadastroCobranca"
        />
        <paciente-ficha-cobranca-edicao-modal
            v-if="modalEditarCobranca"
            v-model="modalEditarCobranca"
            :id-cobranca="idCobranca"
            @fecharModalEdicao="fecharModalEdicaoCobranca"
          />
        <v-row class="white pl-3 pr-3 ml-0 mr-0 pb-5">
            <v-col cols="12">
                <v-card class="dados-gerais">
                    <v-expansion-panels v-model="exibirPanel" active-class="" flat>
                        <v-expansion-panel>
                            <v-expansion-panel-header class="dados-gerais-panel-header">
                                <h4 class="titulo">Cobranças</h4>
                            </v-expansion-panel-header>
                            <v-expansion-panel-content class="container-cobrancas">
                              <div class="container-pesquisa-cobrancas">
                                <v-btn color="primary" depressed @click="adicionarCobranca">
                                  <v-icon>mdi-plus</v-icon>
                                  Adicionar
                                </v-btn>
                                <paciente-ficha-cobranca-pesquisa
                                  :filtros="filtrosInterno"
                                  @buscarComFiltros="tratarEventoBuscaSimples"
                                />
                                <pesquisa-avancada
                                    v-if="false"
                                    :filter="filtrosInterno"
                                    :buscaAvancada="true"
                                    @remove-filter="tratarEventoRemoverFiltro"
                                    @simple-search="tratarEventoBuscaSimples"
                                    @advanced-search="tratarEventoBuscaAvancada"
                                    @clear="tratarEventoLimparBuscaAvancada"
                                    :maxlengthInput="maxInputPesquisa"
                                    simple-search-placeholder="Busque por: codigo, status ou data de vencimento...">
                                  <item-de-pesquisa slot="search-items">
                                    <v-select
                                        v-model="filtrosInterno.atributo.value"
                                        label="Filtrar por"
                                        :items="filtrosInterno.atributo.data"
                                        dense/>
                                    <v-select
                                        v-model="filtrosInterno.tipo.value"
                                        label="Tipo de busca"
                                        :items="filtrosInterno.tipo.data"
                                        dense/>
                                    <v-text-field
                                        v-model="filtrosInterno.conteudo.value"
                                        label="Conteúdo da busca"
                                        :maxlength="maxInputPesquisa"
                                        placeholder="Pesquise aqui..."
                                        @keyup.enter="tratarEventoBuscaAvancada"/>

                                  </item-de-pesquisa>
                                </pesquisa-avancada>
                              </div>

                              <div class="container-tabela-cobrancas">
                                <paciente-ficha-cobrancas-tabela
                                    :itens="itens"
                                    :paginas="paginas"
                                    :total-itens="totalItens"
                                    :paginacao="$store.state.cobranca.resultadoBuscaTodasCobrancas.paginacao"
                                    @acessar="tratarEventoAcessarCobranca"
                                    @paginar="tratarEventoPaginar"
                                />
                              </div>

                            </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-card>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import _ from 'lodash'
import {mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PacienteFichaCobrancasTabela from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancasTabela.vue'
import PesquisaAvancada from '@/views/components/PesquisaAvancada.vue'
import ItemDePesquisa from '@/views/components/ItemDePesquisa.vue'
import PacienteFichaCobrancaPesquisa from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancaPesquisa.vue'
import PacienteFichaCobrancaModal from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancaModal.vue'
import PacienteFichaCobrancaEdicaoModal from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancaEdicaoModal.vue'

export default {
    name: 'PacienteFichaCobrancas',
    components: {
        PacienteFichaCobrancaEdicaoModal,
        PacienteFichaCobrancaModal,
        PacienteFichaCobrancaPesquisa, ItemDePesquisa, PesquisaAvancada, PacienteFichaCobrancasTabela},
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            exibirPanel: false,
            idPaciente: null,
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30,
            orcamentoSelecionado: {},
            orcamentoModal: false,
            modalAdicionarCobranca: false,
            modalEditarCobranca: false,
            idCobranca: null,
        }
    },
    async mounted() {
        this.setarIdPaciente()
    },
    methods: {
        ...mapMutations([
            mutationTypes.COBRANCA.SET_FILTROS_BUSCA_TODAS_COBRANCAS,
            mutationTypes.COBRANCA.SET_PAGINACAO_BUSCA_TODAS_COBRANCAS,
            mutationTypes.COBRANCA.RESETA_PAGE
        ]),
        setarIdPaciente() {
            if (this.$route.params.id) {
                this.idPaciente = this.$route.params.id
            }
        },
        async buscar() {
            this.setFiltrosBuscaTodasCobrancas(this.getFiltrosInterno())
            await this.buscarCobrancas()
        },
        async buscarCobrancas(){
            const resposta = await this.$store.dispatch(actionTypes.COBRANCA.BUSCAR_TODAS_COBRANCAS, this.idPaciente)
            if(resposta){
                this.itens = resposta.items
                this.paginas = resposta.totalPages
                this.totalItens = resposta.totalElements
            }
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.cobranca.resultadoBuscaTodasCobrancas.filtros)
        },
        getFiltrosInterno() {
            return _.cloneDeep(this.filtrosInterno)
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodasCobrancas(paginacao)
            this.buscar()
        },
        tratarEventoRemoverFiltro(propriedade) {
            if (this.filtrosInterno[propriedade]) {
                this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
            }
            this.buscar()
        },
        tratarEventoAcessarCobranca(item) {
            const id = item.id
            this.idCobranca = id
            this.modalEditarCobranca = true
        },
        fecharModalEdicaoCobranca(){
            this.modalEditarCobranca = false
            this.idCobranca = null
            this.buscar()
        },
        tratarEventoBuscaSimples(busca) {
            console.log('---tratarEventoBuscaSimples')
            console.log(busca)
            this.resetaPage()
            this.filtrosInterno.tipo.value = busca.tipo.value
            this.filtrosInterno.atributo.value = busca.atributo.value
            this.filtrosInterno.conteudo.value = busca.conteudo.value
            this.buscar()
        },
        tratarEventoBuscaAvancada() {
            if((this.filtrosInterno.tipo.value || this.filtrosInterno.atributo.value) && !this.filtrosInterno.conteudo.value){
                this.mostrarNotificacaoErro('Informe o conteúdo da busca!')
                return
            }
            this.resetaPage()
            this.buscar()
        },
        tratarEventoLimparBuscaAvancada() {
            this.filtrosInterno.tipo.value = this.filtrosInterno.tipo.default
            this.filtrosInterno.atributo.value = this.filtrosInterno.atributo.default
            this.filtrosInterno.conteudo.value = this.filtrosInterno.conteudo.default
            this.buscar()
        },
        adicionarCobranca(){
            this.modalAdicionarCobranca = true
        },
        fecharModalCadastroCobranca(){
            this.modalAdicionarCobranca = false
            this.buscar()
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

    .container-cobrancas
      >>>.v-expansion-panel-content__wrap
            padding 0px

    .container-pesquisa-cobrancas
      display flex
      justify-content space-between
      align-items center
      padding 0 10px
      margin-top 10px

    .container-tabela-cobrancas
      padding 0 24px 16px

</style>
