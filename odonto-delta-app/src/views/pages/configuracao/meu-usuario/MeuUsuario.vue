<template>
  <div class="form-user form-content altura-componente-container mt-0 pl-0 pr-0">
  <v-form >
    <v-container fluid grid-list-xl class="pt-0">
      <v-row justify="center">
        <v-card class="card-usuario-container">
          <v-row justify="center">
            <v-card-title>Alterar usuário
              <span style="color: #487B9C" class="ml-2"> {{ nome_usuario }}</span></v-card-title>
          </v-row>
          <v-card-text>
            <v-row wrap align-center white class="pl-5 pr-5">
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.login"
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
                    v-model="dadosGerais.nome"
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
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.cargo"
                    name="cargo"
                    placeholder="Informe o cargo do usuário"
                    counter="100"
                    maxlength="100"
                    v-validate="{min: 2, max: 100}"
                    :error-messages="errors.collect('cargo')">
                  <template v-slot:label>Cargo</template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.cnpjCpf"
                    name="cnpjCpf"
                    placeholder="Informe o documento"
                    v-mask="[masks.cpf, masks.cnpj]"
                    v-validate="{required: true, min: 2, max: 100}"
                    :error-messages="errors.collect('cnpjCpf')">
                  <template v-slot:label>
                    Documento (CPF/CNPJ)
                    <span class="ml-1 red--text">*</span>
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.telefone"
                    name="telefone"
                    v-mask="[masks.tel, masks.tel2]"
                    placeholder="Informe o telefone"
                    :error-messages="errors.collect('telefone')">
                  <template v-slot:label>
                    Telefone
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.departamento"
                    name="departamento"
                    placeholder="Informe o departamento"
                    :error-messages="errors.collect('departamento')">
                  <template v-slot:label>
                    Departamento
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-5 pr-5">
              <v-col cols="12" md="12" sm="12" xs="12">
                <v-divider :inset="false" class="divider"></v-divider>
                <span class="section-label">Endereço</span>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.cep"
                    name="cep"
                    placeholder="Informe o CEP"
                    v-mask="masks.cep"
                    :error-messages="errors.collect('cep')">
                  <template v-slot:label>
                    CEP
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                  <buscar-municipio :municipio-id="dadosGerais.municipio.id"
                                    :estado-id="dadosGerais.municipio.estado.id"
                                    @emitirSelecionarCidade="selecionarCidade"
                  />
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.logradouro"
                    name="logradouro"
                    placeholder="Informe o logradouro"
                    :error-messages="errors.collect('logradouro')">
                  <template v-slot:label>
                    Logradouro
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.logradouroNumero"
                    name="numero"
                    placeholder="Informe o número"
                    :error-messages="errors.collect('numero')">
                  <template v-slot:label>
                    Número
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.complemento"
                    name="complemento"
                    placeholder="Informe o complemento"
                    :error-messages="errors.collect('complemento')">
                  <template v-slot:label>
                    Complemento
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-5 pr-5">
              <v-col cols="12" md="12" sm="12" xs="12">
                <v-divider :inset="false" class="divider"></v-divider>
                <span class="section-label">Senha atual</span>
              </v-col>
              <v-col cols="12" md="6" sm="6" xs="12">
                <v-text-field
                    v-model="dadosGerais.senhaAtual"
                    name="senhaAtual"
                    prepend-inner-icon="mdi-lock"
                    :append-icon="showPasswordAtual ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPasswordAtual ? 'text' : 'password'"
                    @click:append="showPasswordAtual = !showPasswordAtual"
                    v-validate="{ required: true}"
                    :error-messages="errors.collect('senhaAtual')"
                />
              </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-5 pr-5">
              <v-col cols="12" md="12" sm="12" xs="12">
                <v-divider :inset="false" class="divider"></v-divider>
                <span class="section-label">Nova senha</span>
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
                      v-validate="{ min: 4, max: 20}"
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
                    v-validate="{ min: 4, max: 20, confirmed: 'senha'}"
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
              Salvar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
    </v-container>
  </v-form>
  </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import {actionTypes} from '@/core/constants'
    import BuscarMunicipio from '@/views/components/BuscarMunicipio.vue'

    export default {
        name: 'MeuUsuario',
        components: {BuscarMunicipio},
        data() {
            return {
                showPasswordAtual: false,
                showPassword: false,
                showPassword2: false,
                dadosGerais: {
                    nome: null,
                    nome_completo: null,
                    email: null,
                    senha: null,
                    municipio: {
                        id: null
                    },
                    empresa: {
                        id: null
                    },
                },
                municipioId: null,
                nome_usuario: null,
                senhaDois: null,
                masks: {
                    cep: '##.###-###',
                    tel: '(##) ####-####',
                    tel2: '(##) #####-####',
                    cpf: '###.###.###-##',
                    cnpj: '##.###.###/####-##',
                }
            }
        },
        async mounted() {
            await this.atualizarRegistroUsuario()
            await this.buscarUsuarioLogado()
        },
        methods: {
            ...mapActions([
                actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID,
                actionTypes.USUARIO.EDITAR_USUARIO,
                actionTypes.COMUM.VERIFICAR_TOKEN
            ]),
            ...mapGetters([
                'getUsuarioLogado'
            ]),
            async atualizarRegistroUsuario() {
                await this.verificarToken()
            },
            async buscarUsuarioLogado() {
                const {id} = this.getUsuarioLogado()
                this.dadosGerais = await this.buscarUsuarioPorId(id)
                this.dadosGerais.permissao = null
                this.nome_usuario = this.dadosGerais.nome
                this.municipioId = this.dadosGerais.municipio.id
            },
            async tratarEventoSalvar() {
                if (await this.validarDadosFormulario()) {
                    if(!this.senhaDois){
                        this.mostrarNotificacaoErro('Informe a senha nova!')
                        return
                    }
                    if(!this.municipioId){
                        this.mostrarNotificacaoErro('Informe o município')
                        return
                    }
                    this.formatarInput()
                    this.setMensagemLoading('Salvando alterações do usuário...')
                    console.log('---tratarEventoSalvar')
                    console.log(this.dadosGerais)
                    const data = await this.editarUsuario(this.dadosGerais)
                    this.nome_usuario = data.nome
                    await this.atualizarRegistroUsuario()
                    this.mostrarNotificacaoSucessoDefault()
                }
            },
            selecionarCidade(municipioId){
                console.log('---selecionarCidade')
                console.log(municipioId)
                this.municipioId = municipioId
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
            },
            formatarInput() {
                this.dadosGerais = {
                    ...this.dadosGerais,
                    empresaId: this.dadosGerais.empresa.id,
                    municipioId: this.municipioId
                }
            },
        }
    }
</script>

<style scoped lang="stylus">

.form-user
  background-color #EEEEEE;
  display flex
  justify-content center
  align-items center
  height 100%


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