<template>
    <form-table>
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
</template>

<script>
    import FormTable from '@/views/components/FormTable'
    import mutationTypes from '@/core/constants/mutationTypes'

    export default {
        name: 'GerenciarUsuarioListagem',
        components: { FormTable},
        props: ['paginacao', 'paginas', 'totalItens'],
        data() {
            return {
                paginacaoInterna: this.paginacao,
                linhasPorPagina: [6, 10, 25, 50, 100],
            }
        },
        methods: {
            tratarPaginacao(pagina) {
                this.paginacaoInterna.page = pagina
            },
            resetaPage() {
                this.$store.commit(mutationTypes.USUARIO.RESETA_PAGE)
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

</style>