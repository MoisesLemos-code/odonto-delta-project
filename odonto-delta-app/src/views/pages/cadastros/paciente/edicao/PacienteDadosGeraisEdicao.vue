<template>
  <v-form class="form-content mt-0 pl-0 pr-0">
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
              v-model="dadosGerais.cnpjCpf"
              name="cpfCnpj"
              :placeholder="(dadosGerais.tipo === 1 ? 'Informe o CPF' : 'Informe o CNPJ')"
              v-mask="(dadosGerais.tipo === 1 ? masks.cpf : masks.cnpj)"
              v-validate="{required: true}"
              :error-messages="errors.collect('cpfCnpj')">
            <template v-slot:label>
              {{(dadosGerais.tipo === 1 ? 'CPF' : 'CNPJ')}}
              <span class="ml-1 red--text">*</span>
            </template>
          </v-text-field>
        </v-col>
        <v-col cols="12" md="3" sm="3" xs="12">
          <date-input
              v-model="dadosGerais.dataNascimento"
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
          />
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
              v-model="dadosGerais.telefone"
              name="contato principal"
              placeholder="Informe o telefone"
              v-mask="[masks.tel8, masks.tel9]"
              v-validate="{required: true}"
              :error-messages="errors.collect('contato principal')">
            <template v-slot:label>
              Telefone pra contato
              <span class="ml-1 red--text">*</span>
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
        <v-col cols="12" md="4" sm="4" xs="12">
          <buscar-municipio
                            :municipio-id="dadosGerais.municipioId"
                            :estado-id="dadosGerais.estadoId"
                            obrigatorio
                            @emitirSelecionarCidade="selecionarCidade"
          />
        </v-col>
      </v-row>
      <v-row wrap align-center white class="pl-10 pr-10">
        <v-col cols="12" md="3" sm="3" xs="12">
          <v-text-field
              v-model="dadosGerais.logradouro"
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
              v-model="dadosGerais.logradouroNumero"
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
import {actionTypes, mutationTypes} from '@/core/constants'
import {mapMutations} from 'vuex'
import moment from 'moment'
import DateInput from '@/views/components/DateInput'
import BuscarMunicipio from '@/views/components/BuscarMunicipio.vue'

export default {
    name: 'ClienteDadosGeraisEdicao',
    components: {BuscarMunicipio, DateInput},
    data() {
        return {
            dadosGerais: {
                nome: null,
                email: null,
                cnpjCpf: null,
                tipo: 1,
                telefone: null,
                logradouro: null,
                logradouroNumero: null,
                complemento: null,
                bairro: null,
                cep: null,
                municipioId: null,
                estadoId: null,
                dataNascimento: null,
                ortodontia: false,
                situacao: 'ATIVO',
            },
            masks: {
                cpf: '###.###.###-##',
                cnpj: '##.###.###/####-##',
                cep: '##.###-###',
                tel8: '(##) ####-####',
                tel9: '(##) #####-####'
            },
            pacienteId: null
        }
    },
    async mounted() {
        this.setarPacienteId()
        this.setarRotaOrigem()
        await this.buscarPaciente()
    },
    methods: {
        ...mapMutations([mutationTypes.CADASTROS.PACIENTE.SET_ROTA_ORIGEM]),
        async buscarPaciente() {
            const resultado = await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID, this.pacienteId)
            if (resultado) {
                this.dadosGerais = resultado
                this.setarTipo()
            }
        },
        formatarData() {
            if (this.dadosGerais.dataNascimento) {
                this.dadosGerais.dataNascimento = moment(this.dadosGerais.dataNascimento).add(5, 'hours').format('YYYY-MM-DD')
            }
        },
        selecionarCidade(municipioId) {
            this.dadosGerais.municipioId = municipioId
        },
        setarPacienteId() {
            if (this.$route.params.id) {
                this.pacienteId = this.$route.params.id
            }
        },
        setarRotaOrigem(){
            this.setRotaOrigem({name: 'PacienteFicha', params: {id: this.pacienteId}})
        },
        setarTipo() {
            if (this.dadosGerais.cnpjCpf.length === 11) {
                this.dadosGerais.tipo = 1
            } else {
                this.dadosGerais.tipo = 2
            }
        },
        tratarEventoCancelar() {
            this.$router.push({name: 'PacienteFicha', params: {id: this.pacienteId}})
        },
        async tratarEventoSalvar() {
            if (await this.validarDadosFormulario()) {
                if(this.municipioInvalido()){
                    this.mostrarNotificacaoErro('Informe a cidade!')
                    return
                }
                this.formatarData()
                this.setMensagemLoading('Salvando alterações do paciente...')
                await this.$store.dispatch(actionTypes.CADASTROS.PACIENTE.EDITAR_PACIENTE, this.dadosGerais)
                this.mostrarNotificacaoSucessoDefault()
                this.tratarEventoCancelar()
            }
        },
        municipioInvalido(){
            if(!this.dadosGerais.municipioId)
                return true
        },
        async validarDadosFormulario() {
            return this.$validator._base.validateAll()
        },
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