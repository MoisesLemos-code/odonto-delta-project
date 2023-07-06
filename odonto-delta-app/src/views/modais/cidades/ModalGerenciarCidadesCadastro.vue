<template>
    <v-dialog v-model="value" max-width="700" scrollable persistent>
        <v-card elevation="4" tile>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Cadastro de Cidade</v-toolbar-title>
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
                                    v-model="dadosGerais.nome"
                                    name="nomeCidade"
                                    placeholder="Informe o nome da cidade"
                                    counter="100"
                                    maxlength="100"
                                    v-validate="{required: true, min: 2, max: 100}"
                                    :error-messages="errors.collect('nomeCidade')">
                                <template v-slot:label>
                                    Nome
                                    <span class="ml-1 red--text">*</span>
                                </template>
                            </v-text-field>
                        </v-col>
                        <v-col cols="12" md="6" sm="6" xs="12">
                            <v-autocomplete
                                    v-model="dadosGerais.id"
                                    name="estadoCidade"
                                    placeholder="Selecione o estado"
                                    :items="estados"
                                    item-text="nomeComposto"
                                    item-value="id"
                                    :filter="filtroComboAutoComplete"
                                    v-validate="{required: true}"
                                    :error-messages="errors.collect('estadoCidade')">
                                <template v-slot:label>
                                    Estado
                                    <span class="ml-1 red--text">*</span>
                                </template>
                                <template v-slot:item="data">
                                    <label class="auto-complete-item-text">{{ data.item.nomeComposto }}</label>
                                </template>
                            </v-autocomplete>
                        </v-col>
                    </v-row>
                    <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
                        <botao-cancelar @cancelar="tratarEventoCancelar"/>

                        <botao-salvar @salvar="tratarEventoSalvar"/>
                    </v-row>
                </v-container>
            </v-form>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapActions} from 'vuex'
    import {actionTypes} from '@/core/constants'

    export default {
        name: 'ModalGerenciarCidadesCadastro',
        props: {
            value: Boolean,
        },
        data() {
            return {
                dadosGerais: {},
                estados: []
            }
        },
        async mounted() {
            await this.buscarEstados()
        },
        methods: {
            ...mapActions([
                actionTypes.CADASTROS.ESTADO.BUSCAR_TODOS_ESTADOS_SEM_PAGINACAO,
                actionTypes.CADASTROS.CIDADE.CADASTRAR_CIDADE
            ]),
            async buscarEstados() {
                const resultado = await this.buscarTodosEstadosSemPaginacao()
                if (resultado) {
                    this.estados = resultado
                }
            },
            filtroComboAutoComplete(item, queryText) {
                const text = item.nomeComposto.toLowerCase()
                const searchText = queryText.toLowerCase()
                return text.indexOf(searchText) > -1
            },
            tratarEventoCancelar() {
                this.$emit('cancelarAcaoCadastro')
            },
            async tratarEventoSalvar() {
                if (await this.validarDadosFormulario()) {
                    this.setMensagemLoading('Salvando a cidade...')
                    const estado = {id: this.dadosGerais.id}
                    const cidade = {nome: this.dadosGerais.nome, estado}
                    await this.cadastrarCidade(cidade)
                    this.mostrarNotificacaoSucessoDefault()
                    this.tratarEventoCancelar()
                }
            },
            async validarDadosFormulario() {
                await this.$validator._base.validateAll()
                if (this.dadosGerais.nome && this.dadosGerais.id) return true
                return false
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