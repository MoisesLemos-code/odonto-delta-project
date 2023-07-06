<template>
    <div>
        <modal-visualizar-relatorio-paciente
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
                            <v-card-title style="color: #487B9C">Gerar Relatório de Pacientes</v-card-title>
                        </v-row>
                        <v-card-text style="height: 250px">
                            <v-row wrap align-center white class="pl-5 pr-5">
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <v-chip
                                            :color="dadosGerais.filtroOrtodontia ? 'green' : '#E4E4E4'"
                                            :text-color="dadosGerais.filtroOrtodontia ? 'white' : ''"
                                            :filter-icon="dadosGerais.filtroOrtodontia ? 'mdi-check' : 'mdi-plus'"
                                            filter
                                            :input-value="true"
                                            @click="dadosGerais.filtroOrtodontia = !dadosGerais.filtroOrtodontia"
                                    >
                                        Filtrar Ortodontia
                                    </v-chip>
                                </v-col>
                                <v-col cols="12" md="6" sm="6" xs="12">
                                    <v-radio-group
                                            v-if="dadosGerais.filtroOrtodontia"
                                            style="margin: 0px"
                                            v-model="dadosGerais.ortodontia">
                                        <v-radio label="Possui ortodontia" :value="true"/>
                                        <v-radio label="Não possui ortodontia" :value="false"/>
                                    </v-radio-group>
                                </v-col>
                            </v-row>
                            <v-row wrap align-center white class="pl-5 pr-5">
                                <v-col cols="12" md="5" sm="5" xs="12">
                                    <v-chip
                                            :color="dadosGerais.cidade ? 'green' : '#E4E4E4'"
                                            :text-color="dadosGerais.cidade ? 'white' : ''"
                                            :filter-icon="dadosGerais.cidade ? 'mdi-check' : 'mdi-plus'"
                                            filter
                                            :input-value="true"
                                            @click="dadosGerais.cidade = !dadosGerais.cidade"
                                    >
                                        Filtrar Cidade
                                    </v-chip>
                                </v-col>
                                <v-col cols="12" md="7" sm="7" xs="12">
                                    <v-autocomplete
                                            v-if="dadosGerais.cidade"
                                            v-model="dadosGerais.cidadeId"
                                            name="cidade"
                                            placeholder="Selecione a cidade"
                                            :items="cidades"
                                            item-text="nome"
                                            item-value="id"
                                            class="ma-0 pa-0"
                                            :filter="filtroComboAutoComplete"
                                            v-validate="{required: dadosGerais.cidade}"
                                            :error-messages="errors.collect('cidade')">
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
    import ModalVisualizarRelatorioPaciente from './ModalVisualizarRelatorioPaciente'

    export default {
        name: 'RelatorioPaciente',
        components: {ModalVisualizarRelatorioPaciente},
        data() {
            return {
                dadosGerais: {
                    filtroOrtodontia: false,
                    ortodontia: true,
                    cidade: false,
                    cidadeId: null
                },
                itens: [],
                cidades: [],
                modalRelatorio: false
            }
        },
        async mounted() {
            await this.buscarCidades()
        },
        methods: {
            ...mapActions([
                actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES_SEM_PAGINACAO,
                actionTypes.RELATORIOS.GERAR_RELATORIO_PACIENTES
            ]),
            async buscarCidades() {
                const resultado = await this.buscarTodasCidadesSemPaginacao()
                if (resultado) {
                    this.cidades = resultado
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
                        ortodontia: (this.dadosGerais.filtroOrtodontia ? this.dadosGerais.ortodontia : null),
                        cidadeId: (this.dadosGerais.cidade ? this.dadosGerais.cidadeId : null)
                    }
                    const response = await this.gerarRelatorioPacientes(data)
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