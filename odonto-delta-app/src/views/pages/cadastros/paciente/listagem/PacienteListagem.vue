<template>
    <div>
        <toolbar-view>
            <botao-acao slot="actions" hideBorder @click="tratarEventoCadastroPaciente">
                <v-icon>add_circle</v-icon>
                Cadastrar
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
                        simple-search-placeholder="Busque por: nome, cpf, cnpj ou telefone...">
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
                      <div>
                        <label>Ortodontia</label>
                        <v-checkbox v-model="ortodontiaSim" label="Sim" dense v-on:change="setarFiltroOrtodontia"/>
                        <v-checkbox v-model="ortodontiaNao" label="Não" dense v-on:change="setarFiltroOrtodontia"/>
                      </div>
                      <div class="container-municipio-estado">
                        <v-autocomplete
                            v-model="estadoSelecionado"
                            label="Estado"
                            :items="estados"
                            item-text="nome"
                            return-object
                            :filter="filtroComboAutoCompleteEstado"
                            v-on:change="buscarCidades"
                            class="select-estados"
                        />
                        <v-autocomplete
                            v-model="municipioSelecionado"
                            label="Cidade"
                            name="cidade"
                            placeholder="Selecione a cidade"
                            :items="cidades"
                            item-text="nome"
                            return-object
                            class="select-municipios"
                            :filter="filtroComboAutoComplete"
                            v-on:change="setFiltroCidade"
                        />
                      </div>
                    </item-de-pesquisa>
                </pesquisa-avancada>
            </v-flex>
        </toolbar-view>
        <paciente-listagem-tabela
                :itens="itens"
                :paginas="paginas"
                :total-itens="totalItens"
                :paginacao="$store.state.paciente.resultadoBuscaTodosPacientes.paginacao"
                @acessar="tratarEventoAcessar"
                @paginar="tratarEventoPaginar"
        />
    </div>
</template>

<script>
import _ from 'lodash'
import {mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PacienteListagemTabela from '@/views/pages/cadastros/paciente/listagem/PacienteListagemTabela'
import PesquisaAvancada from '@/views/components/PesquisaAvancada'
import ItemDePesquisa from '@/views/components/ItemDePesquisa'

export default {
    name: 'PacienteListagem',
    components: {ItemDePesquisa, PesquisaAvancada, PacienteListagemTabela},
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30,
            municipioSelecionado: {id: null},
            estadoSelecionado: {id: null},
            ortodontiaSim: null,
            ortodontiaNao: null,
            estados: [],
            cidades: [],
        }
    },
    async mounted(){
        await this.buscarEstados()
    },
    methods: {
        ...mapMutations([
            mutationTypes.CADASTROS.PACIENTE.SET_FILTROS_BUSCA_TODOS_PACIENTES,
            mutationTypes.CADASTROS.PACIENTE.SET_PAGINACAO_BUSCA_TODOS_PACIENTES,
            mutationTypes.CADASTROS.PACIENTE.RESETA_PAGE
        ]),
        async buscarEstados(){
            const resultado = await this.$store.dispatch(actionTypes.CADASTROS.ESTADO.BUSCAR_TODOS_ESTADOS_SEM_PAGINACAO)
            if (resultado) {
                this.estados = resultado.items
            }
        },
        async buscarCidades() {
            if(this.estadoSelecionado){
                this.filtrosInterno.estadoId.value = this.estadoSelecionado.nome
                this.filtrosInterno.estadoId.valueId = this.estadoSelecionado.id
                const resultado = await this.$store.dispatch(actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES_SEM_PAGINACAO, {idEstado: this.estadoSelecionado.id})
                if (resultado) {
                    this.cidades = resultado.items
                }
                const cidade = this.cidades.find( item => item.id === this.municipioSelecionado.id)
                if(!cidade)
                    this.municipioSelecionado = {id: null}
            }
        },
        setFiltroCidade(){
            this.filtrosInterno.municipioId.value = this.municipioSelecionado.nome
            this.filtrosInterno.municipioId.valueId = this.municipioSelecionado.id
        },
        setarFiltroOrtodontia(){
            if(this.ortodontiaSim && !this.ortodontiaNao){
                this.filtrosInterno.ortodontia.value = 'Faz ortodontia'
                this.filtrosInterno.ortodontia.valueId = true
            } else if(this.ortodontiaNao && !this.ortodontiaSim){
                this.filtrosInterno.ortodontia.value = 'Não faz ortodontia'
                this.filtrosInterno.ortodontia.valueId = false
            } else {
                this.filtrosInterno.ortodontia.value = this.filtrosInterno.ortodontia.default
                this.filtrosInterno.ortodontia.valueId = this.filtrosInterno.ortodontia.default
            }
        },
        tratarEventoCadastroPaciente() {
            this.$router.push({name: 'PacienteCadastro'})
        },
        async buscar() {
            this.setFiltrosBuscaTodosPacientes(this.getFiltrosInterno())
            await this.buscarTodosPacientes()
        },
        async buscarTodosPacientes() {
            const resultado = await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES)
            if (resultado) {
                console.log('---buscaTodosPacientes')
                console.log(resultado)
                this.itens = resultado.items
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.paciente.resultadoBuscaTodosPacientes.filtros)
        },
        getFiltrosInterno() {
            return _.cloneDeep(this.filtrosInterno)
        },
        tratarEventoAcessar(item) {
            const id = item.id
            this.$router.push({name: 'PacienteFicha', params: {id}})
        },
        tratarEventoBuscaSimples(busca) {
            this.resetaPage()
            this.filtrosInterno.tipo.value = busca.tipo
            this.filtrosInterno.atributo.value = busca.atributo
            this.filtrosInterno.conteudo.value = busca.conteudo
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
            this.filtrosInterno.ortodontia.value = this.filtrosInterno.ortodontia.default
            this.filtrosInterno.municipioId.value = this.filtrosInterno.municipioId.default
            this.filtrosInterno.municipioId.valueId = this.filtrosInterno.municipioId.default
            this.filtrosInterno.estadoId.value = this.filtrosInterno.estadoId.default
            this.filtrosInterno.estadoId.valueId = this.filtrosInterno.estadoId.default
            this.filtrosInterno.ortodontia.value = this.filtrosInterno.ortodontia.default
            this.filtrosInterno.ortodontia.valueId = this.filtrosInterno.ortodontia.default
            this.ortodontiaNao = false
            this.ortodontiaSim = false
            this.municipioSelecionado = {id: null}
            this.estadoSelecionado = {id: null}
            this.buscar()
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodosPacientes(paginacao)
            this.buscar()
        },
        tratarEventoRemoverFiltro(propriedade) {
            if (this.filtrosInterno[propriedade]) {
                this.filtrosInterno[propriedade].value = this.filtrosInterno[propriedade].default
            }
            this.buscar()
        },
        filtroComboAutoComplete(item, queryText) {
            const text = item.nome.toLowerCase()
            const searchText = queryText.toLowerCase()
            return text.indexOf(searchText) > -1
        },
        filtroComboAutoCompleteEstado(item, queryText) {
            const text = item.nome.toLowerCase()
            const sigla = item.sigla.toLowerCase()
            const searchText = queryText.toLowerCase()
            return text.indexOf(searchText) > -1 || sigla.indexOf(searchText) > -1
        },
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