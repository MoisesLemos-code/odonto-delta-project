<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Editar usuário {{ this.item.nome }}
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-usuario-edit-container">
                <v-form class="form-content mt-0 pl-0 pr-0">
                    <v-container fluid grid-list-xl white>
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
                          <buscar-municipio
                                            v-if="carregarCidadesEstados"
                                            :municipio-id="dadosGerais.municipio.id"
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
                      <v-expansion-panels>
                      <v-expansion-panel>
                        <v-expansion-panel-header>
                         <div> <v-icon class="mr-1">vpn_key</v-icon><label class="trocar-senha-titulo">Trocar Senha</label></div>
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
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
                                  v-validate="{min: 4, max: 20}"
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
                                  v-validate="{min: 4, max: 20, confirmed: 'senha'}"
                              >
                                <template v-slot:message>
                                  As senhas não se coincidem
                                </template>
                              </v-text-field>
                            </v-col>
                          </v-row>
                        </v-expansion-panel-content>
                      </v-expansion-panel>
                    </v-expansion-panels>
                    </v-container>
                </v-form>
            </v-card-text>
            <v-container>
                <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
                    <botao-cancelar @cancelar="fecharModal"/>

                    <botao-salvar @salvar="tratarEventoEditar"/>
                </v-row>
            </v-container>
        </v-card>
    </v-dialog>
</template>

<script>
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'
import BuscarMunicipio from '../../../components/BuscarMunicipio.vue'

export default {
    name: 'GerenciarUsuarioEdicaoModal',
    components: {BuscarMunicipio, BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            showPasswordAtual: false,
            showPassword: false,
            showPassword2: false,
            carregarCidadesEstados: false,
            dadosGerais: {
                nome: null,
                login: null,
                email: null,
                senha: null,
                senhaAtual: null,
                municipio: {
                    id: null,
                    estado: {
                        id: null
                    }
                },
                empresa: {
                    id: null
                },
            },
            municipioId: null,
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
        await this.buscarUsuario()
    },
    methods: {
        async buscarUsuario(){
            const resultado =  await this.$store.dispatch(actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID, this.item.id)
            if(resultado){
                this.dadosGerais = resultado
                this.municipioId = resultado.municipio.id
                this.carregarCidadesEstados = true
            }
        },
        async tratarEventoEditar() {
            if (await this.validarDadosFormulario()) {
                if(this.dadosGerais.senhaAtual && !this.senhaDois){
                    this.mostrarNotificacaoErro('Digite a senha de confirmação!')
                    return
                }
                if(!this.municipioId){
                    this.mostrarNotificacaoErro('Informe o município')
                    return
                }
                this.formatarInput()
                this.setMensagemLoading('Salvando alterações do usuário...')
                await this.$store.dispatch(actionTypes.USUARIO.EDITAR_USUARIO, this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        fecharModal() {
            this.$emit('fecharEdicao')
        },
        selecionarCidade(municipioId){
            this.municipioId = municipioId
        },
        formatarInput() {
            this.dadosGerais = {
                ...this.dadosGerais,
                municipioId: this.municipioId
            }
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

    .modal-usuario-edit-container
        padding 0px !important
        overflow-x hidden
        height auto

    .trocar-senha-titulo
        font-weight bold
        color #666

</style>