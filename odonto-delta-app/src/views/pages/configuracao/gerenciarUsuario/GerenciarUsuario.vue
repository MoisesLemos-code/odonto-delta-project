<template>
  <div>
    <gerenciar-usuario-edicao-modal
        v-if="modalEdicao"
        v-model="modalEdicao"
        :item="usuarioSelecionado"
        @fecharEdicao="fecharModalEdicao"
    />
    <gerenciar-usuario-permissao-modal
        v-if="modalPermissao"
        v-model="modalPermissao"
        :item="usuarioSelecionado"
        @fecharEdicaoPermissao="fecharModalEdicaoPermissao"
    />
    <toolbar-view>
      <botao-acao slot="actions" hideBorder @click="cadastrarUsuario">
        <v-icon>add_circle</v-icon>
        Cadastrar
      </botao-acao>
      <v-flex slot="simpleSearch">
        <pesquisa-avancada
            :filter="filtrosInterno"
            @remove-filter="tratarEventoRemoverFiltro"
            @simple-search="tratarEventoBuscaSimples"
            :maxlengthInput="maxInputPesquisa"
            simple-search-placeholder="Busque por: nome ou e-mail..."/>
      </v-flex>
    </toolbar-view>
    <container-component class="altura-componente-container">
      <div class="usuarios-itens-container">
        <div :key="usuario.id" v-for="(usuario, index) in itens">
          <gerenciar-usuario-item
              v-model="itens[index]"
              :index="index"
              @editarUsuario="tratarEventoEditar"
              @permissaoUsuario="tratarEventoEditarPermissoes"/>
        </div>
      </div>

    </container-component>
    <gerenciar-usuario-listagem
        :paginas="paginas"
        :total-itens="totalItens"
        :paginacao="$store.state.usuario.resultadoBuscaTodosUsuarios.paginacao"
        @paginar="tratarEventoPaginar"/>
  </div>
</template>

<script>
import _ from 'lodash'
import {mapActions, mapMutations} from 'vuex'
import {actionTypes, mutationTypes} from '@/core/constants'
import GerenciarUsuarioListagem from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuarioListagem'
import PesquisaAvancada from '@/views/components/PesquisaAvancada'
import GerenciarUsuarioItem from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuarioItem'
import ContainerComponent from '@/views/components/Container'
import GerenciarUsuarioEdicaoModal from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuarioEdicaoModal'
import GerenciarUsuarioPermissaoModal from '@/views/pages/configuracao/gerenciarUsuario/GerenciarUsuarioPermissaoModal'

export default {
    name: 'GerenciarUsuario',
    components: {
        GerenciarUsuarioPermissaoModal,
        GerenciarUsuarioEdicaoModal,
        ContainerComponent, GerenciarUsuarioItem, GerenciarUsuarioListagem, PesquisaAvancada
    },
    data() {
        return {
            filtrosInterno: this.getFiltros(),
            itens: [],
            paginas: 0,
            totalItens: 0,
            maxInputPesquisa: 30,
            usuarioSelecionado: {},
            modalEdicao: false,
            modalPermissao: false
        }
    },
    async mounted() {
        await this.buscar()
    },
    methods: {
        ...mapActions([
            actionTypes.USUARIO.BUSCAR_TODOS_USUARIOS,
            actionTypes.USUARIO.EDITAR_USUARIO,
        ]),
        ...mapMutations([
            mutationTypes.USUARIO.SET_FILTROS_BUSCA_TODOS_USUARIOS,
            mutationTypes.USUARIO.SET_PAGINACAO_BUSCA_TODOS_USUARIOS,
            mutationTypes.USUARIO.RESETA_PAGE
        ]),
        async buscarUsuarios() {
            const resultado = await this.buscarTodosUsuarios()
            if (resultado) {
                this.itens = resultado.content
                this.paginas = resultado.totalPages
                this.totalItens = resultado.totalElements
            }
        },
        async buscar() {
            this.setFiltrosBuscaTodosUsuarios(this.getFiltrosInterno())
            await this.buscarUsuarios()
        },
        cadastrarUsuario() {
            this.$router.push({name: 'CadastrarUsuario'})
        },
        tratarEventoEditar(item) {
            this.usuarioSelecionado = item
            this.modalEdicao = true
        },
        tratarEventoEditarPermissoes(item) {
            this.usuarioSelecionado = item
            this.modalPermissao = true
        },
        tratarEventoPaginar(paginacao) {
            this.setPaginacaoBuscaTodosUsuarios(paginacao)
            this.buscar()
        },
        getFiltros() {
            return _.cloneDeep(this.$store.state.usuario.resultadoBuscaTodosUsuarios.filtros)
        },
        getFiltrosInterno() {
            return _.cloneDeep(this.filtrosInterno)
        },
        fecharModalEdicao() {
            this.modalEdicao = false
            this.usuarioSelecionado = null
            this.buscar()
        },
        fecharModalEdicaoPermissao() {
            this.modalPermissao = false
            this.usuarioSelecionado = null
            this.buscar()
        },
        tratarEventoBuscaSimples(valor) {
            this.resetaPage()
            this.filtrosInterno.conteudo.value = valor
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

.usuarios-itens-container
  padding 0px 10px

</style>