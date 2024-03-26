<template>
    <v-form class="form-content mt-0 pl-0 pr-0">
        <modal-gerenciar-cidades v-model="modalCidades" @fechar="fecharModalCidades"/>
        <v-container fluid grid-list-xl white>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.nome"
                            name="nome"
                            placeholder="Informe o nome completo"
                            counter="100"
                            maxlength="100"
                            v-validate="{required: true, min: 5, max: 100}"
                            :error-messages="errors.collect('nome')">
                        <template v-slot:label>
                            Nome
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.email"
                            name="email"
                            placeholder="Informe o e-mail"
                            counter="100"
                            maxlength="100"
                            v-validate="{max: 100, email: true}"
                            :error-messages="errors.collect('email')">
                        <template v-slot:label>
                            Email
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="2" sm="2" xs="12" class="radio-col">
                    <label class="radio-tipo">
                        Tipo de Pessoa
                    </label>
                    <v-radio-group class="radio-group" v-model="dadosGerais.tipo">
                        <v-radio label="Pessoa Física" :value="1"/>
                        <v-radio label="Pessoa Jurídica" :value="2"/>
                    </v-radio-group>
                </v-col>
                <v-col cols="12" md="4" sm="4" xs="12">
                    <v-text-field
                            v-model="dadosGerais.cpfOuCnpj"
                            name="cpfCnpj"
                            :placeholder="(dadosGerais.tipo === 1 ? 'Informe o CPF' : 'Informe o CNPJ')"
                            v-mask="(dadosGerais.tipo === 1 ? masks.cpf : masks.cnpj)"
                            v-validate="{required: true}"
                            :error-messages="errors.collect('cpfCnpj')">
                        <template v-slot:label>
                            {{ (dadosGerais.tipo === 1 ? 'CPF' : 'CNPJ') }}
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <date-input
                            v-model="dadosGerais.dataAniversario"
                            name-date="dataNascimento"
                            date
                            placeholderDate="Informe a data de nascimento">
                        <template v-slot:label-date>
                            Nascimento
                        </template>
                    </date-input>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-switch
                            v-model="dadosGerais.ortodontia"
                            label="Ortodontia"
                    ></v-switch>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="12" sm="12" xs="12">
                    <v-divider :inset="false" class="divider"></v-divider>
                    <span class="section-label">Contatos</span>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="4" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.contatoPrincipal"
                            name="contato principal"
                            placeholder="Informe o telefone principal"
                            v-mask="[masks.tel8, masks.tel9]"
                            v-validate="{required: true}"
                            :error-messages="errors.collect('contato principal')">
                        <template v-slot:label>
                            Contato principal
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="4" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.contato2"
                            name="contato2"
                            placeholder="Informe o segundo contato"
                            v-mask="[masks.tel8, masks.tel9]">
                        <template v-slot:label>
                            Segundo contato
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="4" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.contato3"
                            name="contato3"
                            placeholder="Informe o terceiro contato"
                            v-mask="[masks.tel8, masks.tel9]">
                        <template v-slot:label>
                            Terceiro contato
                        </template>
                    </v-text-field>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="12" sm="12" xs="12">
                    <v-divider :inset="false" class="divider"></v-divider>
                    <span class="section-label">Endereço</span>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.cep"
                            name="CEP"
                            placeholder="Informe o CEP"
                            v-mask="masks.cep"
                            v-validate="{required: true, max: 100}"
                            :error-messages="errors.collect('CEP')">
                        <template v-slot:label>
                            CEP
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-autocomplete
                            v-model="dadosGerais.cidadeId"
                            name="cidade"
                            placeholder="Selecione a cidade"
                            :items="cidades"
                            item-text="nome"
                            item-value="id"
                            :filter="filtroComboAutoComplete"
                            v-validate="{required: true}"
                            :error-messages="errors.collect('cidade')">
                        <template v-slot:label>
                            Cidade
                            <span class="ml-1 red--text">*</span>
                        </template>
                        <template v-slot:item="data">
                            <label class="auto-complete-item-text">{{ data.item.nome }}</label>
                        </template>
                        <template v-slot:append-item>
                            <botao-acao
                                    class="btn-gerenciar-cidades"
                                    @click="abrirModalCidades">
                                <v-icon size="16" color="gray">
                                    fas fa-cog
                                </v-icon>
                                <label class="btn-text-gerenciar-cidades">Gerenciar Cidades</label>
                            </botao-acao>
                        </template>
                    </v-autocomplete>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10">
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.rua"
                            name="rua"
                            placeholder="Informe o nome da rua"
                            counter="100"
                            maxlength="100"
                            v-validate="{required: true, max: 100}"
                            :error-messages="errors.collect('rua')">
                        <template v-slot:label>
                            Rua
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.numero"
                            name="número"
                            placeholder="Informe o número"
                            counter="50"
                            maxlength="50"
                            v-validate="{required: true, max: 50, numeric: true}"
                            :error-messages="errors.collect('número')">
                        <template v-slot:label>
                            Número
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.complemento"
                            name="complemento"
                            placeholder="Informe o complemento"
                            counter="100"
                            maxlength="100"
                            v-validate="{max: 100}"
                            :error-messages="errors.collect('complemento')">
                        <template v-slot:label>
                            Complemento
                        </template>
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="3" sm="3" xs="12">
                    <v-text-field
                            v-model="dadosGerais.bairro"
                            name="bairro"
                            placeholder="Informe o bairro"
                            counter="100"
                            maxlength="100"
                            v-validate="{required: true, max: 100}"
                            :error-messages="errors.collect('bairro')">
                        <template v-slot:label>
                            Bairro
                            <span class="ml-1 red--text">*</span>
                        </template>
                    </v-text-field>
                </v-col>
            </v-row>
            <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
                <botao-cancelar @cancelar="tratarEventoCancelar"/>

                <botao-salvar @salvar="tratarEventoSalvar"/>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import moment from 'moment'
