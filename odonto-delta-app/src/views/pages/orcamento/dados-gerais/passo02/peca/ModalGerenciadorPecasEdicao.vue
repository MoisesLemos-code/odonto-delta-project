<template>
    <div>
        <confirm-modal
                v-if="modalExcluir"
                v-model="modalExcluir"
                @modalAcaoCancelar="fecharModalExcluir"
                @modalAcaoConfirmar="tratarEventoExcluir"
                :persistencia="false"
                mensagemInicial="Deseja realmente excluir a peça "
                :mensagemMeio="item.descricao"
                mensagemFinal="?"/>
        <v-dialog v-model="value" max-width="700" scrollable persistent>
            <v-card elevation="4" tile>
                <v-toolbar dark color="primary" elevation="0">
                    <v-toolbar-title>Editando a peça <b>{{item.descricao}}</b></v-toolbar-title>
                    <v-spacer/>
                    <v-btn class="close__button" text @click="tratarEventoCancelar" icon>
                        <v-icon>close</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-form class="form-content mt-0 pl-0 pr-0">
                    <v-container fluid grid-list-xl white>
                        <v-row wrap align-center white class="pl-10 pr-10">
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
                            <v-col cols="12" md="6" sm="6" xs="12">
                                <v-text-field
                                        v-model="dadosGerais.valor"
                                        name="valor"
                                        type="number"
                                        placeholder="Informe o valor"
                                        v-validate="{required: true, decimal: true}"
                                        :error-messages="errors.collect('valor')">
                                    <template v-slot:label>
                                        Valor
                                        <span class="ml-1 red--text">*</span>
                                    </template>
                                </v-text-field>
                            </v-col>
                        </v-row>
                        <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
                            <botao-cancelar @cancelar="tratarEventoCancelar"/>

                            <div>
                                <botao-excluir @excluir="abrirModalExcluir" color="grey" class="mr-5"/>
                                <botao-salvar @salvar="tratarEventoSalvar"/>
                            </div>
                        </v-row>
                    </v-container>
                </v-form>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import _ from 'lodash'
    import {mapActions} from 'vuex'
    import {actionTypes} from '@/core/constants'

    export default {
        name: 'ModalGerenciarPecasEdicao',
        props: {
            value: Boolean,
            item: Object
        },
        data() {
            return {
                dadosGerais: {},
                modalExcluir: false,
            }
        },
        mounted() {
            this.setarDadosGerais()
        },
        methods: {
            ...mapActions([
                actionTypes.ORCAMENTO.PECA.EDITAR_PECA,
                actionTypes.ORCAMENTO.PECA.EXCLUIR_PECA
            ]),
            setarDadosGerais() {
                this.dadosGerais = _.cloneDeep(this.item)
            },
            fecharModalExcluir() {
                this.modalExcluir = false
            },
            abrirModalExcluir() {
                this.modalExcluir = true
            },
            async tratarEventoExcluir() {
                await this.excluirPeca(this.dadosGerais.id)
                this.fecharModalExcluir()
                this.tratarEventoCancelar()
                this.mostrarNotificacaoSucessoDefault()
            },
            tratarEventoCancelar() {
                this.$emit('cancelarAcaoEditar')
            },
            async tratarEventoSalvar() {
                if (await this.validarDadosFormulario()) {
                    this.setMensagemLoading('Salvando alterações da peça...')
                    await this.editarPeca(this.dadosGerais)
                    this.mostrarNotificacaoSucessoDefault()
                }
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
            },
        }
    }
</script>

<style scoped lang="stylus">

    .header-text
        font-size 20px
        font-weight bold
        color #777

    .row-top
        border-bottom-width 1px
        border-bottom-style solid
        border-bottom-color #ccc
        box-sizing border-box

    .row-bottom
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc
        box-sizing border-box
        margin-top 5%
        padding-top 10px
        padding-bottom 10px
        display flex
        justify-content space-between

    .auto-complete-item-text
        font-size 14px !important
        color #555 !important

</style>