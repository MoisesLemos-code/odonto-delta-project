<template>
  <div>
    <toolbar-view>
      <botao-acao slot="actions" hideBorder @click="adicionarCobranca">
        <v-icon>add_circle</v-icon>
        Cadastrar
      </botao-acao>
      <v-flex slot="simpleSearch">
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
      </v-flex>
    </toolbar-view>
    <cobranca-listagem-tabela
        :itens="itens"
        :paginas="paginas"
        :total-itens="totalItens"
        :paginacao="$store.state.cobranca.resultadoBuscaTodasCobrancas.paginacao"
        @acessar="tratarEventoAcessarCobranca"
        @paginar="tratarEventoPaginar"
    />
  </div>
</template>

<script>
import _ from 'lodash'
import {mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import CobrancaListagemTabela from '@/views/pages/cadastros/cobranca/CobrancaListagemTabela.vue'
import PesquisaAvancada from '@/views/components/PesquisaAvancada.vue'
import ItemDePesquisa from '@/views/components/ItemDePesquisa.vue'
import PacienteFichaCobrancaPesquisa from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancaPesquisa.vue'

export default {
    name: 'PacienteFichaCobrancas',
    components: {
        PacienteFichaCobrancaPesquisa, ItemDePesquisa, PesquisaAvancada, CobrancaListagemTabela
    },
    data() {
        return {
            filtrosInterno: this.getFiltros(),
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
        async buscarCobrancas() {
            const resposta = await this.$store.dispatch(actionTypes.COBRANCA.BUSCAR_TODAS_COBRANCAS, this.idPaciente)
            if (resposta) {
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
        fecharModalEdicaoCobranca() {
            this.modalEditarCobranca = false
            this.idCobranca = null
            this.buscar()
        },
        tratarEventoBuscaSimples(busca) {
            this.resetaPage()
            this.filtrosInterno.tipo.value = busca.tipo.value
            this.filtrosInterno.atributo.value = busca.atributo.value
            this.filtrosInterno.conteudo.value = busca.conteudo.value
            this.buscar()
        },
        tratarEventoBuscaAvancada() {
            if ((this.filtrosInterno.tipo.value || this.filtrosInterno.atributo.value) && !this.filtrosInterno.conteudo.value) {
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
        adicionarCobranca() {
            this.modalAdicionarCobranca = true
        },
        fecharModalCadastroCobranca() {
            this.modalAdicionarCobranca = false
            this.buscar()
        }
    }
}
</script>

<style scoped lang="stylus">
.container-municipio-estado
  display flex
  flex-direction column

.select-estados
  max-width 210px
  margin-right 30px
.select-municipios
  max-width 210px
.call-to-action
  font-size 14px
  margin 0
  text-transform none
  position relative
  height unset
  padding 5px 15px
  box-shadow none
  font-weight bold

  i
    margin-right 5px
    font-size 18px

  &.hide-border
    border 0 !important
</style>