import BotaoAcao from '@/views/components/BotaoAcao'
import ModalGerenciarCidades from '@/views/modais/cidades/ModalGerenciarCidades'
import DateInput from '@/views/components/DateInput'

export default {
    name: 'ClienteDadosGeraisEdicao',
    components: {DateInput, ModalGerenciarCidades, BotaoAcao},
    data() {
        return {
            dadosGerais: {
                nome: null,
                email: null,
                cpfOuCnpj: null,
                tipo: 1,
                contatoPrincipal: null,
                contato2: null,
                contato3: null,
                rua: null,
                numero: null,
                complemento: null,
                bairro: null,
                cep: null,
                cidadeId: null,
                dataAniversario: null,
                ortodontia: null,
                status: 1
            },
            masks: {
                cpf: '###.###.###-##',
                cnpj: '##.###.###/####-##',
                cep: '##.###-###',
                tel8: '(##) ####-####',
                tel9: '(##) #####-####'
            },
            tiposPessoa: [
                {codigo: 1, nome: 'Pessoa Física'},
                {codigo: 2, nome: 'Pessoa Jurídica'}
            ],
            cidades: [],
            pacienteId: null,
            modalCidades: false
        }
    },
    async mounted() {
        await this.buscarPaciente()
        await this.buscarCidades()
    },
    methods: {
        ...mapActions([
            actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES_SEM_PAGINACAO,
            actionTypes.CADASTROS.PACIENTE.EDITAR_PACIENTE,
            actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID
        ]),
        async buscarCidades() {
            const resultado = await this.buscarTodasCidadesSemPaginacao()
            if (resultado) {
                this.cidades = resultado
            }
        },
        async buscarPaciente() {
            if (this.$route.params.id) {
                const resultado = await this.buscarPacientePorId(this.$route.params.id)
                if (resultado) {
                    this.dadosGerais = resultado
                    this.pacienteId = this.dadosGerais.id
                    this.setarCidade()
                    this.setarTipo()
                    this.setarStatus()
                }
            }
        },
        setarCidade() {
            this.dadosGerais.cidadeId = this.dadosGerais.cidade.id
        },
        setarTipo() {
            if (this.dadosGerais.tipo === 'PESSOAFISICA') {
                this.dadosGerais.tipo = 1
            } else if (this.dadosGerais.tipo === 'PESSOAJURIDICA') {
                this.dadosGerais.tipo = 2
            }
        },
        setarStatus() {
            if (this.dadosGerais.status === 'SEM_PENDENCIAS') {
                this.dadosGerais.status = 1
            } else if (this.dadosGerais.status === 'COM_PARCELAS') {
                this.dadosGerais.status = 2
            } else if (this.dadosGerais.status === 'ATRASADO') {
                this.dadosGerais.status = 3
            }
        },
        filtroComboAutoComplete(item, queryText) {
            const text = item.nome.toLowerCase()
            const searchText = queryText.toLowerCase()
            return text.indexOf(searchText) > -1
        },
        formatarData() {
            if (this.dadosGerais.dataAniversario) {
                this.dadosGerais.dataAniversario = moment(this.dadosGerais.dataAniversario).format('YYYY-MM-DD')
            }
        },
        tratarEventoCancelar() {
            this.$router.push({name: 'PacienteFicha', params: { id: this.pacienteId}})
        },
        async tratarEventoSalvar() {
            if (await this.validarDadosFormulario()) {
                this.formatarData()
                this.setMensagemLoading('Salvando alterações do paciente...')
                await this.editarPaciente(this.dadosGerais)
                this.pacienteId = this.$store.state.paciente.dadosGerais.id
                this.mostrarNotificacaoSucessoDefault()
                this.tratarEventoCancelar()
            }
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
        abrirModalCidades() {
            this.modalCidades = true
        },
        async fecharModalCidades() {
            this.modalCidades = false
            await this.buscarCidades()
        }
    }
}
</script>

<style scoped lang="stylus">

    .row-bottom
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc
        box-sizing border-box
        margin-top 5%
        padding-top 10px
        padding-bottom 10px
        display flex
        justify-content space-between

    .radio-col
        padding 0 12px

    .radio-tipo
        margin 0
        color #777 !important

    .radio-group
        margin-top 0

    .auto-complete-item-text
        font-size 14px !important
        color #555 !important

    .btn-gerenciar-cidades
        border none
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc
        border-radius 0
        width 100%
        box-sizing border-box
        justify-content start

    .btn-text-gerenciar-cidades
        margin-left 5px
        font-weight bold
        font-size 14px !important
        color white !important

</style>