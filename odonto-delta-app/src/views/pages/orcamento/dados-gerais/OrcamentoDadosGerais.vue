<template>
    <div>
        <toolbar-view>
            <botao-voltar slot="actions" :route="{name: rotaOrigem}" text="Voltar para listagem"/>
        </toolbar-view>
        <container-component class="orcamento-container">
            <v-stepper v-model="passoAtual.numero">
                <v-stepper-header class="elevation-0 barra-passo">
                    <v-spacer/>

                    <v-stepper-step
                            :complete="passos[0].numero < passoAtual.numero"
                            :disabled="passos[0].desabilitado"
                            :editable="!passos[0].desabilitado"
                            :key="`${passos[0].numero}-header`"
                            :step="passos[0].numero"
                            @click="tratarEventoClick(passos[0])"
                            edit-icon="fas fa-check">
                        {{ passos[0].titulo }}
                    </v-stepper-step>

                    <v-divider/>

                    <v-tooltip
                            nudge-left="10"
                            nudge-top="-22"
                            max-width="200"
                            top>
                        <template v-slot:activator="{ on }">
                            <div v-on="on" v-show="passos[1].desabilitado">
                                <v-stepper-step
                                        :complete="passos[1].numero < passoAtual.numero"
                                        :disabled="passos[1].desabilitado"
                                        :editable="!passos[1].desabilitado"
                                        :key="`${passos[1].numero}-header`"
                                        :step="passos[1].numero"
                                        @click="tratarEventoClick(passos[1])"
                                        edit-icon="fas fa-check"
                                        :class="{'passo-ativo' : !passos[1].desabilitado}"
                                >{{ passos[1].titulo }}
                                </v-stepper-step>
                            </div>
                            <v-stepper-step
                                    :complete="passos[1].numero < passoAtual.numero"
                                    :disabled="passos[1].desabilitado"
                                    :editable="!passos[1].desabilitado"
                                    :key="`${passos[1].numero}-header`"
                                    :step="passos[1].numero"
                                    @click="tratarEventoClick(passos[1])"
                                    edit-icon="fas fa-check"
                                    v-show="!passos[1].desabilitado"
                                    :class="{'passo-ativo' : !passos[1].desabilitado}"
                            >{{ passos[1].titulo }}
                            </v-stepper-step>
                        </template>
                        É necessário que todos os campos obrigatórios sejam preenchidos para continuar.
                    </v-tooltip>

                    <v-divider/>

                    <v-tooltip
                            nudge-left="10"
                            nudge-top="-20"
                            max-width="200"
                            top>
                        <template v-slot:activator="{ on }">
                            <div v-on="on" v-show="passos[2].desabilitado && !passos[1].desabilitado">
                                <v-stepper-step
                                        :complete="passos[2].numero < passoAtual.numero"
                                        :disabled="passos[2].desabilitado"
                                        :editable="!passos[2].desabilitado"
                                        :key="`${passos[2].numero}-header`"
                                        :step="passos[2].numero"
                                        @click="tratarEventoClick(passos[2])"
                                        edit-icon="fas fa-check"
                                        :class="{'passo-ativo' : !passos[2].desabilitado}"
                                >{{ passos[2].titulo }}
                                </v-stepper-step>
                            </div>
                            <v-stepper-step
                                    :complete="passos[2].numero < passoAtual.numero"
                                    :disabled="passos[2].desabilitado"
                                    :editable="!passos[2].desabilitado"
                                    :key="`${passos[2].numero}-header`"
                                    :step="passos[2].numero"
                                    @click="tratarEventoClick(passos[2])"
                                    edit-icon="fas fa-check"
                                    v-show="!passos[2].desabilitado || passos[1].desabilitado"
                                    :class="{'passo-ativo' : !passos[2].desabilitado}"
                            >{{ passos[2].titulo }}
                            </v-stepper-step>
                        </template>
                        É necessário que todos os campos obrigatórios sejam preenchidos para continuar.
                    </v-tooltip>

                    <v-spacer/>

                    <div>
                        <botao-excluir @excluir="abrirModalExcluir" color="grey" v-if="orcamentoId"/>
                        <confirm-modal
                                v-model="modalExcluir"
                                @modalAcaoCancelar="fecharModalExcluir"
                                @modalAcaoConfirmar="tratarEventoDeletarIncorporacao"
                                :persistencia="false"
                                mensagemInicial="Deseja realmente excluir o orçamento?"
                        />
                    </div>
                </v-stepper-header>

                <v-divider/>

                <v-stepper-items>
                    <v-stepper-content
                            :key="`${passo.numero}-content`"
                            :step="passo.numero"
                            v-for="passo in passos">
                    </v-stepper-content>
                    <router-view @desabilitaPasso2="desabilitaPasso2"
                                 @habilitaPasso2="habilitaPasso2"
                                 @desabilitaPasso3="desabilitaPasso3"
                                 @habilitaPasso3="habilitaPasso3"
                    />
                </v-stepper-items>
            </v-stepper>
        </container-component>
    </div>
