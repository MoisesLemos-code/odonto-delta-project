<template>
    <div>
        <v-form class="form-content pb-0 altura-componente-container" ref="form">
            <orcamento-modal-paciente v-model="modalPaciente" @fechar="fecharModalPaciente"
                                      @selecionar="selecionarPaciente"/>
            <orcamento-paciente-vazio v-if="!dadosDeEntrada.paciente"
                                      @adicionarPaciente="abrirModalSelecionarPaciente"/>
            <v-container v-else fluid class="pa-0">
                <div class="orcamento-paciente-header">
                    <v-row wrap>
                        <v-col cols="12" md="12" sm="12" xs="12" class="head-orcamento pb-0">
                            <label class="head-nome-paciente max-40">{{ dadosDeEntrada.paciente.nome }}</label>
                            <v-tooltip top max-width="200">
                                <template v-slot:activator="{ on }">
                                    <div v-on="on">
                                        <v-btn icon class="ml-3" @click="abrirModalSelecionarPaciente">
                                            <v-icon color="white" size="15">fas fa-edit</v-icon>
                                        </v-btn>
                                    </div>
                                </template>
                                Alterar paciente
                            </v-tooltip>
                            <v-tooltip top max-width="200">
                                <template v-slot:activator="{ on }">
                                    <div v-on="on">
                                        <v-btn icon class="ml-3" @click="direcionarFichaPaciente">
                                            <v-icon color="white" size="18">far fa-id-card</v-icon>
                                        </v-btn>
                                    </div>
                                </template>
                                Visualizar Ficha
                            </v-tooltip>
                        </v-col>
                    </v-row>
                    <v-row wrap>
                        <v-col cols="12" md="12" sm="12" xs="12" class="head-orcamento pt-0 mt-5">
                            <label class="head-text-orcamento">Idade: {{ dadosDeEntrada.paciente.idade }} anos</label>
                        </v-col>
                    </v-row>
                </div>
            </v-container>
        </v-form>
        <acoes-orcamento :controleContinuar="podeContinuar" @voltar="tratarEventoVoltar"
                         @continuar="tratarEventoContinuar"/>
    </div>
</template>

<script>
    import AcoesOrcamento from '@/views/pages/orcamento/dados-gerais/registro-orcamento/AcoesOrcamento'
    import OrcamentoPacienteVazio from '@/views/pages/orcamento/dados-gerais/passo01/OrcamentoPacienteVazio'
    import OrcamentoModalPaciente from '@/views/modais/orcamento-paciente/OrcamentoModalPaciente'
    import {actionTypes} from '@/core/constants'
    import {mapActions} from 'vuex'
    import _ from 'lodash'

    export default {
        name: 'OrcamentoPaciente',
        components: {OrcamentoModalPaciente, OrcamentoPacienteVazio, AcoesOrcamento},
        data() {
            return {
                dadosDeEntrada: {
                    id: null
                },
                orcamentoId: null,
                modalPaciente: false
            }
        },
        computed: {
            podeContinuar() {
                return this.dadosDeEntrada.paciente
            }
        },
        async mounted() {
            this.setOrcamentoId()
            await this.buscarOrcamento()
            this.verificarPasso2()
        },
        methods: {
            ...mapActions([
                actionTypes.ORCAMENTO.EDITAR_ORCAMENTO,
                actionTypes.ORCAMENTO.BUSCAR_ORCAMENTO_POR_ID_SIMPLES,
            ]),
            setOrcamentoId() {
                if (this.$route.params.orcamentoId) {
                    this.orcamentoId = this.$route.params.orcamentoId
                }
            },
            async buscarOrcamento() {
                this.dadosDeEntrada = await this.buscarOrcamentoPorIdSimples(this.orcamentoId)
            },
            async editarOrcamentoSelecionado() {
                this.verificarPasso2()
                await this.editarOrcamento(this.setarDadosEdicao())
            },
            setarDadosEdicao() {
                const dados = _.cloneDeep(this.dadosDeEntrada)
                dados.paciente = {id: this.dadosDeEntrada.paciente.id}
                return dados
            },
            verificarPasso2() {
                if (this.podeContinuar) {
                    this.$emit('habilitaPasso2')
                    this.$emit('habilitaPasso3')
                } else {
                    this.$emit('desabilitaPasso2')
                    this.$emit('desabilitaPasso3')
                }
            },
            abrirModalSelecionarPaciente() {
                this.modalPaciente = true
            },
            direcionarFichaPaciente() {
                if (this.dadosDeEntrada.paciente.id) {
                    this.$router.push({
                        name: 'PacienteFichaOrcamento',
                        params: {id: this.dadosDeEntrada.paciente.id, orcamentoId: this.orcamentoId}
                    })
                }
            },
            fecharModalPaciente() {
                this.modalPaciente = false
            },
            async selecionarPaciente(paciente) {
                this.dadosDeEntrada.paciente = paciente
                this.fecharModalPaciente()
                await this.editarOrcamentoSelecionado()
            },
            tratarEventoVoltar() {
                this.$router.push({name: 'OrcamentoListagem'})
            },
            tratarEventoContinuar() {
                this.$router.push({
                    name: 'OrcamentoOdontograma',
                    params: {orcamentoId: this.orcamentoId}
                })
            }
        }
    }
</script>

<style scoped lang="stylus">

    .orcamento-paciente-header
        background-color #487B9C
        padding 15px

    .head-orcamento
        display flex
        align-content center

    .head-nome-paciente
        color white
        font-size 24px
        font-weight 400

    .head-text-orcamento
        color white
        font-size 14px
        font-weight 400

</style>
