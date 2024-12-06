<template>
<div :class="{ 'container-municipio-em-linha': emLinha, 'container-municipio-em-coluna': !emLinha }">
  <v-autocomplete
      v-model="estadoSelecionado"
      label="Estado"
      :items="estados"
      item-text="nome"
      item-value="id"
      :filter="filtroComboAutoCompleteEstado"
      v-on:change="buscarCidades"
      class="select-estados"
      />
  <v-autocomplete
      v-model="municipioSelecionado"
      name="cidade"
      placeholder="Selecione a cidade"
      :items="cidades"
      item-text="nome"
      item-value="id"
      class="select-municipios"
      :filter="filtroComboAutoComplete"
      v-on:change="emitirSelecionarCidade"
      :v-validate="{required: obrigatorio}"
      :error-messages="errors.collect('cidade')">
    <template v-slot:label>
      Cidade
      <span v-if="obrigatorio" class="ml-1 red--text">*</span>
    </template>
    <template v-slot:item="{item}">
      <label class="auto-complete-item-text">{{ item.nome }}</label>
    </template>
  </v-autocomplete>
</div>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'buscarMunicipio',
    props: {
        estadoId:{
            type: Number
        },
        municipioId:{
            type: Number
        },
        obrigatorio: {
            type: Boolean,
            default: false
        },
        emLinha: {
            type: Boolean,
            default: true
        }
    },
    data(){
        return{
            estadoSelecionado: null,
            municipioSelecionado: null,
            estados: [],
            cidades: []
        }
    },
    async mounted() {
        this.setarMunicipio()
        await this.setarEstado()
        await this.buscarEstados()
    },
    methods:{
        ...mapActions([
            actionTypes.CADASTROS.ESTADO.BUSCAR_TODOS_ESTADOS_SEM_PAGINACAO,
            actionTypes.CADASTROS.CIDADE.BUSCAR_TODAS_CIDADES_SEM_PAGINACAO,
        ]),
        async setarEstado(){
            if(this.estadoId > 0){
                this.estadoSelecionado = this.estadoId
                await this.buscarCidades()
            }
        },
        setarMunicipio(){
            if(this.municipioId > 0){
                this.municipioSelecionado = this.municipioId
            }
        },
        async buscarEstados(){
            const resultado = await this.buscarTodosEstadosSemPaginacao()
            if (resultado) {
                this.estados = resultado.items
            }
        },
        async buscarCidades() {
            if(this.estadoSelecionado){
                const resultado = await this.buscarTodasCidadesSemPaginacao({idEstado: this.estadoSelecionado})
                if (resultado) {
                    this.cidades = resultado.items
                }
                const cidade = this.cidades.find( item => item.id === this.municipioSelecionado)
                if(!cidade)
                    this.municipioSelecionado = null
                this.emitirSelecionarCidade()
                this.emitirSelecionarEstado()
            }
        },
        emitirSelecionarEstado(){
            this.$emit('emitirSelecionarEstado', this.estadoSelecionado)
        },
        emitirSelecionarCidade(){
            this.$emit('emitirSelecionarCidade', this.municipioSelecionado)
        },
        filtroComboAutoComplete(item, queryText) {
            const text = item.nome.toLowerCase()
            const searchText = queryText.toLowerCase()
            return text.indexOf(searchText) > -1
        },
        filtroComboAutoCompleteEstado(item, queryText) {
            const text = item.nome.toLowerCase()
            const sigla = item.sigla.toLowerCase()
            const searchText = queryText.toLowerCase()
            return text.indexOf(searchText) > -1 || sigla.indexOf(searchText) > -1
        },
    }
}
</script>

<style scoped lang="stylus">
.container-municipio-em-linha
  display flex
  flex-direction row

.container-municipio-em-coluna
  display flex
  flex-direction column

.select-estados
  max-width 210px
  margin-right 30px
.select-municipios
  max-width 210px
.call-to-action
  font-size 14px
  margin 0
  text-transform none
  position relative
  height unset
  padding 5px 15px
  box-shadow none
  font-weight bold

  i
    margin-right 5px
    font-size 18px

  &.hide-border
    border 0 !important
</style>