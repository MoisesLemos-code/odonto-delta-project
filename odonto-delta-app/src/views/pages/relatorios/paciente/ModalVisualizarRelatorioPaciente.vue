<template>
    <v-dialog v-model="value" max-width="920" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Relatório de pacientes</v-toolbar-title>
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
                        class="table-list mt-2">
                    <template v-slot:item.nome="{item}">
                        <span class="d-inline-block text-truncate max-20">{{item.nome | textoSemValor}}</span>
                    </template>
                    <template v-slot:item.cpfOuCnpj="{item}">
                        <span class="d-inline-block text-truncate max-10">{{formatarCpfCnpj(item.cpfOuCnpj) | textoSemValor}}</span>
                    </template>
                    <template v-slot:item.email="{item}">
                        <span class="d-inline-block text-truncate max-15">{{item.email | textoSemValor}}</span>
                    </template>
                    <template v-slot:item.cidade="{item}">
                        <span class="d-inline-block text-truncate max-15">{{item.cidade | textoSemValor}}</span>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: 'ModalVisualizarRelatorioPaciente',
        props: ['itens', 'value'],
        data() {
            return {
                colunas: [
                    {
                        text: 'Nome',
                        value: 'nome',
                        sortable: true,
                        align: 'left',
                        width: '20%',
                        class: 'gray--text'
                    },
                    {
                        text: 'CPF/CNPJ',
                        value: 'cpfOuCnpj',
                        sortable: true,
                        align: 'left',
                        width: '10%',
                        class: 'gray--text'
                    },
                    {
                        text: 'E-mail',
                        value: 'email',
                        sortable: true,
                        align: 'left',
                        width: '15%',
                        class: 'gray--text'
                    },
                    {
                        text: 'Cidade',
                        value: 'cidade',
                        sortable: true,
                        align: 'left',
                        width: '15%',
                        class: 'gray--text'
                    },
                ],
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
