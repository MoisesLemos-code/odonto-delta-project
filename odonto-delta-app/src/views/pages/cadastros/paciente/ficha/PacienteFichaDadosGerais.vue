<template>
    <v-row class="white pl-3 pr-3 ml-0 mr-0 pb-5">
        <v-col cols="12">
            <v-card class="dados-gerais">
                <v-expansion-panels v-model="exibirPanel" active-class="" flat>
                    <v-expansion-panel>
                        <v-expansion-panel-header class="dados-gerais-panel-header">
                            <h4 class="titulo">Dados Gerais</h4>
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                            <v-row>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">CPF/CNPJ</span>
                                    <p class="mb-0 text-truncate texto">{{ formatarCpfCnpj(paciente.cpfOuCnpj) | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Tipo</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.tipo | filterEnum(tipoPaciente)
                                        }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Data aniversário</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.dataAniversario |
                                        filterDate(paciente.dataAniversario) }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">E-mail</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.email | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Rua</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.rua | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Número</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.numero | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Bairro</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.bairro | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">CEP</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.cep | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Complemento</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.complemento | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Contato Principal</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.contatoPrincipal | textoSemValor
                                        }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">2º Contato</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.contato2 | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">3º Contato</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.contato3 | textoSemValor }}</p>
                                </v-col>

                            </v-row>
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import tipoPaciente from '@/core/constants/enums/tipoPaciente'

    export default {
        name: 'PacienteFichaDadosGerais',
        props: {
            paciente: {
                required: true
            }
        },
        data() {
            return {
                exibirPanel: 0,
                tipoPaciente
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
            }
        }
    }
</script>

<style scoped lang="stylus">
    .dados-gerais
        border solid 1px #e7e7e7 !important
        border-radius 5px !important

    .dados-gerais-panel-header
        border-bottom solid 1px #e7e7e7 !important
        background-color #F6F6F6
        border-radius 5px !important
        min-height 20px !important
        padding-bottom 10px
        padding-top 10px

    .titulo
        font-size 15px
        font-weight bold
        color #777 !important

    .label
        font-size 13px
        font-weight bold
        color #777 !important

    .texto
        font-size 13px
        color #777 !important

</style>
