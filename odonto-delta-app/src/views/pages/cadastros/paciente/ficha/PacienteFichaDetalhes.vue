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
                                            v-model="fichaDadosGerais.historiaMedica"
                                            :label="'História médica'"
                                            :name="'check-box'"
                                            @cancelaEditando="cancelarEdicaoHistoriaMedica"
                                            @input=" value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.estaFazendoTratamento"
                                            :label="'Está fazendo tratamento médico'"
                                            :name="'esta-fazendo-tratamento'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-check-box-editavel
                                            v-model="fichaDadosGerais.usoMedicamento"
                                            :label="'Está fazendo uso de medicamento'"
                                            :name="'check-box'"
                                            @cancelaEditando="cancelarEdicaoUsoMedico"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaDadosGerais.outrosMedicamentos"
                                            :label="'Outros medicamentos'"
                                            :name="'outros_medicamentos'"
                                            :maxlength="100"
                                            :counter="100"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.fezUsoAnestesiaLocal"
                                            :label="'Já fez uso de anestesia local?'"
                                            :name="'uso-anestesia-local'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.casoPositivoAnestesiaLocal"
                                            :label="'Em caso positivo, já sentiu alguma reação?'"
                                            :name="'caso-positivo-anestesia'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.fezUsoAntibiotico"
                                            :label="'Já fez uso de penicilina ou outros antibióticos?'"
                                            :name="'uso-antibiotico'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.casoPositivoAntibiotico"
                                            :label="'Em caso positivo, já sentiu alguma reação?'"
                                            :name="'caso-positivo-antibiotico'"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaDadosGerais.nomeAntibiotico"
                                            :label="'Nome do antibótico'"
                                            :name="'nome_antibiotico'"
                                            :maxlength="100"
                                            :counter="100"
                                            @input="value => tratarEventoSalvar(value)"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.alergiaMedicamento"
                                            :label="'Já teve alergia a algum tipo de medicamento?'"
                                            :name="'alergia-medicamento'"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaDadosGerais.nomeMedicamentoAlergia"
                                            :label="'Nome do medicamento'"
                                            :name="'nome_medicamento_alergia'"
                                            :maxlength="100"
                                            :counter="100"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.cicatrizacaoNormal"
                                            :label="'Quando se fere, o sangramento e a cicatrização são normais?'"
                                            :name="'cicatrizacao-normal'"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.tiqueMania"
                                            :label="'Possui algum tique ou mania?'"
                                            :name="'tique-mania'"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-de-texto-editavel
                                            v-model="fichaDadosGerais.qualTiqueMania"
                                            :label="'Qual?'"
                                            :name="'qual-tique-mania'"
                                            :maxlength="100"
                                            :counter="100"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.estaGravida"
                                            :label="'Está gravida?'"
                                            :name="'esta-gravida'"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <campo-radio-group-editavel
                                            v-model="fichaDadosGerais.fumante"
                                            :label="'Fuma?'"
                                            :name="'fumante'"
                                            @input="tratarEventoSalvar()"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <campo-text-area-editavel
                                            v-model="fichaDadosGerais.outrasInformacoesImportantes"
                                            :label="'Outras informações importantes'"
                                            :name="'outras informações importantes'"
                                            :validate="'max: 200'"
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
    import {mapActions} from 'vuex'
    import {actionTypes} from '@/core/constants'
    import tipoPaciente from '@/core/constants/enums/tipoPaciente'
    import CampoCheckBoxEditavel from '@/views/components/camposEditaveis/campo-check-box-editavel'
    import CampoRadioGroupEditavel from '@/views/components/camposEditaveis/campo-radio-group-editavel'
    import CampoDeTextoEditavel from '@/views/components/camposEditaveis/campo-texto-editavel'
    import CampoTextAreaEditavel from '@/views/components/camposEditaveis/campo-text-area-editavel'

    export default {
        name: 'PacienteFichaDetalhes',
        components: {CampoTextAreaEditavel, CampoDeTextoEditavel, CampoRadioGroupEditavel, CampoCheckBoxEditavel},
        props: ['pacienteId'],
        data() {
            return {
                fichaDadosGerais: {},
                historiaMedicaAnterior: {},
                usoMedicamentoAnterior: {},
                exibirPanel: false,
                tipoPaciente
            }
        },
        async mounted() {
            await this.buscarFicha()
            await this.setarHistoriaMedicaAnterior()
            await this.setarUsoMedicamentoAnterior()
        },
        methods: {
            ...mapActions([ actionTypes.CADASTROS.PACIENTE.FICHA.BUSCAR_POR_PACIENTE ]),
            async buscarFicha() {
                this.fichaDadosGerais = await this.buscarPorPaciente(this.pacienteId)
            },
            async setarHistoriaMedicaAnterior() {
                this.historiaMedicaAnterior = _.cloneDeep(this.fichaDadosGerais.historiaMedica)
            },
            async setarUsoMedicamentoAnterior() {
                this.usoMedicamentoAnterior = _.cloneDeep(this.fichaDadosGerais.usoMedicamento)
            },
            cancelarEdicaoHistoriaMedica() {
                this.fichaDadosGerais.historiaMedica = _.cloneDeep(this.historiaMedicaAnterior)
            },
            cancelarEdicaoUsoMedico() {
                this.fichaDadosGerais.usoMedicamento = _.cloneDeep(this.usoMedicamentoAnterior)
            },
            construirHistoriaMedica() {
                let historiaMedicaFilter = []
                this.setarHistoriaMedicaAnterior()
                this.fichaDadosGerais.historiaMedica.forEach(x => {
                    if (x.atribuido) {
                        historiaMedicaFilter.push(x.cod)
                    }
                })
                return historiaMedicaFilter
            },
            construirUsoMedicamento() {
                let usoMedicamentoFilter = []
                this.fichaDadosGerais.usoMedicamento.forEach(x => {
                    if (x.atribuido) {
                        usoMedicamentoFilter.push(x.cod)
                    }
                })
                return usoMedicamentoFilter
            },
            tratarEventoSalvar() {
                let ficha = _.cloneDeep(this.fichaDadosGerais)
                ficha.historiaMedica = this.construirHistoriaMedica()
                ficha.usoMedicamento = this.construirUsoMedicamento()
                this.setarHistoriaMedicaAnterior()
                this.setarUsoMedicamentoAnterior()
                this.$emit('salvar', ficha)
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
