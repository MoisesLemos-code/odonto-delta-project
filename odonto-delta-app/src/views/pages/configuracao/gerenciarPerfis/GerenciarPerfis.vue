<template>
  <div>
    <gerenciar-perfil-edicao-modal
        v-if="modalEdicao"
        v-model="modalEdicao"
        :item="itemSelecionado"
        @fecharEdicao="fecharModalEdicao"
    />
    <toolbar-view>
      <botao-acao slot="actions" hideBorder @click="cadastrarPerfil">
        <v-icon>add_circle</v-icon>
        Novo Perfil
      </botao-acao>
      <v-flex slot="simpleSearch">
        <pesquisa-avancada
            :filter="filtrosInterno"
            @remove-filter="tratarEventoRemoverFiltro"
            @simple-search="tratarEventoBuscaSimples"
            :maxlengthInput="maxInputPesquisa"
            simple-search-placeholder="Busque por: nome ou descrição..."/>
      </v-flex>
    </toolbar-view>
    <container-component class="altura-componente-container container-perfil">
        <v-row>
            <v-col cols="12" xs="12" sm="6" md="4" lg="2" :key="item.id" v-for="(item, index) in itens" >
              <gerenciar-perfil-card
                  v-model="itens[index]"
                  :index="index"
                  @editarItem="tratarEventoEditarPerfil"
                />
            </v-col>
        </v-row>

    </container-component>
  </div>
</template>

<script>
import _ from 'lodash'
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PesquisaAvancada from '@/views/components/PesquisaAvancada.vue'
import ContainerComponent from '@/views/components/Container.vue'
import GerenciarPerfilCard from '@/views/pages/configuracao/gerenciarPerfis/GerenciarPerfilCard.vue'
import GerenciarPerfilEdicaoModal from '@/views/pages/configuracao/gerenciarPerfis/GerenciarPerfilEdicaoModal.vue'

export default {
    name: 'GerenciarPerfis',
    components: {
        GerenciarPerfilEdicaoModal, GerenciarPerfilCard, ContainerComponent, PesquisaAvancada},
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30,
            itemSelecionado: null,
            modalEdicao: false
        }
    },
    async mounted() {
        await this.buscar()
    },
    methods: {
        ...mapActions([
            actionTypes.PERFIL.BUSCAR_TODOS_PERFIS
        ]),
        ...mapMutations([
            mutationTypes.PERFIL.SET_FILTROS_BUSCA_TODOS_PERFIS,
            mutationTypes.PERFIL.SET_PAGINACAO_BUSCA_TODOS_PERFIS,
            mutationTypes.PERFIL.RESETA_PAGE
        ]),
        async buscar() {
            this.setFiltrosBuscaTodosPerfis(this.getFiltrosInterno())
            await this.buscarPerfis()
        },
        async buscarPerfis() {
            const resultado = await this.buscarTodosPerfis()
            if (resultado) {
                this.itens = resultado.items
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        cadastrarPerfil() {
            this.$router.push({name: 'CadastrarPerfil'})
        },
        tratarEventoEditarPerfil(perfil){
            this.itemSelecionado = perfil
            this.modalEdicao = true
        },
        fecharModalEdicao() {
            this.modalEdicao = false
            this.itemSelecionado = null
            this.buscar()
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.perfil.resultadoBuscaTodosPerfis.filtros)
        },
        getFiltrosInterno() {
            return _.cloneDeep(this.filtrosInterno)
        },
        tratarEventoRemoverFiltro(propriedade) {
            if (this.filtrosInterno[propriedade]) {
                this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
            }
            this.buscar()
        },
        tratarEventoBuscaSimples(valor) {
            this.resetaPage()
            this.filtrosInterno.conteudo.value = valor
            this.buscar()
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodosPerfis(paginacao)
            this.buscar()
        },
    }
}
</script>

<style scoped lang="stylus">

.container-perfil
  width 100%
  padding 15px
  margin 0px

.max-width-item
  max-width 20vw

.max-width-item-descricao
  max-width 25vw


</style>