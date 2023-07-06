<template>
  <div>
    <toolbar-view>
      <botao-acao slot="actions" hideBorder @click="tratarEventoIniciarOrcamento">
        <v-icon>add_circle</v-icon>Iniciar Orçamento
      </botao-acao>
      <v-flex slot="simpleSearch">
        <pesquisa-avancada
            :filter="filtrosInterno"
            :buscaAvancada="true"
            @remove-filter="tratarEventoRemoverFiltro"
            @simple-search="tratarEventoBuscaSimples"
            @advanced-search="tratarEventoBuscaAvancada"
            @clear="tratarEventoLimparBuscaAvancada"
            :maxlengthInput="maxInputPesquisa"
            simple-search-placeholder="Busque por: paciente, cpf, cnpj ou código...">
          <item-de-pesquisa slot="search-items">
            <v-select
                v-model="filtrosInterno.atributo.value"
                label="Filtrar por"
                :items="filtrosInterno.atributo.data"
                dense/>
            <v-select
                :disabled="(filtrosInterno.atributo.value === 'codigo')"
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
      </v-flex>
    </toolbar-view>
    <orcamento-listagem-tabela
        :itens="itens"
        :paginas="paginas"
        :total-itens="totalItens"
        :paginacao="$store.state.orcamento.resultadoBuscaTodosOrcamentos.paginacao"
        @acessar="tratarEventoAcessar"
        @paginar="tratarEventoPaginar"
    />
  </div>
</template>

<script>
    import _ from 'lodash'
    import { mapActions, mapMutations } from 'vuex'
    import { actionTypes, mutationTypes } from '@/core/constants'
    import OrcamentoListagemTabela from '@/views/pages/orcamento/listagem/OrcamentoListagemTabela'
    import PesquisaAvancada from '@/views/components/PesquisaAvancada'
    import ItemDePesquisa from '@/views/components/ItemDePesquisa'

    export default {
        name: 'OrcamentoListagem',
        components: {ItemDePesquisa, PesquisaAvancada, OrcamentoListagemTabela },
        data() {
            return {
                filtrosInterno: this.getFiltros(),
                itens: [],
                paginas: 0,
                totalItens: 0,
                maxInputPesquisa: 30
            }
        },
        methods:{
            ...mapActions([
                actionTypes.ORCAMENTO.BUSCAR_TODOS_ORCAMENTOS,
                actionTypes.ORCAMENTO.CADASTRAR_ORCAMENTO
            ]),
            ...mapMutations([
                mutationTypes.ORCAMENTO.SET_FILTROS_BUSCA_TODOS_ORCAMENTOS,
                mutationTypes.ORCAMENTO.SET_PAGINACAO_BUSCA_TODOS_ORCAMENTOS,
                mutationTypes.ORCAMENTO.RESETA_PAGE
            ]),
            async tratarEventoIniciarOrcamento(){
                let orcamentoSalvo = await this.cadastrarOrcamento({})
                await this.$router.push({name: 'OrcamentoPaciente', params: {orcamentoId: orcamentoSalvo.id}})
            },
            async buscar() {
                this.setFiltrosBuscaTodosOrcamentos(this.getFiltrosInterno())
                await this.buscaTodosOrcamentos()
            },
            async buscaTodosOrcamentos() {
                const resultado = await this.buscarTodosOrcamentos()
                if (resultado) {
                    this.itens = resultado.content
                    this.paginas = resultado.totalPages
                    this.totalItens = resultado.totalElements
                }
            },
            getFiltros() {
                return _.cloneDeep(this.$store.state.orcamento.resultadoBuscaTodosOrcamentos.filtros)
            },
            getFiltrosInterno() {
                return _.cloneDeep(this.filtrosInterno)
            },
            tratarEventoAcessar(item) {
                const id = item.id
                this.$router.push({name: 'OrcamentoPaciente', params: {orcamentoId: id}})
            },
            tratarEventoBuscaSimples(busca) {
                this.resetaPage()
                this.filtrosInterno.tipo.value = busca.tipo
                this.filtrosInterno.atributo.value = busca.atributo
                this.filtrosInterno.conteudo.value = busca.conteudo
                this.buscar()
            },
            tratarEventoBuscaAvancada(){
                this.resetaPage()
                this.buscar()
            },
            tratarEventoLimparBuscaAvancada(){
                this.filtrosInterno.tipo.value = this.filtrosInterno.tipo.default
                this.filtrosInterno.atributo.value = this.filtrosInterno.atributo.default
                this.filtrosInterno.conteudo.value = this.filtrosInterno.conteudo.default
                this.buscar()
            },
            tratarEventoPaginar(paginacao) {
                this.setPaginacaoBuscaTodosOrcamentos(paginacao)
                this.buscar()
            },
            tratarEventoRemoverFiltro(propriedade) {
                if (this.filtrosInterno[propriedade]) {
                    this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
                }
                this.buscar()
            }
        }
    }
</script>

<style scoped lang="stylus">

</style>