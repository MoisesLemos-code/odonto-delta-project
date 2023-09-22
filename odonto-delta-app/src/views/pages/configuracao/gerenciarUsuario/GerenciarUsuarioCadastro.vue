<template>
  <v-form class="form-user form-content altura-componente-container mt-0 pl-0 pr-0">
    <v-container fluid grid-list-xl class="pt-0">
      <v-row justify="center">
          <v-card class="card-usuario-container">
            <v-row justify="center">
            <v-card-title>Cadastrar usuário
              <span style="color: #487B9C" class="ml-2"> {{ nome_usuario }}</span></v-card-title>
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
                        v-model="dadosGerais.nome_completo"
                        name="nome_completo"
                        placeholder="Informe o nome completo"
                        counter="100"
                        maxlength="100"
                        v-validate="{required: true, min: 2, max: 100}"
                        :error-messages="errors.collect('nome_completo')">
                      <template v-slot:label>
                        Nome Completo
                        <span class="ml-1 red--text">*</span>
                      </template>
                    </v-text-field>
                  </v-col>
                </v-row>
                <v-row wrap align-center white class="pl-5 pr-5">
                  <v-col cols="12" md="12" sm="12" xs="12">
                    <v-text-field
                        v-model="dadosGerais.email"
                        name="email"
                        placeholder="Informe o e-mail"
                        counter="100"
                        maxlength="100"
                        v-validate="{required: true, max: 100, email: true}"
                        :error-messages="errors.collect('email')">
                      <template v-slot:label>
                        Email
                        <span class="ml-1 red--text">*</span>
                      </template>
                    </v-text-field>
                  </v-col>
                </v-row>
                <v-row wrap align-center white class="pl-5 pr-5">
                  <v-col cols="12" md="12" sm="12" xs="12">
                    <v-divider :inset="false" class="divider"></v-divider>
                    <span class="section-label">Senha</span>
                  </v-col>
                  <v-col cols="12" md="6" sm="6" xs="12">
                    <v-text-field
                        v-model="dadosGerais.senha"
                        ref="senha"
                        data-vv-name="pass"
                        counter="20"
                        maxlength="20"
                        prepend-inner-icon="mdi-lock"
                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                        :type="showPassword ? 'text' : 'password'"
                        @click:append="showPassword = !showPassword"
                        v-validate="{required: true, min: 4, max: 20}"
                        :error-messages="errors.collect('pass')"
                    >
                      <template v-slot:message>
                        Deve possuir entre 4 a 20 caracteres
                      </template>
                    </v-text-field>
                  </v-col>
                  <v-col cols="12" md="6" sm="6" xs="12">
                    <v-text-field
                        v-model="senhaDois"
                        data-vv-name="senha2"
                        counter="20"
                        maxlength="20"
                        :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
                        :type="showPassword2 ? 'text' : 'password'"
                        @click:append="showPassword2 = !showPassword2"
                        :error-messages="errors.collect('senha2')"
                        v-validate="{required: true, min: 4, max: 20, confirmed: 'senha'}"
                    >
                      <template v-slot:message>
                        As senhas não se coincidem
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
    name: 'CadastrarUsuario',
    data() {
        return {
            showPassword: false,
            showPassword2: false,
            dadosGerais: {
                nome: null,
                nome_completo: null,
                email: null,
                senha: null,
            },
            nome_usuario: null,
            senhaDois: null
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

  .card-usuario-container
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