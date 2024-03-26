<template>
    <div class="container-servicos-realizados elevation-1">
        <v-data-table
                :headers="colunas"
                :items="itens"
                :items-per-page="5"
                no-data-text="Não há serviços realizados"
                class="table-list mt-2">
            <template v-slot:item.servico.descricao="{item}">
                <span class="d-inline-block text-truncate max-10">{{ item.servico.descricao | textoSemValor }}</span>
            </template>
            <template v-slot:item.denteItem="{item}">
                <span class="d-inline-block text-truncate max-30">{{ item.denteItem.dente.nome | textoSemValor }} ({{ item.denteItem.dente.numero | textoSemValor }}<span v-if="item.denteItem.dente.numero2">/{{ item.denteItem.dente.numero2 }}</span>)</span>
            </template>
            <template v-slot:item.localizacao="{item}">
                <span class="d-inline-block text-truncate max-10">{{ item.localizacao | filterEnum(facesDente) }}</span>
            </template>
            <template v-slot:item.valor="{item}">
                <span class="d-inline-block text-truncate max-10">{{ item.valor | valorParaReal }}</span>
            </template>
            <template v-slot:top>
                <span class="servicos-realizados-text">Serviços Realizados</span>
            </template>
            <template v-slot:footer>
                <span class="servicos-realizados-valor-text">Total: {{ valorTotal | valorParaReal }}</span>
            </template>
        </v-data-table>
    </div>
</template>

<script>
import facesDente from '@/core/constants/enums/facesDente'

export default {
    name: 'ServicosRealizadosTabela',
    props: ['itens', 'valorTotal'],
    data() {
        return {
            colunas: [
                {
                    text: 'Descrição',
                    value: 'servico.descricao',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Dente',
                    value: 'denteItem',
                    sortable: false,
                    align: 'left',
                    width: '30%',
                    class: 'gray--text'
                },
                {
                    text: 'Face',
                    value: 'localizacao',
                    sortable: true,
                    align: 'left',
                    width: '10%',
                    class: 'gray--text'
                },
                {
                    text: 'Valor',
                    value: 'valor',
                    sortable: true,
                    align: 'left',
                    width: '8%',
                    class: 'gray--text'
                },
            ],
            facesDente
        }
    }
}
</script>

<style scoped lang="stylus">

    .container-servicos-realizados
        border-radius 5px

    .servicos-realizados-text
        font-size 20px
        font-weight bold
        color #757575
        margin 10px

    .servicos-realizados-valor-text
        font-size 18px
        font-weight bold
        color #757575
        margin 10px

    .max-10
        max-width 10vw

    .max-30
        max-width 30vw

    @media (max-width: 720px)
        .max-10, .max-30
            max-width 40vw !important
</style>
