<template>
    <v-dialog v-model="value" max-width="920" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Relatório de orçamentos</v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-relatorio-paciente-container">
                <v-data-table
                        :headers="colunas"
                        :items="itens"
                        :items-per-page="10"
                        no-data-text="Nenhum registro encontrado"
                        single-expand
                        :expanded.sync="expandidos"
                        show-expand
                        class="table-list mt-2">
                    <template v-slot:item.paciente.nome="{item}">
                        <span class="d-inline-block text-truncate max-20">{{item.paciente.nome | textoSemValor}}</span>
                    </template>
                    <template v-slot:item.status="{item}">
                        <span class="d-inline-block text-truncate max-10">{{item.status | filterEnum(statusOrcamento)}}</span>
                    </template>
                    <template v-slot:item.servicosItensTotal="{item}">
                        <span class="d-inline-block text-truncate max-15">{{item.servicosItensTotal | valorParaReal}}</span>
                    </template>
                    <template v-slot:item.pecasItensTotal="{item}">
                        <span class="d-inline-block text-truncate max-15">{{item.pecasItensTotal | valorParaReal}}</span>
                    </template>
                    <template v-slot:item.valorTotal="{item}">
                        <span class="d-inline-block text-truncate max-10">{{item.valorTotal | valorParaReal}}</span>
                    </template>
                    <template v-slot:expanded-item="{ headers, item }">
                        <td :colspan="headers.length" class="container-expand">
                            <div class="mt-3" v-if="item.servicosItens.length > 0">
                                <span class="expand-text-header">Serviços</span>
                                <div class="expand-item" :key="itemServico.id + '-servico'"
                                     v-for="itemServico in item.servicosItens">
                                    <span class="expand-text">{{itemServico.servico.descricao}}</span><span> - {{itemServico.denteItem.dente.nome}} </span>
                                  <span>({{itemServico.denteItem.dente.numero}}<span
                                        v-if="itemServico.denteItem.dente.numero2">/{{itemServico.denteItem.dente.numero2}}
                                </span>)</span><span> - {{itemServico.subTotal | valorParaReal}}</span>
                                </div>
                            </div>
                            <div class="mt-3" v-if="item.pecasItens.length > 0">
                                <span class="expand-text-header">Peças</span>
                                <div class="expand-item" :key="itemPeca.id + '-peca'"
                                     v-for="itemPeca in item.pecasItens">
                                    <span class="expand-text">{{itemPeca.peca.descricao}}</span><span> - {{itemPeca.denteItem.dente.nome}} </span>
                                  <span>({{itemPeca.denteItem.dente.numero}}<span
                                        v-if="itemPeca.denteItem.dente.numero2">/{{itemPeca.denteItem.dente.numero2}}</span>)</span>
                                  <span> - {{itemPeca.subTotal | valorParaReal}}</span>
                                </div>
                            </div>
                        </td>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import statusOrcamento from '@/core/constants/enums/statusOrcamento'

export default {
    name: 'ModalVisualizarRelatorioOrcamento',
    props: ['itens', 'value'],
    data() {
        return {
            colunas: [
                {
                    text: 'Paciente',
                    value: 'paciente.nome',
                    sortable: true,
                    align: 'left',
                    width: '20%',
                    class: 'gray--text'
                },
                {
                    text: 'Status',
                    value: 'status',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Total Serviços',
                    value: 'servicosItensTotal',
                    sortable: true,
                    align: 'left',
                    width: '15%',
                    class: 'gray--text'
                },
                {
                    text: 'Total Peças',
                    value: 'pecasItensTotal',
                    sortable: true,
                    align: 'left',
                    width: '15%',
                    class: 'gray--text'
                },
                {
                    text: 'Total',
                    value: 'valorTotal',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Expandir',
                    align: 'right',
                    value: 'data-table-expand'
                },
            ],
            expandidos: [],
            statusOrcamento
        }
    },
    methods: {
        formatarCpfCnpj(cpfCnpjValue) {
            if (cpfCnpjValue) {
                const cnpjCpf = cpfCnpjValue.replace(/\D/g, '')
                if (cnpjCpf.length === 11) {
                    return cnpjCpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, '$1.$2.$3-$4')
                }
                return cnpjCpf.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, '$1.$2.$3/$4-$5')
            }
            return ''
        },
        fecharModal() {
            this.$emit('fecharModal')
        },
    }
}
</script>

<style scoped lang="stylus">

    .modal-relatorio-paciente-container
        padding 0px !important
        overflow-x hidden
        height 900px

    .container-expand
        padding 5px 0px !important

    .expand-text-header
        font-weight bold
        color #757575
        font-size 18px
        padding-left 5px

    .expand-text
        font-weight bold
        color #757575

    .expand-item
        padding-left 5px
        border-bottom 1px solid #E0E0E0

    .max-10
        max-width 10vw

    .max-15
        max-width 15vw

    .max-20
        max-width 20vw

    @media (max-width: 720px)
        .max-10, .max-15, .max-20
            max-width 40vw !important
</style>
