<template>
  <div>
    <confirm-modal
        v-model="modalExcluir"
        @modalAcaoCancelar="fecharModalExcluir"
        @modalAcaoConfirmar="tratarEventoExcluir"
        :persistencia="false"
        mensagemInicial="Deseja realmente excluir o perfil?"
    />
    <v-dialog v-model="value" max-width="720" scrollable persistent>
      <v-card>
        <v-toolbar dark color="primary" elevation="0">
          <v-toolbar-title>Editar perfil {{ nomeItem }}
          </v-toolbar-title>
          <v-spacer/>
          <v-btn class="close__button" text @click="fecharModal" icon>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pt-5">
          <v-row wrap align-center white class="pl-5 pr-5">
            <v-col cols="12" md="6" sm="6" xs="12">
              <v-text-field
                  v-model="dadosGerais.nome"
                  name="nome"
                  placeholder="Informe o nome"
                  counter="100"
                  maxlength="100"
                  v-validate="{required: true, min: 2, max: 100}"
                  :error-messages="errors.collect('nome')">
                <template v-slot:label>
                  Nome
                  <span class="ml-1 red--text">*</span>
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="12" md="6" sm="6" xs="12">
              <v-text-field
                  v-model="dadosGerais.descricao"
                  name="descricao"
                  placeholder="Informe a descrição"
                  counter="100"
                  maxlength="100"
                  v-validate="{required: true, min: 2, max: 100}"
                  :error-messages="errors.collect('descricao')">
                <template v-slot:label>
                  Descrição
                  <span class="ml-1 red--text">*</span>
                </template>
              </v-text-field>
            </v-col>
          </v-row>
          <v-row wrap align-center white class="pl-5 pr-5">
            <div class="header-permissao-crud">
              <div class="container-permissao-list">
                <div :key="permissao.id" v-for="(permissao, index) in permissoes">
                  <v-tooltip top>
                    <template v-slot:activator={on}>
                      <div class="permissao-item" v-on="on" :index="index">
                          <v-checkbox v-model="permissao.ativo" dark :label="permissao.nome" />
                      </div>
                    </template>
                        {{ permissao.descricao }}
                    </v-tooltip>
                </div>
              </div>
            </div>
          </v-row>
        </v-card-text>
        <v-container>
          <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
            <botao-cancelar @cancelar="fecharModal"/>
            <div>
              <botao-excluir @excluir="abrirModalExcluir" color="grey" v-if="itemId" class="mr-5"/>
              <botao-salvar @salvar="tratarEventoEditar"/>
            </div>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>
import _ from 'lodash'
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'
import BotaoExcluir from '@/views/components/BotaoExcluir.vue'

export default {
    name: 'GerenciarPerfilEdicaoModal',
    components: {BotaoExcluir, BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            dadosGerais: {},
            nomeItem: null,
            modalExcluir: false,
            itemId: null,
            permissoes: []
        }
    },
    async mounted() {
        this.setarDadosGerais(this.item)
        await this.buscarPermissoes()
    },
    methods: {
        ...mapActions([
            actionTypes.PERFIL.EDITAR_PERFIL,
            actionTypes.PERFIL.REMOVER_PERFIL,
            actionTypes.PERMISSAO.BUSCAR_TODAS_PERMISSOES
        ]),
        setarDadosGerais(objeto) {
            this.dadosGerais = _.cloneDeep(objeto)
            this.itemId = this.dadosGerais.id
            this.nomeItem = this.dadosGerais.nome
        },
        async tratarEventoEditar() {
            if (await this.validarDadosFormulario()) {
                this.setMensagemLoading('Salvando alterações do perfil...')
                const resultado = await this.editarPerfil(this.dadosGerais)
                this.setarDadosGerais(resultado)
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        async buscarPermissoes(){
            const resultado = await this.buscarTodasPermissoes()
            if(resultado){
                this.permissoes = resultado.items
                console.log('---permissoes')
                console.log(this.permissoes)
            }
        },
        abrirModalExcluir() {
            this.modalExcluir = true
        },
        fecharModalExcluir() {
            this.modalExcluir = false
        },
        async tratarEventoExcluir() {
            await this.removerPerfil(this.itemId)
            this.mostrarNotificacaoSucessoDefault()
            this.fecharModal()
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        async fecharModalPermissao(){
            this.modalPermissao = false
            await this.buscarPermissoes()
        },
        fecharModal() {
            this.$emit('fecharEdicao')
        },
    }
}
</script>

<style scoped lang="stylus">

    .row-bottom
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc
        box-sizing border-box
        padding-top 10px
        padding-bottom 10px
        display flex
        justify-content space-between

    .header-permissao-crud
        width 100%
        padding-top 10px
        margin-top 50px
        margin-bottom 50px
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc

    .container-permissao-list
        padding-top 10px

    .permissao-item
        background-color #487B9C
        padding 10px
        margin 10px
        border-radius 20px
        display flex
        align-items center
        justify-content space-between
        >>> label
              color #ffff
              font-size 16px
              font-weight bold
        >>> .v-input__control
              margin 0px !important
              height 30px
        >>> .v-input__slot
              margin 0px !important

</style>