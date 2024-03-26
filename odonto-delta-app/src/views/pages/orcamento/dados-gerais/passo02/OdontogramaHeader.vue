<template>
    <div class="orcamento-odontograma-header">
        <odontograma-modal-quadrante
                v-if="quadranteModal"
                v-model="quadranteModal"
                @fecharQuadrante="fecharModalQuadrante"
        />
        <modal-gerenciar-servicos
                v-if="servicosModal"
                v-model="servicosModal"
                @fecharModalServicos="fecharModalServicos"
        />
        <modal-gerenciar-pecas
                v-if="pecasModal"
                v-model="pecasModal"
                @fecharModalPecas="fecharModalPecas"
            />
        <v-row wrap class="pa-0 ma-0">
            <div class="divisor-header">
                <v-tooltip top>
                    <template v-slot:activator={on}>
                        <v-btn v-on="on" icon @click="abrirModalQuadrante">
                            <v-icon color="white">mdi-compare</v-icon>
                        </v-btn>
                    </template>
                    Visualizar esquema de quadrantes
                </v-tooltip>
                <v-tooltip top>
                    <template v-slot:activator={on}>
                        <v-btn v-on="on" icon @click="alterarCoresQuadrante">
                            <v-icon color="white">{{coresQuadrante ? 'mdi-arrange-bring-forward' :
                                'mdi-arrange-send-backward'}}
                            </v-icon>
                        </v-btn>
                    </template>
                    Visualizar cores de quadrantes
                </v-tooltip>
            </div>
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <v-btn v-on="on" icon @click="abrirModalServicos">
                        <v-icon color="white">mdi-clipboard-text</v-icon>
                    </v-btn>
                </template>
                Gerenciar Serviços
            </v-tooltip>
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <v-btn v-on="on" icon @click="abrirModalPecas">
                        <v-icon color="white">mdi-hammer-screwdriver</v-icon>
                    </v-btn>
                </template>
                Gerenciar Peças
            </v-tooltip>
        </v-row>
    </div>
</template>

<script>
import OdontogramaModalQuadrante from './OdontogramaModalQuadrante'
import ModalGerenciarServicos from './servico/ModalGerenciarServicos'
import ModalGerenciarPecas from './peca/ModalGerenciarPecas'

export default {
    name: 'OdontogramaHeader',
    components: {ModalGerenciarPecas, ModalGerenciarServicos, OdontogramaModalQuadrante},
    props: {
        coresQuadrante: Boolean
    },
    data() {
        return {
            quadranteModal: false,
            servicosModal: false,
            pecasModal: false,
        }
    },
    methods: {
        abrirModalQuadrante() {
            this.quadranteModal = true
        },
        fecharModalQuadrante() {
            this.quadranteModal = false
        },
        abrirModalServicos() {
            this.servicosModal = true
        },
        fecharModalServicos() {
            this.servicosModal = false
        },
        abrirModalPecas() {
            this.pecasModal = true
        },
        fecharModalPecas() {
            this.pecasModal = false
        },
        alterarCoresQuadrante() {
            this.$emit('alterarCoresQuadrantes')
        }
    }
}
</script>

<style scoped lang="stylus">

    .orcamento-odontograma-header
        background-color #487B9C
        height 50px
        color white
        font-size 20px
        font-weight 400

        .divisor-header
            border-right 2px solid #30536A
            margin-right 5px
</style>