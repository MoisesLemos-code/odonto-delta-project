<template>
  <div>
    <v-layout column style="height: 50vh">
      <v-flex style="overflow-y: auto">
        <v-data-table
            slot="table"
            ref="table"
            :headers="colunas"
            :items="itens"
            :server-items-length="totalItens"
            :options.sync="paginacaoInterna"
            :loading="false"
            no-data-text="Não há peças cadastradas"
            class="table-list"
            hide-default-footer>
          <template v-slot:item.descricao="{item}">
            <span class="d-inline-block text-truncate max-20">{{ item.descricao | textoSemValor }}</span>
          </template>
          <template v-slot:item.valor="{item}">
            <span class="d-inline-block text-truncate max-15">{{ item.valor | valorParaReal }}</span>
          </template>
          <template v-slot:item.acoes="{item}">
            <v-btn icon class="no-mobile mr-2" @click="tratarEventoAcessar(item)">
              <v-icon size="25" class="mr-14">fa edit</v-icon>
            </v-btn>
            <botao-acao hide-border class="mobile" slot="actions" @click="tratarEventoAcessar(item)">
              Editar
            </botao-acao>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
    <div class="pagination" slot="footer">
      <v-pagination
          v-if="paginas > 1"
          v-model="paginacaoInterna.page"
          total-visible="7"
          :length="paginas"
      />
      <v-spacer v-if="paginas <= 1"/>
      <div class="select-pagination alinhamentoFiltragem">
        <span>Linhas por página:</span>
        <v-select :items="linhasPorPagina" @change="resetaPage" v-model="paginacaoInterna.rowsPerPage"/>
      </div>
    </div>
  </div>
</template>

<script>
    import mutationTypes from '@/core/constants/mutationTypes'
    import BotaoAcao from '@/views/components/BotaoAcao'

    export default {
        name: 'ModalGerenciarPecasTabela',
        components: {BotaoAcao},
        props: ['itens', 'paginacao', 'paginas', 'totalItens'],
        data() {
            return {
                colunas: [
                    {
                        text: 'Descrição',
                        value: 'descricao',
                        sortable: true,
                        align: 'left',
                        width: '55%',
                        class: 'gray--text'
                    },
                    {
                        text: 'Valor',
                        value: 'valor',
                        sortable: true,
                        align: 'left',
                        width: '20%',
                        class: 'gray--text'
                    },
                    {
                        text: '',
                        value: 'acoes',
                        sortable: false,
                        align: 'right',
                        width: '25%',
                        class: 'gray--text'
                    }
                ],
                paginacaoInterna: this.paginacao,
                linhasPorPagina: [5, 10, 25, 50, 100],
            }
        },
        methods: {
            tratarEventoAcessar(item) {
                this.$emit('acessar', item)
            },
            tratarPaginacao(pagina) {
                this.paginacaoInterna.page = pagina
            },
            resetaPage() {
                this.$store.commit(mutationTypes.ORCAMENTO.PECA.RESETA_PAGE)
            },
        },
        watch: {
            paginacaoInterna: {
                handler(novoValor) {
                    this.$emit('paginar', novoValor)
                },
                deep: true,
            },
        },
    }
</script>

<style scoped lang="stylus">

.pagination
  padding 0 10px

.max-10
  max-width 10vw

.max-15
  max-width 15vw

.max-20
  max-width 20vw

@media (max-width: 720px)
  .max-10, .max-15, .max-20
    max-width 40vw !important
</style>
