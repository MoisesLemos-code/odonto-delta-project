<template>
    <container-component>
        <form-table style="padding: 0px; margin-top: 10px">
            <v-data-table
                    slot="table"
                    ref="table"
                    :headers="colunas"
                    :items="itens"
                    :server-items-length="totalItens"
                    :options.sync="paginacaoInterna"
                    :loading="false"
                    no-data-text="Não há cobranças registradas para este paciente"
                    class="table-list"
                    hide-default-footer
                    @click:row="tratarEventoAcessar">
                <template v-slot:item.codigo="{item}">
                    <span class="d-inline-block text-truncate max-10">{{ item.codigo | textoSemValor }}</span>
                </template>
                <template v-slot:item.valorTotal="{item}">
                    <span class="d-inline-block text-truncate max-20">{{ item.valorTotal | valorParaReal }}</span>
                </template>
              <template v-slot:item.valorPago="{item}">
                <span class="d-inline-block text-truncate max-20">{{ item.valorPago | valorParaReal }}</span>
              </template>
                <template v-slot:item.status="{item}">
                    <span :class="['d-inline-block text-truncate max-15', 'status-cobranca-' + item.status]">{{ item.status | filterEnum(statusCobranca)}}</span>
                </template>
                <template v-slot:item.dataVencimento="{item}">
                  <v-tooltip top v-if="item.isVencido">
                    <template v-slot:activator={on}>
                      <span class="d-inline-block text-truncate max-10 cobranca-vencida" v-on="on">{{ item.dataVencimento | filterDate }}</span>
                    </template>
                    Cobrança vencida!
                  </v-tooltip>
                    <span class="d-inline-block text-truncate max-10" v-else>{{ item.dataVencimento | filterDate }}</span>
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
                    <v-select :items="linhasPorPagina" @change="resetaPage" v-model="paginacaoInterna.size"/>
                </div>
            </div>
        </form-table>
    </container-component>
</template>

<script>
import mutationTypes from '@/core/constants/mutationTypes'
import FormTable from '@/views/components/FormTable'
import ContainerComponent from '@/views/components/Container'
import {statusCobranca} from '@/core/constants'

export default {
    name: 'PacienteFichaCobrancasTabela',
    components: {ContainerComponent, FormTable},
    props: ['itens', 'paginacao', 'paginas', 'totalItens'],
    data() {
        return {
            colunas: [
                {
                    text: 'Código',
                    value: 'codigo',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Valor',
                    value: 'valorTotal',
                    sortable: true,
                    align: 'left',
                    width: '20%',
                    class: 'gray--text'
                },
                {
                    text: 'Valor Pago',
                    value: 'valorPago',
                    sortable: true,
                    align: 'left',
                    width: '20%',
                    class: 'gray--text'
                },
                {
                    text: 'Status',
                    value: 'status',
                    sortable: true,
                    align: 'left',
                    width: '15%',
                    class: 'gray--text'
                },
                {
                    text: 'Vencimento',
                    value: 'dataVencimento',
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
            statusCobranca
        }
    },
    methods: {
        tratarEventoAcessar(item) {
            this.$emit('acessar', item)
        },
        resetaPage() {
            this.$store.commit(mutationTypes.COBRANCA.RESETA_PAGE)
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
