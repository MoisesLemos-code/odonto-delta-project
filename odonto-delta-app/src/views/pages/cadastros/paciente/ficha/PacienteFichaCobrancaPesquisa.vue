<template>
      <div class="container-filtros-pesquisa">
        <div class="container-text-descricao">
          <div class="simple-search">
            <div class="input-search">
              <input
                  :maxlength="200"
                  placeholder="Busque por: codigo, status ou data de vencimento..."
                  @keyup.enter="buscarFiltros()"
                  class="input-text"
                  v-model="filtros.conteudo.value"/>
            </div>

            <v-btn @click="buscarFiltros()" class="icon-search" color="primary" dark depressed fab small>
              <v-icon small>search</v-icon>
            </v-btn>
          </div>
        </div>
        <div class="container-mais-filtros" v-if="false">
          <date-input
              v-model="filtros.dataVencimento.value"
              name-date="dataVencimento"
              placeholderDate="Informe a data de vencimento">
            <template v-slot:label-date>
              Data vencimento
            </template>
          </date-input>
        </div>
      </div>
</template>

<script>
import DateInput from '@/views/components/DateInput.vue'

export default {
    name: 'PacienteFichaCobrancaPesquisa',
    components: {DateInput},
    props: ['filtros'],
    data() {
        return {
        }
    },
    methods: {
        buscarFiltros() {
            this.$emit('buscarComFiltros', this.filtros)
        },
    }
}
</script>

<style scoped lang="stylus">

.container-table-pesquisa
  display flex
  flex-direction row
  align-items center
  justify-content space-between
  height 75px
  padding-bottom 10px

.container-filtros-pesquisa
  width 100%
  display flex
  flex-direction row
  justify-content flex-end
  align-items center

.container-mais-filtros
  margin-right 20px
  >>> .v-input--switch
    margin 0px
    margin-top 3px
  >>> .v-label
    font-size 15px !important

.btn-buscar-lotes
  text-transform none
  font-weight 400
  width 200px
  height 40px
  margin 0px !important
  margin-left -1px
  margin-bottom 28px

.simple-search
  display flex

  .input-search
    display flex
    border 1px solid #ddd
    border-left none
    background-color #eee
    border-radius 20px 0 0 20px
    transition .2s
    align-items center

    .v-chip
      max-width none
      padding 0 10px
      overflow initial
      margin-left 5px

    .input-text
      height 30px
      padding 10px 15px
      width 100%
      outline none
      color #777777
      font-size 14px

  ::placeholder
    color #cccccc !important

  :-ms-input-placeholder
    color #cccccc !important

.icon-search
    border-radius 0 20px 20px 0
    width 33px
    height 32px
    margin 0 10px 0 0
    padding 0
    font-weight normal

@media (max-width: 950px)
  .pesquisa-descricao-textfield
    >>> .v-input__slot
      border-radius 4px
      border 1px solid #ced4da !important

@media (max-width 720px)
  .input-search
    width 100% !important


</style>