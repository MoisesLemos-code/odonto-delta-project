<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Orçamento Nº{{ dadosGerais.id }}
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-usuario-edit-container">
                <v-form class="form-content mt-0 pl-0 pr-0">
                    <v-container fluid grid-list-xl white>
                        <v-row class="pa-5 mb-5">
                            <v-row class="pl-5" cols="12" md="6">
                                <v-col class="pr-0" md="3" sm="2" xs="6">
                                    <span class="font-weight-bold">Status</span>
                                    <p class="mb-0 text-truncate">{{ dadosGerais.status | filterEnum(statusOrcamento) }}</p>
                                </v-col>
                                <v-col class="pl-0 pr-0" md="3" sm="1" xs="6">
                                    <span class="font-weight-bold">Data de abertura</span>
                                    <p class="mb-0">{{ dadosGerais.data_abertura | textoSemValor }}</p>
                                </v-col>
                                <v-col class="pl-0 pr-0" md="3" sm="1" xs="6">
                                    <span class="font-weight-bold">Data de fechamento</span>
                                    <p class="mb-0">{{ dadosGerais.data_fechamento | textoSemValor }}</p>
                                </v-col>
                                <v-col class="pl-0" md="3" sm="3" xs="6">
                                    <span class="font-weight-bold">Valor Total</span>
                                    <p v-if="dadosGerais.valorTotal" class="mb-0 text-truncate">{{ dadosGerais.valorTotal | valorParaReal }}</p>
                                    <p v-else class="mb-0 text-truncate">{{ dadosGerais.valorTotal | textoSemValor }}</p>
                                </v-col>
                            </v-row>
                        </v-row>
                    </v-container>
                </v-form>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import statusOrcamento from '@/core/constants/enums/statusOrcamento'

export default {
    name: 'PacienteFichaOrcamentoModal',
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            dadosGerais: {},
            statusOrcamento
        }
    },
    async mounted() {
        await this.setarDadosGerais()
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.BURCAR_ORCAMENTO_MODAL_POR_ID
        ]),
        async setarDadosGerais() {
            this.dadosGerais = await this.buscarOrcamentoModalPorId(this.item.id)
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