<template>
    <v-dialog v-model="value" max-width="580" scrollable persistent>
        <v-card id="modal-melhor-lance">
            <v-toolbar dark color="#3a6861" elevation="0">
                <v-toolbar-title><h4>Lances: {{ lote.sequencia }}</h4></v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-detalhes-text">
                <div class="container-table-detalhes">
                    <v-simple-table class="table-detalhe-lote">
                        <template v-slot:default>
                            <thead class="table-detalhe-header">
                            <tr>
                                <th class="text-left table-detalhe-coluna">
                                    Data/Hora
                                </th>
                                <th class="text-left">
                                    Valor
                                </th>
                            </tr>
                            </thead>
                            <tbody style="color: #666">
                            <tr v-for="item in itens" :key="item.idLance">

                                <template v-if="fornecedorId == item.fornecedor.id">
                                    <td class="table-detalhe-coluna" style="color: #3a6861; font-weight: bold">
                                        {{ item.dataLance | azDate('DD/MM/YYYY - HH:mm:ss')}}
                                    </td>
                                    <td class="table-detalhe-linha-fornecedor">
                                        <span>{{ item.valorLance | valorParaReal(casasDecimais) }}</span>
                                        <v-tooltip top color="black">
                                            <template v-slot:activator="{ on }">
                                                <v-icon v-on="on" color="#3a6861" small >fa-user-check</v-icon>
                                            </template>
                                            Meu Lance
                                        </v-tooltip>
                                    </td>
                                </template>
                                <template v-else>
                                    <td class="table-detalhe-coluna">{{ item.dataLance | azDate('DD/MM/YYYY - HH:mm:ss')}}
                                    </td>
                                    <td>{{ item.valorLance | valorParaReal(casasDecimais) }}</td>
                                </template>
                            </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </div>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>

import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
    name: 'ModalMelhorLanceLote',
    props: {
        value: Boolean,
        lote: {
            type: Object
        },
        fornecedorId: String,
        pregaoId: String,
    },
    data(){
        return{
            itens: [],
            socket: null,
            stompClient: null,
        }
    },
    computed: {
        casasDecimais(){
            return this.$store.state.licitacao.casasDecimais
        }
    },
    async mounted() {
        this.ajustarBarraLateral('modal-melhor-lance')
        await this.buscarLancesLote()
        this.conectar()
        console.log('Aqui:', this.pregaoId)
    },
    methods: {
        ...mapActions([
            actionTypes.FORNECEDOR.BUSCAR_LANCES_LOTE
        ]),
        fecharModal() {
            this.$emit('fecharModalMelhorLance')
        },
        async buscarLancesLote(){
            const resposta = await this.buscarLancesLoteSelecionado({idLote: this.lote.idLote, idPregao: this.pregaoId})
            if(resposta){
                this.itens = resposta.items
            }
        },
        conectar() {
            this.socket = new SockJS(this.getSocketURL())
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.debug = () => {}
            this.stompClient.connect(
                {},
                () => {
                    this.stompClient.subscribe(`/topic/atualizarInformacoes=${this.pregaoId}`, async () => {
                        await this.buscarLancesLote()
                    })
                    this.stompClient.subscribe(`/topic/lancecanceladopregao=${this.pregaoId}`, async () => {
                        await this.buscarLancesLote()
                    })
                },
                error => {
                    console.log('--------- ERROR')
                    console.log(error)
                }
            )
        },
        getSocketURL() {
            return window.location.protocol + '//' + window.location.hostname + '/pregao/api/sockjs'
        }
    }
}
</script>

<style scoped lang="stylus">

    .container-table-detalhes
        margin 20px

    .modal-detalhes-text
        padding 0px !important
        overflow-x hidden
        height auto

    .table-detalhe-lote
        border 1px solid #ccc

    .table-detalhe-header
        background-color #eee

        tr th
            font-size 14px !important

    .table-detalhe-coluna
        border-right 1px solid #ccc

    .table-detalhe-linha-fornecedor
        color #3a6861
        display flex
        align-items center
        justify-content space-between
        font-weight bold

    .auto-save-modal
        color white
</style>
