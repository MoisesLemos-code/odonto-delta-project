<template>
  <v-dialog v-model="value" max-width="720" scrollable persistent>
    <v-card>
      <v-toolbar dark color="primary" elevation="0">
        <v-toolbar-title>Permissões do usuário {{ this.item.nome }}
        </v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="fecharModal" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text class="modal-permissao-container">
        <v-form class="form-content mt-0 pl-0 pr-0">
          <v-container fluid grid-list-xl white>
            <v-expansion-panels>
              <v-expansion-panel :key="perfil.idPerfil" v-for="perfil in perfis">
                <v-expansion-panel-header style="padding-left: 8px">
                  <div class="perfil-container">
                    <v-checkbox v-model="perfil.ativo" class="perfil-checkbox" hide-details />
                    <div class="perfil-item">
                      <span class="perfil-nome">{{ perfil.nome }}</span>
                      <span class="perfil-descricao">{{ perfil.descricao }}</span>
                    </div>
                  </div>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <div :key="permissao.idPermissao" v-for="permissao in perfil.permissoes">
                      <div class="permissao-item">
                        <v-icon dense color="#4caf50" class="mr-1" v-if="permissao.ativo">check</v-icon>
                        <v-icon dense color="#f44336" class="mr-1" v-if="!permissao.ativo">close</v-icon>
                        <div>
                          <span class="permissao-nome">{{permissao.nome}}</span><span class="permissao-descricao"> - {{permissao.descricao}}</span>
                        </div>
                      </div>
                  </div>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-container>
        </v-form>
      </v-card-text>
      <v-container>
        <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
          <botao-cancelar @cancelar="fecharModal"/>

          <botao-salvar @salvar="tratarEventoEditar" v-if="possuiPermissao"/>
          <v-tooltip
              v-else
              top
              nudge-top="30"
              max-width="200">
            <template v-slot:activator="{ on }">
              <div v-on="on" class="btn-disabled">
                <botao-salvar disabled/>
              </div>
            </template>
            Você não possui permissão para salvar este formulário.
          </v-tooltip>
        </v-row>
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'


export default {
    name: 'GerenciarUsuarioPermissaoModal',
    components: {BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            perfis: {},
        }
    },
    computed: {
        possuiPermissao() {
            return true
        }
    },
    async mounted() {
        await this.buscarPerfisUsuario()
    },
    methods: {
        async buscarPerfisUsuario() {
            const resultado = await this.$store.dispatch(actionTypes.PERFIL_USUARIO.BUSCAR_PERFIS_DO_USUARIO, this.item.id)
            if (resultado) {
                this.perfis = resultado.items
            }
        },
        async tratarEventoEditar() {
            if (this.possuiPermissao) {
                this.setMensagemLoading('Salvando alterações do usuário...')
                const input = {
                    usuarioId: this.item.id,
                    items: this.perfis
                }
                await this.$store.dispatch(actionTypes.PERFIL_USUARIO.ATUALIZAR_PERFIS_DO_USUARIO, input)
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        fecharModal() {
            this.$emit('fecharEdicaoPermissao')
        }
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

.modal-permissao-container
  overflow-x hidden
  height auto

.perfil-container
  display flex
  flex-direction row
  align-items center

.perfil-checkbox
  margin-right 2px

.perfil-item
  display flex
  flex-direction column

.perfil-nome
  font-weight bold
  font-size 18px
  color #777

.perfil-descricao
  margin-top 2px
  color #979696

.permissao-item
  background-color #487b9c
  margin-bottom 5px
  padding 5px
  border-radius 5px
  display flex
  flex-direction row
  align-items center

.permissao-nome
  font-weight bold
  font-size 15px
  color #FFF

.permissao-descricao
  color #FFF

</style>