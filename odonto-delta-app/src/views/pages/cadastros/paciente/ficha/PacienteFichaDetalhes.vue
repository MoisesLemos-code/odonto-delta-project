<template>
    <v-row class="white pl-3 pr-3 ml-0 mr-0 pb-5">
        <v-col cols="12">
            <v-card class="dados-gerais">
                <v-expansion-panels v-model="exibirPanel" active-class="" flat>
                    <v-expansion-panel>
                        <v-expansion-panel-header class="dados-gerais-panel-header">
                            <h4 class="titulo">Ficha médica</h4>
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12" class="pt-5">
                                    <campo-check-box-editavel
                                            v-model="fichaPaciente"
                                            :label="'História médica'"
                                            :name="'check-box'"
                                            @input="value => tratarEventoSalvar(value)"
                                            @cancelarEdicao="value => tratarEventoCancelar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.tratamento"
                                            :label="'Está fazendo tratamento médico'"
                                            :name="'esta-fazendo-tratamento'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                  <campo-de-texto-editavel
                                      v-model="fichaPaciente.outrosMedicamentos"
                                      label="Outros medicamentos"
                                      name="outros_medicamentos"
                                      :maxlength="250"
                                      :counter="250"
                                      @input="value => tratarEventoSalvar(value)"
                                  />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.anestesiaLocal"
                                            label="Já fez uso de anestesia local?"
                                            name="uso-anestesia-local"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.possuiReacaoAnestesiaLocal"
                                            label="Em caso positivo, já sentiu alguma reação?"
                                            name="caso-positivo-anestesia"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.usoAntibiotico"
                                            label="Já fez uso de penicilina ou outros antibióticos?"
                                            name="uso-antibiotico"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.possuiReacaoAntibiotico"
                                            label="Em caso positivo, já sentiu alguma reação?"
                                            name="caso-positivo-antibiotico"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaPaciente.nomeAntibiotico"
                                            label="Nome do antibótico que houve reação"
                                            name="nome_antibiotico"
                                            :maxlength="150"
                                            :counter="150"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaPaciente.nomeMedicamentoAlergia"
                                            label="Possui alergia a algum medicamento?"
                                            name="nome_medicamento_alergia"
                                            :maxlength="250"
                                            :counter="250"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.cicatrizacaoNormal"
                                            label="Quando se fere, o sangramento e a cicatrização são normais?"
                                            name="cicatrizacao-normal"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaPaciente.tiqueMania"
                                            label="Possui algum tique ou mania?"
                                            name="qual-tique-mania"
                                            :maxlength="250"
                                            :counter="250"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.estaGravida"
                                            label="Está gravida?"
                                            name="esta-gravida"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaPaciente.fumante"
                                            label="É fumante?"
                                            name="fumante"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-text-area-editavel
                                            v-model="fichaPaciente.outrasInformacoesImportantes"
                                            label="Outras informações importantes"
                                            name="outras informações importantes"
                                            :validate="{max: 500}"
                                            @input="tratarEventoSalvar()"
                                    />
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
import _ from 'lodash'
import tipoPaciente from '@/core/constants/enums/tipoPaciente'
import CampoCheckBoxEditavel from '@/views/components/camposEditaveis/campo-check-box-editavel'
import CampoRadioGroupEditavel from '@/views/components/camposEditaveis/campo-radio-group-editavel'
import CampoDeTextoEditavel from '@/views/components/camposEditaveis/campo-texto-editavel'
import CampoTextAreaEditavel from '@/views/components/camposEditaveis/campo-text-area-editavel'

export default {
    name: 'PacienteFichaDetalhes',
    components: {CampoTextAreaEditavel, CampoDeTextoEditavel, CampoRadioGroupEditavel, CampoCheckBoxEditavel},
    props: ['pacienteId', 'fichaPaciente'],
    data() {
        return {
            exibirPanel: false,
            tipoPaciente
        }
    },
    methods: {
        tratarEventoSalvar() {
            let ficha = _.cloneDeep(this.fichaPaciente)
            this.$emit('salvarFicha', ficha)
        },
        tratarEventoCancelar(ficha){
            this.$emit('cancelarEdicaoFicha', ficha)
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
