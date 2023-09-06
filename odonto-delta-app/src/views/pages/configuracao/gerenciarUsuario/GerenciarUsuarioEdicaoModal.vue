<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Editar usuário {{ nome_usuario }}
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
                                <span class="section-label">Alterar senha</span>
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
    import _ from 'lodash'
    import {mapActions} from 'vuex'
    import {actionTypes} from '@/core/constants'
    import BotaoSalvar from '@/views/components/BotaoSalvar'
    import BotaoCancelar from '@/views/components/BotaoCancelar'

    export default {
        name: 'GerenciarUsuarioEdicaoModal',
        components: {BotaoCancelar, BotaoSalvar},
        props: {
            value: Boolean,
            item: Object
        },
        data() {
            return {
                dadosGerais: {},
                showPassword: false,
                showPassword2: false,
                nome_usuario: null,
                senhaDois: null
            }
        },
        mounted() {
            this.setarDadosGerais(this.item)
        },
        methods: {
            ...mapActions([
                actionTypes.USUARIO.EDITAR_USUARIO
            ]),
            setarDadosGerais(objeto) {
                this.dadosGerais = _.cloneDeep(objeto)
                this.dadosGerais.permissao = null
                this.senhaDois = this.dadosGerais.senha
                this.nome_usuario = this.dadosGerais.nome_completo
            },
            async tratarEventoEditar() {
                if (await this.validarDadosFormulario()) {
                    this.setMensagemLoading('Salvando alterações do usuário...')
                    const resultado = await this.editarUsuario(this.dadosGerais)
                    this.setarDadosGerais(resultado)
                    this.mostrarNotificacaoSucessoDefault()
                }
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
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

    .modal-usuario-edit-container
        padding 0px !important
        overflow-x hidden
        height auto

</style>