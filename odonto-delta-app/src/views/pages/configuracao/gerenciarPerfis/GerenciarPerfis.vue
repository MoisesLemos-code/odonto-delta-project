<template>
  <div>
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
    <container-component class="altura-componente-container">
      <div class="perfis-itens-container">
        <div :key="item.id" v-for="item in itens">
              <h2>{{item.id}} - {{item.nome}}</h2>
        </div>
      </div>

    </container-component>
  </div>
</template>

<script>
    import _ from 'lodash'
    import {mapActions, mapMutations} from 'vuex'
    import {mutationTypes} from '@/core/constants'
    import PesquisaAvancada from '@/views/components/PesquisaAvancada.vue'
    import ContainerComponent from '@/views/components/Container.vue'

    export default {
        name: 'GerenciarPerfis',
        components: {ContainerComponent, PesquisaAvancada},
        data() {
            return {
                filtrosInterno: this.getFiltros(),
                itens: [],
                paginas: 0,
                totalItens: 0,
                maxInputPesquisa: 30,
                dados: {
                    content: [
                        {id: 1, nome: 'teste'},
                        {id: 2, nome: 'teste'},
                        {id: 3, nome: 'teste'},
                        {id: 4, nome: 'teste'},
                        {id: 5, nome: 'teste'},
                        {id: 6, nome: 'teste'},
                        {id: 7, nome: 'teste'},
                        {id: 8, nome: 'teste'},
                        {id: 9, nome: 'teste'},
                        {id: 10, nome: 'teste'},
                    ],
                    totalPages: 2,
                    totalElements: 20
                }
            }
        },
        async mounted() {
            await this.buscar()
        },
        methods: {
            ...mapActions([
            ]),
            ...mapMutations([
                mutationTypes.PERFIL.SET_FILTROS_BUSCA_TODOS_USUARIOS,
                mutationTypes.PERFIL.SET_PAGINACAO_BUSCA_TODOS_USUARIOS,
                mutationTypes.PERFIL.RESETA_PAGE
            ]),
            async buscar() {
                this.setFiltrosBuscaTodosPerfis(this.getFiltrosInterno())
                await this.buscarUsuarios()
            },
            async buscarUsuarios() {
                const resultado = this.dados
                if (resultado) {
                    this.itens = resultado.content
                    this.paginas = resultado.totalPages
                    this.totalItens = resultado.totalElements
                }
            },
            cadastrarPerfil(){

            },
            getFiltros() {
                return _.cloneDeep(this.$store.state.usuario.resultadoBuscaTodosUsuarios.filtros)
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

.perfis-itens-container
  padding 0px 10px

</style>