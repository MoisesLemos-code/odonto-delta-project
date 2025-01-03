<template>
  <container-component class="altura-componente-container">
    <form-table>
      <v-data-table
          slot="table"
          ref="table"
          :headers="colunas"
          :items="itens"
          :server-items-length="totalItens"
          :options.sync="paginacaoInterna"
          :loading="false"
          no-data-text="Não há orçamentos realizados"
          class="table-list"
          hide-default-footer
          @click:row="tratarEventoAcessar">
        <template v-slot:item.id="{item}">
          <span class="d-inline-block text-truncate max-10">{{ item.id | textoSemValor }}</span>
        </template>
        <template v-slot:item.paciente="{item}">
          <span class="d-inline-block text-truncate max-20">{{ item.paciente | textoSemValor }}</span>
        </template>
        <template v-slot:item.cpfOuCnpj="{item}">
          <span class="d-inline-block text-truncate max-15">{{ formatarCpfCnpj(item.cpfOuCnpj) | textoSemValor }}</span>
        </template>
        <template v-slot:item.status="{item}">
          <span class="d-inline-block text-truncate max-10">{{ item.status | filterEnum(statusOrcamento) }}</span>
        </template>
        <template v-slot:item.data_abertura="{item}">
          <span class="d-inline-block text-truncate max-10">{{ item.data_abertura | textoSemValor }}</span>
        </template>
        <template v-slot:item.acoes="{  }">
          <v-icon>keyboard_arrow_right</v-icon>
          <span class="mobile">Acessar</span>
        </template>
      </v-data-table>
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
    </form-table>
  </container-component>
</template>

<script>
import mutationTypes from '@/core/constants/mutationTypes'
import FormTable from '@/views/components/FormTable'
import ContainerComponent from '@/views/components/Container'
import statusOrcamento from '@/core/constants/enums/statusOrcamento'

export default {
    name: 'OrcamentoListagemTabela',
    components: {ContainerComponent, FormTable},
    props: ['itens', 'paginacao', 'paginas', 'totalItens'],
    data() {
        return {
            colunas: [
                {
                    text: 'Código',
                    value: 'id',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Nome',
                    value: 'paciente',
                    sortable: true,
                    align: 'left',
                    width: '20%',
                    class: 'gray--text'
                },
                {
                    text: 'CPF/CNPJ',
                    value: 'cpfOuCnpj',
                    sortable: true,
                    align: 'left',
                    width: '15%',
                    class: 'gray--text'
                },
                {
                    text: 'Status',
                    value: 'status',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Data de abertura',
                    value: 'data_abertura',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: '',
                    value: 'acoes',
                    sortable: false,
                    align: 'right',
                    width: '20%',
                    class: 'gray--text'
                }
            ],
            paginacaoInterna: this.paginacao,
            linhasPorPagina: [10, 25, 50, 100],
            statusOrcamento
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
            this.$store.commit(mutationTypes.ORCAMENTO.RESETA_PAGE)
        },
        formatarCpfCnpj(cpfCnpjValue) {
            if (cpfCnpjValue) {
                const cnpjCpf = cpfCnpjValue.replace(/\D/g, '')
                if (cnpjCpf.length === 11) {
                    return cnpjCpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, '$1.$2.$3-$4')
                }
                return cnpjCpf.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, '$1.$2.$3/$4-$5')
            }
            return ''
        }
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
