<template>
  <div>
    <confirm-modal
        v-model="modalExcluir"
        @modalAcaoCancelar="fecharModalExcluir"
        @modalAcaoConfirmar="tratarEventoExcluir"
        :persistencia="false"
        mensagemInicial="Deseja realmente excluir a permissão?"
    />
    <v-dialog v-model="value" max-width="720" scrollable persistent>
      <v-card>
        <v-toolbar dark color="primary" elevation="0">
          <v-toolbar-title>{{ tituloItem }}
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
        </v-card-text>
        <v-container>
          <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
            <botao-cancelar @cancelar="fecharModal"/>
            <div>
              <botao-excluir @excluir="abrirModalExcluir" color="grey" v-if="itemId" class="mr-5"/>
              <botao-salvar @salvar="tratarEventoConfirmar"/>
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
    name: 'PermissaoModal',
    components: {BotaoExcluir, BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object,
        idPerfil: Number,
        isCadastro: Boolean
    },
    data() {
        return {
            dadosGerais: {
                id: null,
                nome: null,
                descricao: null,
                perfilId: null
            },
            modalExcluir: false,
            tituloItem: '',
            itemId: null,
            permissoes: [],
        }
    },
    async mounted() {
        this.setarDadosGerais(this.item)
    },
    methods: {
        ...mapActions([
            actionTypes.PERFIL_PERMISSAO.CADASTRAR_PERMISSAO,
            actionTypes.PERFIL_PERMISSAO.EDITAR_PERMISSAO,
            actionTypes.PERFIL_PERMISSAO.REMOVER_PERMISSAO,
        ]),
        setarDadosGerais(objeto) {
            if(this.isCadastro){
                this.tituloItem = 'Cadastrar Permissão'
                this.dadosGerais.perfilId = this.idPerfil
            } else{
                this.dadosGerais = _.cloneDeep(objeto)
                this.itemId = this.dadosGerais.id
                this.tituloItem = 'Atualizar Permissão ' + this.dadosGerais.nome
            }
        },
        async tratarEventoConfirmar(){
            if(this.isCadastro){
                await this.tratarEventoSalvar()
            }else{
                await this.tratarEventoEditar()
            }
        },
        async tratarEventoSalvar() {
            if (await this.validarDadosFormulario()) {
                this.setMensagemLoading('Salvando a permissão...')
                await this.cadastrarPermissao(this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
                this.fecharModal()
            }
        },
        async tratarEventoEditar() {
            if (await this.validarDadosFormulario()) {
                this.setMensagemLoading('Salvando alterações da permissão...')
                const resultado = await this.editarPermissao(this.dadosGerais)
                this.setarDadosGerais(resultado)
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        abrirModalExcluir() {
            this.modalExcluir = true
        },
        fecharModalExcluir() {
            this.modalExcluir = false
        },
        async tratarEventoExcluir() {
            await this.removerPermissao(this.itemId)
            this.mostrarNotificacaoSucessoDefault()
            this.fecharModal()
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        fecharModal() {
            this.$emit('fecharModalPermissao')
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

</style>