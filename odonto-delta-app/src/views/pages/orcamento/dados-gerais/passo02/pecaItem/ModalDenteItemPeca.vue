<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent @click:outside="fecharModal">
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Adicionar Peça {{ face ? ' - Face: ' + face : ' - Espaço geral'}}</v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-pecas-text">
                <v-container fluid grid-list-xl white>
                    <v-row wrap align-center white class="pl-10 pr-10">
                        <v-col cols="12" md="6" sm="6" xs="12">
                            <v-autocomplete
                                    v-model="pecaSelecionada"
                                    name="peca"
                                    placeholder="Selecione a peça"
                                    :items="pecas"
                                    item-text="descricao"
                                    return-object
                                    :filter="filtroComboAutoComplete"
                                    v-validate="{required: true}"
                                    :error-messages="errors.collect('peca')"
                            >
                                <template v-slot:item="data">
                                    <label class="auto-complete-item-text">{{ data.item.descricao }}</label>
                                </template>
                            </v-autocomplete>
                        </v-col>
                        <v-col cols="12" md="6" sm="6" xs="12">
                            <v-text-field
                                    v-model="pecaSelecionada.valor"
                                    name="valor"
                                    type="number"
                                    placeholder="Informe o valor"
                                    v-validate="{required: true, decimal: true}"
                                    :error-messages="errors.collect('valor')">
                                <template v-slot:label>
                                    Valor
                                </template>
                            </v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer/>
                <v-btn @click="fecharModal" depressed>Cancelar</v-btn>
                <v-btn @click="selecionarPecaDenteItem" color="primary" depressed>Selecionar</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {actionTypes} from '@/core/constants'
    import {mapActions} from 'vuex'

    export default {
        name: 'ModalDenteItemPeca',
        props: {
            value: Boolean,
            face: String
        },
        data() {
            return {
                pecas: [],
                pecaSelecionada: {
                    valor: null,
                    descricao: null
                }
            }
        },
        async mounted() {
            await this.buscarTodosServicos()
        },
        methods: {
            ...mapActions([
                actionTypes.ORCAMENTO.PECA.BUSCAR_TODAS_PECAS_SEM_PAGINACAO
            ]),
            async buscarTodosServicos() {
                this.pecas = await this.buscarTodasPecasSemPaginacao()
            },
            fecharModal() {
                this.$emit('fecharModalPeca')
            },
            async selecionarPecaDenteItem() {
                if (await this.validarDadosFormulario() && this.pecaSelecionada.descricao) {
                    this.pecaSelecionada.localizacao = this.setarLocalizacao()
                    this.$emit('selecionarPeca', this.pecaSelecionada)
                    this.fecharModal()
                } else {
                    this.mostrarNotificacaoAviso('Preencha todos os campos!')
                }
            },
            setarLocalizacao() {
                if(this.face === 'Lingual/Palatal') return 'LINGUAL'
                if(this.face === 'Oclusal') return 'OCLUSAL'
                if(this.face === 'Vestibular') return 'VESTIBULAR'
                if(this.face === 'Distal') return 'DISTAL'
                if(this.face === 'Mesial') return 'MESIAL'
                if(!this.face) return 'GERAL'
            },
            filtroComboAutoComplete(item, queryText) {
                const text = item.descricao.toLowerCase()
                const searchText = queryText.toLowerCase()
                return text.indexOf(searchText) > -1
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
            },
        }
    }
</script>

<style scoped lang="stylus">

    .modal-pecas-text
        padding 0px !important
        overflow-x hidden
        height auto

    .auto-complete-item-text
        font-size 14px !important
        color #555 !important

</style>