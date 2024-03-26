<template>
    <container-component>
        <form-table style="padding: 0px; padding-right: 20px">
            <v-data-table
                    slot="table"
                    ref="table"
                    :headers="colunas"
                    :items="itens"
                    :server-items-length="totalItens"
                    :options.sync="paginacaoInterna"
                    :loading="false"
                    no-data-text="Não há serviços adicionados"
                    class="table-list"
                    hide-default-footer>
                <template v-slot:item.id="{item}">
                    <span class="d-inline-block text-truncate">{{ item.id | textoSemValor }}</span>
                </template>
                <template v-slot:item.servico.descricao="{item}">
                    <span class="d-inline-block text-truncate max-210">{{ item.servico.descricao | textoSemValor }}</span>
                </template>
                <template v-slot:item.localizacao="{item}">
                    <span class="d-inline-block text-truncate">{{ item.localizacao | filterEnum(facesDente) }}</span>
                </template>
                <template v-slot:item.valor="{item}">
                    <span class="d-inline-block text-truncate">{{ item.valor | valorParaReal }}</span>
                </template>
                <template v-slot:item.acoes="{item}">
                    <botao-editar color="grey" @editar="editarServico(item)"/>
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
                    <v-select :items="linhasPorPagina" v-model="paginacaoInterna.rowsPerPage"/>
                </div>
            </div>
        </form-table>
    </container-component>
</template>

<script>
import FormTable from '@/views/components/FormTable'
import ContainerComponent from '@/views/components/Container'
import facesDente from '@/core/constants/enums/facesDente'
import BotaoEditar from '@/views/components/BotaoEditar'

export default {
    name: 'ServicosListagemTabela',
    components: {BotaoEditar, FormTable, ContainerComponent},
    props: ['itens', 'paginacao', 'paginas', 'totalItens'],
    data() {
        return {
            colunas: [
                {
                    text: 'Código',
                    value: 'id',
                    sortable: true,
                    align: 'left',
                    width: '90px',
                    class: 'gray--text'
                },
                {
                    text: 'Descrição',
                    value: 'servico.descricao',
                    sortable: false,
                    align: 'left',
                    width: '200px',
                    class: 'gray--text'
                },
                {
                    text: 'Localização',
                    value: 'localizacao',
                    sortable: true,
                    align: 'left',
                    width: '120px',
                    class: 'gray--text'
                },
                {
                    text: 'Valor',
                    value: 'valor',
                    sortable: true,
                    align: 'left',
                    width: '50px',
                    class: 'gray--text'
                },
                {
                    text: '',
                    value: 'acoes',
                    sortable: false,
                    align: 'right',
                    width: '20px',
                    class: 'gray--text'
                }
            ],
            paginacaoInterna: this.paginacao,
            linhasPorPagina: [5, 10, 25, 50, 100],
            facesDente
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
    methods: {
        editarServico(item) {
            this.$emit('editarServico', item)
        }
    },
}
</script>

<style scoped lang="stylus">
    .max-210
        max-width 210px

    @media (max-width: 720px)
        .max-210
            max-width 40vw !important
</style>