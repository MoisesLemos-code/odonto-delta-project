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
                                    <p class="mb-0 text-truncate texto">{{ paciente.cnpjCpf | formatarCpfCnpj }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Data aniversário</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.dataNascimento |
                                        filterDate(paciente.dataNascimento) }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">E-mail</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.email | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Rua</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.logradouro | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Número</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.logradouroNumero | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Bairro</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.bairro | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">CEP</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.cep | formatarCEP }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Complemento</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.complemento | textoSemValor }}</p>
                                </v-col>
                                <v-col cols="12" md="3" sm="6" xs="12">
                                    <span class="font-weight-bold label">Contato Principal</span>
                                    <p class="mb-0 text-truncate texto">{{ paciente.telefone | formatarTelefone }}</p>
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
        }
    },
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