</template>

<script>
import ContainerComponent from '@/views/components/Container'
import {actionTypes, mutationTypes} from '@/core/constants'
import {mapActions} from 'vuex'

export default {
    name: 'OrcamentoPassos',
    components: {ContainerComponent},
    data() {
        return {
            passos: [
                {
                    titulo: 'Paciente',
                    rotaPadrao: 'OrcamentoPaciente',
                    rotaVisualizacao: 'VisualizarOrcamentoPaciente',
                    rotaEdicao: 'OrcamentoPaciente',
                    desabilitado: false,
                    numero: 1,
                },
                {
                    titulo: 'Odontograma',
                    rotaPadrao: 'OrcamentoOdontograma',
                    rotaVisualizacao: 'VisualizarOrcamentoOdontograma',
                    rotaEdicao: 'OrcamentoOdontograma',
                    desabilitado: false,
                    numero: 2,
                },
                {
                    titulo: 'Finalização',
                    rotaPadrao: 'OrcamentoFinalizacao',
                    rotaVisualizacao: 'VisualizacaoOrcamentoFinalizacao',
                    rotaEdicao: 'OrcamentoFinalizacao',
                    desabilitado: false,
                    numero: 3,
                }
            ],
            modalExcluir: false,
            passoAtual: {},
            orcamentoId: null,
            rotaOrigem: this.$store.state.orcamento.rota.origem,
        }
    },
    watch: {
        $route() {
            this.setOrcamentoId()
            this.setPassoAtual(this.$route)
        },
    },
    async mounted() {
        this.retrairMenu()
        this.setOrcamentoId()
        this.setPassoAtual(this.$route)
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.EXCLUIR_ORCAMENTO
        ]),
        fecharModalExcluir() {
            this.modalExcluir = false
        },
        abrirModalExcluir() {
            this.modalExcluir = true
        },
        contralaAcessoAosPassosEmCadaRota() {
            if (this.$route.name === 'OrcamentoOdontograma' || this.$route.name === 'VisualizarOrcamentoOdontograma') {
                this.passos[1].desabilitado = false
                this.passos[2].desabilitado = false
            }
            if (this.$route.name === 'OrcamentoPaciente' || this.$route.name === 'VisualizarOrcamentoPaciente') {
                this.passos[1].desabilitado = false
                this.passos[2].desabilitado = true
            }
            if (this.$route.name === 'OrcamentoFinalizacao' || this.$route.name === 'VisualizacaoOrcamentoFinalizacao') {
                this.passos[0].desabilitado = false
                this.passos[1].desabilitado = false
                this.passos[2].desabilitado = false
            }
        },
        desabilitaPasso3() {
            this.passos[2].desabilitado = true
        },
        habilitaPasso3() {
            this.passos[2].desabilitado = false
        },
        desabilitaPasso2() {
            this.passos[1].desabilitado = true
        },
        habilitaPasso2() {
            this.passos[1].desabilitado = false
        },
        expandirMenu() {
            this.$store.commit(mutationTypes.DRAWER.SET_ASIDE, false)
        },
        retrairMenu() {
            this.$store.commit(mutationTypes.DRAWER.SET_ASIDE, true)
        },
        setOrcamentoId() {
            if (this.$route.params.orcamentoId) {
                this.orcamentoId = this.$route.params.orcamentoId
            }
        },
        setPassoAtual(route) {
            const encontrado = this.passos.filter(
                passo =>
                    passo.rotaPadrao === route.name ||
                        passo.rotaEdicao === route.name ||
                        passo.rotaVisualizacao === route.name
            )
            this.passoAtual = encontrado[0]
            this.contralaAcessoAosPassosEmCadaRota()
        },
        async tratarEventoDeletarIncorporacao() {
            await this.excluirOrcamento(this.orcamentoId)
            this.mostrarNotificacaoSucessoDefault()
            await this.$router.push({name: this.rotaOrigem})
        },
        tratarEventoClick(item) {
            if (item.desabilitado === false) {
                if (this.orcamentoId) {
                    this.$router.push({
                        name: item.rotaEdicao,
                        params: {orcamentoId: this.orcamentoId}
                    })
                } else {
                    this.$router.push({
                        name: this.rotaOrigem,
                    })
                }
            }
        }
    },
    destroyed() {
        this.expandirMenu()
    }
}
</script>

<style lang="stylus">

    .orcamento-container
        .v-stepper
            box-shadow none

            &__content
                padding 0 15% 0 60%

            &__step__step .v-icon.v-icon
                font-size .9rem

        .container
            background-color #fff

        .actions-form
            border-top solid thin rgba(0, 0, 0, .12)
            padding-top 10px
            margin-top 0

        &-conteudo
            min-height 350px

    .barra-passo
        display flex
        align-items center
        margin-right 1%

    .passo-ativo
        .v-stepper__step__step
            background-color #487b9c !important
            border-color #487b9c !important

</style>
