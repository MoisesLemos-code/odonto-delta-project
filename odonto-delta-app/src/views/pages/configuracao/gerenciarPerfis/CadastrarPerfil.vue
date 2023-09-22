<template>
  <v-form class="form-user form-content altura-componente-container mt-0 pl-0 pr-0">
    <v-container fluid grid-list-xl class="pt-0">
      <v-row justify="center">
          <v-card class="card-perfil-container">
            <v-row justify="center">
            <v-card-title>Cadastrar Perfil</v-card-title>
            </v-row>
              <v-card-text>
                <v-row wrap align-center white class="pl-5 pr-5">
                  <v-col cols="12" md="6" sm="6" xs="12">
                    <v-text-field
                        v-model="dadosGerais.nome"
                        name="nome"
                        placeholder="Informe o nome de usuário"
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
              <v-card-actions>
                <v-btn
                    color="primary"
                    depressed
                    block
                    @click="tratarEventoSalvar">
                  Cadastrar
                </v-btn>
              </v-card-actions>
          </v-card>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'CadastrarPerfil',
    data() {
        return {
            dadosGerais: {
                nome: null,
                descricao: null,
            }
        }
    },
    methods:{
        ...mapActions([
            actionTypes.USUARIO.CADASTRAR_USUARIO
        ]),
        async tratarEventoSalvar() {
            if (await this.validarDadosFormulario()) {
                this.setMensagemLoading('Salvando o usuário...')
                await this.cadastrarUsuario(this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
                this.$router.push({name: 'GerenciarUsuarios'})
            }
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
    }
}
</script>

<style scoped lang="stylus">

  .form-user
    background-color #EEEEEE;

  .card-perfil-container
    margin-top 20px

  .row-bottom
    border-top-width 1px
    border-top-style solid
    border-top-color #ccc
    box-sizing border-box
    margin-top 5%
    padding-top 10px
    display flex
    justify-content space-between

</style>