<template>
    <v-form class="form-content mt-0 pl-0 pr-0 altura-componente-container">
        <v-container fluid grid-list-xl white>
            <confirm-modal
                    v-model="modalExcluir"
                    @modalAcaoCancelar="fecharModalExcluir"
                    @modalAcaoConfirmar="excluirPacienteSelecionado"
                    :persistencia="false"
                    mensagemInicial="Deseja realmente excluir o paciente "
                    :mensagemMeio="dadosGerais.nome"
                    mensagemFinal="?"/>
            <paciente-ficha-cabecalho :paciente="dadosGerais" @abrirModalExcluir="abrirModalExcluir"
                                      @editarPaciente="editarPaciente" @relatorioFichaPaciente="imprimirRelatorioFichaPaciente"/>
            <paciente-ficha-dados-gerais :paciente="dadosGerais"/>
            <paciente-ficha-detalhes v-if="fichaPaciente" :ficha-paciente="fichaPaciente"  @salvarFicha="tratarEventoSalvarFicha"
                                     @cancelarEdicaoFicha="tratarEventoCancelarEdicaoFicha"/>
            <paciente-ficha-cobrancas />
            <paciente-ficha-orcamentos v-if="false"/>
        </v-container>
    </v-form>
</template>

<script>
import {actionTypes, mutationTypes} from '@/core/constants'
import {mapMutations} from 'vuex'
import PacienteFichaCabecalho from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCabecalho'
import ConfirmModal from '@/views/components/ConfirmModal'
import PacienteFichaDadosGerais from '@/views/pages/cadastros/paciente/ficha/PacienteFichaDadosGerais'
import PacienteFichaDetalhes from '@/views/pages/cadastros/paciente/ficha/PacienteFichaDetalhes'
import PacienteFichaOrcamentos from './PacienteFichaOrcamentos'
import PacienteFichaCobrancas from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCobrancas.vue'

export default {
    nome: 'PacienteFicha',
    components: {
        PacienteFichaCobrancas,
        PacienteFichaOrcamentos,
        PacienteFichaDetalhes, PacienteFichaDadosGerais, ConfirmModal, PacienteFichaCabecalho
    },
    data() {
        return {
            dadosGerais: {
                nome: null,
                cidade: null,
                estado: null,
                ficha: {
                    historiaMedica: {}
                }
            },
            fichaPaciente: null,
            pacienteId: null,
            modalExcluir: false
        }
    },
    async mounted() {
        this.setarRotaOrigem()
        await this.buscarPaciente()
        await this.buscarFicha()
    },
    methods: {
        ...mapMutations([mutationTypes.CADASTROS.PACIENTE.SET_ROTA_ORIGEM]),
        setarRotaOrigem(){
            this.setRotaOrigem({name: 'PacienteListagem'})
        },
        async buscarPaciente() {
            if (this.$route.params.id) {
                const resposta = await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID, this.$route.params.id)
                if (resposta) {
                    this.dadosGerais = resposta
                    this.pacienteId = this.dadosGerais.id
                }
            }
        },
        async buscarFicha(){
            if (this.$route.params.id) {
                const resposta = await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.FICHA.BUSCAR_POR_PACIENTE, this.$route.params.id)
                if (resposta) {
                    this.fichaPaciente = resposta
                }
            }
        },
        async tratarEventoSalvarFicha(dados) {
            dados = {...dados, pacienteId: this.pacienteId}
            await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.FICHA.EDITAR_FICHA, dados)
        },
        tratarEventoCancelarEdicaoFicha(fichaAntiga){
            this.fichaPaciente = fichaAntiga  
        },
        fecharModalExcluir() {
            this.modalExcluir = false
        },
        abrirModalExcluir() {
            this.modalExcluir = true
        },
        async imprimirRelatorioFichaPaciente(){
            await this.$store.dispatch(actionTypes.RELATORIOS.GERAR_RELATORIO_FICHA_PACIENTE, this.pacienteId)
        },
        async excluirPacienteSelecionado() {
            await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.EXCLUIR_PACIENTE, this.$route.params.id)
            this.fecharModalExcluir()
            this.$router.push({name: this.$store.state.paciente.rotaOrigem})
            this.mostrarNotificacaoSucessoDefault()
        },
        editarPaciente() {
            this.$router.push({name: 'PacienteEdicao', params: this.pacienteId})
        }
    }
}
</script>

<style scoped lang="stylus">

    .ficha-header
        background-color #487B9C
        padding 5px 20px

    .head-nome
        color white
        font-size 24px
        font-weight 400

    .ficha-header-actions
        text-align end

    .btn-excluir
        margin-left 15px

</style>