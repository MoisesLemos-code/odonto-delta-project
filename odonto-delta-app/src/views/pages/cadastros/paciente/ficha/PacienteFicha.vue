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
                                      @editarPaciente="editarPaciente"/>
            <paciente-ficha-dados-gerais :paciente="dadosGerais"/>
            <paciente-ficha-detalhes v-if="pacienteId" :pacienteId="pacienteId" @salvar="tratarEventoSalvarFicha"/>
            <paciente-ficha-orcamentos/>
        </v-container>
    </v-form>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import PacienteFichaCabecalho from '@/views/pages/cadastros/paciente/ficha/PacienteFichaCabecalho'
import ConfirmModal from '@/views/components/ConfirmModal'
import PacienteFichaDadosGerais from '@/views/pages/cadastros/paciente/ficha/PacienteFichaDadosGerais'
import PacienteFichaDetalhes from '@/views/pages/cadastros/paciente/ficha/PacienteFichaDetalhes'
import PacienteFichaOrcamentos from './PacienteFichaOrcamentos'

export default {
    nome: 'PacienteFicha',
    components: {
        PacienteFichaOrcamentos,
        PacienteFichaDetalhes, PacienteFichaDadosGerais, ConfirmModal, PacienteFichaCabecalho
    },
    data() {
        return {
            dadosGerais: {
                nome: null,
                cidade: {
                    nome: null,
                    estado: { sigla: null }
                },
                ficha: {
                    historiaMedica: {}
                }
            },
            pacienteId: null,
            modalExcluir: false
        }
    },
    async mounted() {
        await this.buscarPaciente()
    },
    methods: {
        ...mapActions([
            actionTypes.CADASTROS.PACIENTE.EDITAR_PACIENTE,
            actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID,
            actionTypes.CADASTROS.PACIENTE.EXCLUIR_PACIENTE,
            actionTypes.CADASTROS.PACIENTE.FICHA.EDITAR_FICHA,
        ]),
        async buscarPaciente() {
            if (this.$route.params.id) {
                const resposta = await this.buscarPacientePorId(this.$route.params.id)
                if (resposta) {
                    this.dadosGerais = resposta
                    this.pacienteId = this.dadosGerais.id
                    this.setarCidade()
                }
            }
        },
        async tratarEventoSalvarFicha(dados) {
            dados = {...dados, id: this.pacienteId}
            await this.editarFicha(dados)
        },
        setarCidade() {
            this.dadosGerais.cidadeId = this.dadosGerais.cidade.id
        },
        fecharModalExcluir() {
            this.modalExcluir = false
        },
        abrirModalExcluir() {
            this.modalExcluir = true
        },
        async excluirPacienteSelecionado() {
            await this.excluirPaciente(this.pacienteId)
            this.fecharModalExcluir()
            this.$router.push({name: this.$route.meta.rotaOrigem})
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