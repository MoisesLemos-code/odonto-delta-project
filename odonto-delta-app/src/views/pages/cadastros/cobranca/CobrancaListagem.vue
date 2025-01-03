<template>
  <div>
    <PesquisaPacienteModal
        v-if="modalSelecionarPaciente"
        v-model="modalSelecionarPaciente"
        @fecharModalPesquisaPaciente="fecharModalSelecionarPaciente"
        @confirmarPacienteSelecionado="selecionarPaciente"
    />
    <toolbar-view>
      <botao-acao slot="actions" hideBorder @click="adicionarCobranca">
        <v-icon>add_circle</v-icon>
        Cadastrar
      </botao-acao>
      <botao-acao slot="actions-second" hideBorder @click="abrirModalSelecionarPaciente">
        <v-icon>mdi-magnify</v-icon>
        Selecionar Paciente
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
    <div v-if="pacienteSelecionado" class="primary">
      <div class="container-remover-paciente">
        <v-btn class="close__button" text @click="removerPacienteSelecionado" icon>
          <v-icon color="#fff">close</v-icon>
        </v-btn>
      </div>
      <div class="atributos-paciente">
      <div class="selecionado-label">
        <span style="font-size: 14px">Nome</span>
        <p>{{ pacienteSelecionado.nome }}</p>
      </div>
      <div class="selecionado-label" style="margin-top: 5px">
        <span style="font-size: 14px">CPF/CNPJ</span>
        <p>{{ pacienteSelecionado.cnpjCpf | formatarCpfCnpj }}</p>
      </div>
      </div>
    </div>
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
import PesquisaPacienteModal from '@/views/modais/PesquisaPacienteModal.vue'

export default {
    name: 'PacienteFichaCobrancas',
    components: {
        PesquisaPacienteModal, PacienteFichaCobrancaPesquisa, ItemDePesquisa, PesquisaAvancada, CobrancaListagemTabela
    },
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            idPaciente: null,
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30,
            pacienteSelecionado: null,
            orcamentoModal: false,
            modalAdicionarCobranca: false,
            modalEditarCobranca: false,
            modalSelecionarPaciente: false,
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
        },
        abrirModalSelecionarPaciente(){
            this.modalSelecionarPaciente = true
        },
        fecharModalSelecionarPaciente(){
            this.modalSelecionarPaciente = false
        },
        selecionarPaciente(paciente){
            this.fecharModalSelecionarPaciente()
            this.pacienteSelecionado = paciente
        },
        removerPacienteSelecionado(){
            this.pacienteSelecionado = null
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

.atributos-paciente
  height 150px
  width 100%
  display flex
  flex-direction column
  justify-content center
  align-items start
  padding 15px
  padding-top 0
  font-size 20px

.selecionado-label
  font-weight bold
  color white

.container-remover-paciente
  float right


</style>
