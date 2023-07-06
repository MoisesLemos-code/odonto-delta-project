<template>
    <div>
        <modal-visualizar-relatorio-orcamento
                v-if="modalRelatorio"
                v-model="modalRelatorio"
                :itens="itens"
                @fecharModal="fecharModalRelatorio"
        />
        <v-form class="form-relatorio-pacientes form-content altura-componente-container mt-0 pl-0 pr-0">
            <v-container fluid grid-list-xl class="pt-0">
                <v-row justify="center">
                    <v-card class="card-relatorio-pacinente-container">
                        <v-row justify="center">
                            <v-card-title style="color: #487B9C">Gerar Relatório de Orçamentos</v-card-title>
                        </v-row>
                        <v-card-text style="height: 250px">
                            <v-row wrap align-center white class="pl-5 pr-5">
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <v-chip
                                            :color="dadosGerais.filtroStatus ? 'green' : '#E4E4E4'"
                                            :text-color="dadosGerais.filtroStatus ? 'white' : ''"
                                            :filter-icon="dadosGerais.filtroStatus ? 'mdi-check' : 'mdi-plus'"
                                            filter
                                            :input-value="true"
                                            @click="dadosGerais.filtroStatus = !dadosGerais.filtroStatus"
                                    >
                                        Filtrar Status
                                    </v-chip>
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <combo-enum
                                            v-if="dadosGerais.filtroStatus"
                                            v-model="dadosGerais.status"
                                            placeholder="Selecione o status"
                                            maxlength="100"
                                            name="statusOrcamento"
                                            class="ma-0 pa-0"
                                            :is-required="true"
                                            :enum-object="statusOrcamento"
                                            :insertNullItem="false"
                                            order-by="''"
                                            >
                                    </combo-enum>
                                </v-col>
                            </v-row>
                            <v-row wrap align-center white class="pl-5 pr-5">
                                <v-col cols="12" md="5" sm="5" xs="12">
                                    <v-chip
                                            :color="dadosGerais.filtroPaciente ? 'green' : '#E4E4E4'"
                                            :text-color="dadosGerais.filtroPaciente ? 'white' : ''"
                                            :filter-icon="dadosGerais.filtroPaciente ? 'mdi-check' : 'mdi-plus'"
                                            filter
                                            :input-value="true"
                                            @click="dadosGerais.filtroPaciente = !dadosGerais.filtroPaciente"
                                    >
                                        Filtrar Paciente
                                    </v-chip>
                                </v-col>
                                <v-col cols="12" md="7" sm="7" xs="12">
                                    <v-autocomplete
                                            v-if="dadosGerais.filtroPaciente"
                                            v-model="dadosGerais.pacienteId"
                                            name="filtroPaciente"
                                            placeholder="Selecione o paciente"
                                            :items="pacientes"
                                            item-text="nome"
                                            item-value="id"
                                            class="ma-0 pa-0"
                                            :filter="filtroComboAutoComplete"
                                            v-validate="{required: dadosGerais.filtroPaciente}"
                                            :error-messages="errors.collect('filtroPaciente')">
                                        <template v-slot:item="data">
                                            <label class="auto-complete-item-text">{{ data.item.nome }}</label>
                                        </template>
                                    </v-autocomplete>
                                </v-col>
                            </v-row>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn
                                    color="primary"
                                    depressed
                                    block
                                    @click="gerarRelatorio">
                                Gerar Relatório
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </v-container>
        </v-form>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import {actionTypes} from '@/core/constants'
    import ComboEnum from '@/views/components/ComboEnum'
    import statusOrcamento from '@/core/constants/enums/statusOrcamento'
    import ModalVisualizarRelatorioOrcamento from './ModalVisualizarRelatorioOrcamento'

    export default {
        name: 'RelatorioOrcamento',
        components: {ModalVisualizarRelatorioOrcamento, ComboEnum},
        data() {
            return {
                statusOrcamento,
                dadosGerais: {
                    filtroStatus: false,
                    status: 'ABERTO',
                    filtroPaciente: false,
                    pacienteId: null
                },
                itens: [],
                pacientes: [],
                modalRelatorio: false
            }
        },
        async mounted() {
            await this.buscarPacientes()
        },
        methods: {
            ...mapActions([
                actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO,
                actionTypes.RELATORIOS.GERAR_RELATORIO_ORCAMENTOS
            ]),
            async buscarPacientes() {
                const resultado = await this.buscarTodosPacientesSemPaginacao()
                if (resultado) {
                    this.pacientes = resultado
                }
            },
            filtroComboAutoComplete(item, queryText) {
                const text = item.nome.toLowerCase()
                const searchText = queryText.toLowerCase()
                return text.indexOf(searchText) > -1
            },
            async gerarRelatorio() {
                if (await this.validarDadosFormulario()) {
                    const data = {
                        status: (this.dadosGerais.filtroStatus ? this.dadosGerais.status : null),
                        pacienteId: (this.dadosGerais.filtroPaciente ? this.dadosGerais.pacienteId : null)
                    }
                    const response = await this.gerarRelatorioOrcamentos(data)
                    if (response) {
                        this.itens = response
                        this.abrirModalRelatorio()
                    }
                }
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
            },
            abrirModalRelatorio() {
                this.modalRelatorio = true
            },
            fecharModalRelatorio() {
                this.modalRelatorio = false
            }
        }
    }
</script>

<style scoped lang="stylus">

    .form-relatorio-pacientes
        background-color #EEEEEE;
        display flex
        justify-content center
        align-items center
        height 80vh

    .card-relatorio-pacinente-container
        width 450px

</style>