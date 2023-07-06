<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent @click:outside="fecharModal">
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Adicionar Serviço {{ face ? ' - Face: ' + face : ' - Espaço geral'}}</v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-servicos-text">
                <v-container fluid grid-list-xl white>
                    <v-row wrap align-center white class="pl-10 pr-10">
                        <v-col cols="12" md="6" sm="6" xs="12">
                            <v-autocomplete
                                    v-model="servicoSelecionado"
                                    name="servico"
                                    placeholder="Selecione o serviço"
                                    :items="servicos"
                                    item-text="descricao"
                                    return-object
                                    :filter="filtroComboAutoComplete"
                                    v-validate="{required: true}"
                                    :error-messages="errors.collect('servico')"
                            >
                                <template v-slot:item="data">
                                    <label class="auto-complete-item-text">{{ data.item.descricao }}</label>
                                </template>
                            </v-autocomplete>
                        </v-col>
                        <v-col cols="12" md="6" sm="6" xs="12">
                            <v-text-field
                                    v-model="servicoSelecionado.valor"
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
                <v-btn @click="selecionarServicoDenteItem" color="primary" depressed>Selecionar</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {actionTypes} from '@/core/constants'
    import {mapActions} from 'vuex'

    export default {
        name: 'ModalDenteItemServico',
        props: {
            value: Boolean,
            face: String
        },
        data() {
            return {
                servicos: [],
                servicoSelecionado: {
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
                actionTypes.ORCAMENTO.SERVICO.BUSCAR_TODOS_SERVICOS_SEM_PAGINACAO
            ]),
            async buscarTodosServicos() {
                this.servicos = await this.buscarTodosServicosSemPaginacao()
            },
            fecharModal() {
                this.$emit('fecharModalServico')
            },
            async selecionarServicoDenteItem() {
                if (await this.validarDadosFormulario() && this.servicoSelecionado.descricao) {
                    this.servicoSelecionado.localizacao = this.setarLocalizacao()
                    this.$emit('selecionarServico', this.servicoSelecionado)
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

    .modal-servicos-text
        padding 0px !important
        overflow-x hidden
        height auto

    .auto-complete-item-text
        font-size 14px !important
        color #555 !important

</style>