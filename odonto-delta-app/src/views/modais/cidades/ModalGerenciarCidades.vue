<template>
  <v-dialog v-model="value" max-width="720" scrollable @click:outside="fecharModal">
    <v-card>
      <v-toolbar dark color="primary" elevation="0">
        <v-toolbar-title>Gerenciar Cidades</v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <modal-gerenciar-cidades-cadastro
          v-if="exibirModalCadastro"
          v-model="exibirModalCadastro"
          @cancelarAcaoCadastro="tratarEventoCancelarModalCadastro"/>
      <modal-gerenciar-cidades-edicao
          v-if="exibirModalEdicao"
          v-model="exibirModalEdicao"
          :item="itemEdicao"
          @cancelarAcaoEditar="tratarEventoCancelarModalEdicao" />
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
                simple-search-placeholder="Busque por: cidade ou estado"
            />
        </v-toolbar>
        <v-card-text class="modal-cidades-text">
          <modal-gerenciar-cidades-tabela
              :itens="itens"
              :paginas="paginas"
              :total-itens="totalItens"
              :paginacao="$store.state.cidade.resultadoBuscaTodasCidades.paginacao"
              @acessar="tratarEventoAcessar"
              @paginar="tratarEventoPaginar"
          />
        </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import BotaoAcao from '@/views/components/BotaoAcao'
import PesquisaAvancada from '@/views/components/PesquisaAvancada'
import ModalGerenciarCidadesTabela from '@/views/modais/cidades/ModalGerenciarCidadesTabela'
import ModalGerenciarCidadesCadastro from '@/views/modais/cidades/ModalGerenciarCidadesCadastro'
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import _ from 'lodash'
import ModalGerenciarCidadesEdicao from '@/views/modais/cidades/ModalGerenciadorCidadesEdicao'

export default {
    name: 'ModalGerenciarCidades',
    components: {
        ModalGerenciarCidadesEdicao,
        ModalGerenciarCidadesCadastro, ModalGerenciarCidadesTabela, PesquisaAvancada, BotaoAcao},
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
        ...mapActions([actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES]),
        ...mapMutations([
            mutationTypes.CADASTROS.CIDADE.SET_FILTROS_BUSCA_TODAS_CIDADES,
            mutationTypes.CADASTROS.CIDADE.SET_PAGINACAO_BUSCA_TODAS_CIDADES,
            mutationTypes.CADASTROS.CIDADE.RESETA_PAGE
        ]),
        async buscar() {
            this.setFiltrosBuscaTodasCidades(this.getFiltrosInterno())
            await this.buscaTodasCidades()
        },
        async buscaTodasCidades() {
            const resultado = await this.buscarTodasCidades()
            if (resultado) {
                this.itens = resultado.content
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.cidade.resultadoBuscaTodasCidades.filtros)
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
            this.setPaginacaoBuscaTodasCidades(paginacao)
            this.buscar()
        },
        tratarEventoRemoverFiltro(propriedade) {
            if (this.filtrosInterno[propriedade]) {
                this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
            }
            this.buscar()
        },
        fecharModal() {
            this.$emit('fechar')
        },
        cadastrarCidade() {
            this.exibirModalCadastro = true
        },
        async tratarEventoCancelarModalCadastro() {
            this.exibirModalCadastro = false
            await this.buscaTodasCidades()
        },
        async tratarEventoCancelarModalEdicao(){
            this.exibirModalEdicao = false
            await this.buscaTodasCidades()
        },
        tratarEventoAcessar(item) {
            this.itemEdicao = item
            this.exibirModalEdicao = true
        },
    }
}
</script>

<style scoped lang="stylus">

.modal-cidades-text
  padding 0px !important
  overflow-x hidden
  height auto

</style>