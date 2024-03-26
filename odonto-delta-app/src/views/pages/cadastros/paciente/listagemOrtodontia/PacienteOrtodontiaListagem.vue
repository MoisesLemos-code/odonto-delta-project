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
                        simple-search-placeholder="Busque por: nome, cpf, cnpj ou e-mail...">
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
        <paciente-ortodontia-listagem-tabela
                :itens="itens"
                :paginas="paginas"
                :total-itens="totalItens"
                :paginacao="$store.state.paciente.resultadoBuscaTodosPacientesOrtodontia.paginacao"
                @acessar="tratarEventoAcessar"
                @paginar="tratarEventoPaginar"
        />
    </div>
</template>

<script>
import _ from 'lodash'
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import PesquisaAvancada from '@/views/components/PesquisaAvancada'
import ItemDePesquisa from '@/views/components/ItemDePesquisa'
import PacienteOrtodontiaListagemTabela from './PacienteOrtodontiaListagemTabela'

export default {
    name: 'PacienteListagem',
    components: {
        PacienteOrtodontiaListagemTabela, ItemDePesquisa, PesquisaAvancada },
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30
        }
    },
    methods: {
        ...mapActions([actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_ORTODONTIA]),
        ...mapMutations([
            mutationTypes.CADASTROS.PACIENTE.SET_FILTROS_BUSCA_TODOS_PACIENTES_ORTODONTIA,
            mutationTypes.CADASTROS.PACIENTE.SET_PAGINACAO_BUSCA_TODOS_PACIENTES_ORTODONTIA,
            mutationTypes.CADASTROS.PACIENTE.RESETA_PAGE_ORTODONTIA
        ]),
        tratarEventoCadastroPaciente() {
            this.$router.push({name: 'PacienteCadastro'})
        },
        async buscar() {
            this.setFiltrosBuscaTodosPacientesOrtodontia(this.getFiltrosInterno())
            await this.buscaTodosPacientes()
        },
        async buscaTodosPacientes() {
            const resultado = await this.buscarTodosPacientesOrtodontia()
            if (resultado) {
                this.itens = resultado.content
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.paciente.resultadoBuscaTodosPacientesOrtodontia.filtros)
        },
        getFiltrosInterno() {
            return _.cloneDeep(this.filtrosInterno)
        },
        tratarEventoAcessar(item) {
            const id = item.id
            this.$router.push({name: 'PacienteFicha', params: {id}})
        },
        tratarEventoBuscaSimples(busca) {
            this.resetaPageOrtodontia()
            this.filtrosInterno.tipo.value = busca.tipo
            this.filtrosInterno.atributo.value = busca.atributo
            this.filtrosInterno.conteudo.value = busca.conteudo
            this.buscar()
        },
        tratarEventoBuscaAvancada() {
            this.resetaPageOrtodontia()
            this.buscar()
        },
        tratarEventoLimparBuscaAvancada() {
            this.filtrosInterno.tipo.value = this.filtrosInterno.tipo.default
            this.filtrosInterno.atributo.value = this.filtrosInterno.atributo.default
            this.filtrosInterno.conteudo.value = this.filtrosInterno.conteudo.default
            this.buscar()
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodosPacientesOrtodontia(paginacao)
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