<template>
    <v-dialog v-model="value" max-width="970" scrollable persistent>
        <v-card id="modal-detalhes">
            <v-toolbar dark color="#3a6861" elevation="0">
                <v-toolbar-title style="width: 100%">
                  <h4 class="d-inline-block text-truncate max-w">Itens - {{ item.descricao }}</h4>
                </v-toolbar-title>
                <v-spacer />
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-detalhes-text">
                <div class="container-table-detalhes">
                    <v-simple-table class="table-detalhe-lote">
                        <template v-slot:default>
                            <thead class="table-detalhe-header">
                                <tr>
                                    <th class="text-left table-detalhe-coluna">
                                        Itens
                                    </th>
                                    <th class="text-left table-detalhe-coluna">
                                        Descrição
                                    </th>
                                    <th class="text-left">
                                        Quantidade
                                    </th>
                                </tr>
                            </thead>
                            <tbody style="color: #666">
                                <tr v-for="item in itens" :key="item.id">
                                    <td class="table-detalhe-coluna">{{ item.numSequencia }}</td>
                                    <td class="table-detalhe-coluna">{{ item.itemCompra.descricao }}</td>
                                    <td>{{ item.quantidade }}</td>
                                </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </div>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import { actionTypes } from '@/core/constants'
import { mapActions } from 'vuex'

export default {
    name: 'ModalDetalhesLote',
    props: {
        value: Boolean,
        item: {
            type: Object
        },
        pregaoId: String
    },
    data() {
        return {
            itens: []
        }
    },
    async mounted() {
        this.ajustarBarraLateral('modal-detalhes')
        await this.buscarItensDoLoteSelecionado()
    },
    methods: {
        ...mapActions([actionTypes.FORNECEDOR.BUSCAR_ITENS_LOTE]),
        async buscarItensDoLoteSelecionado() {
            const { items } = await this.buscarItensLote({ idLote: this.item.idLote, idPregao: this.pregaoId })
            this.itens = items
        },
        fecharModal() {
            this.$emit('fecharModalDetalhes')
        }
    }
}
</script>

<style scoped lang="stylus">

.max-w
  max-width 90%

.container-table-detalhes
  margin 20px

.modal-detalhes-text
  padding 0px !important
  overflow-x hidden
  height auto

.table-detalhe-lote
  border 1px solid #ccc

.table-detalhe-header
  background-color #eee

  tr th
    font-size 14px !important

.table-detalhe-coluna
  border-right 1px solid #ccc
</style>
