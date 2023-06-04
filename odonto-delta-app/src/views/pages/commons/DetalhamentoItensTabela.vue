<template>
  <v-dialog v-model="exibirModal" max-width="800" scrollable @click:outside="$emit('fechar')">
    <v-card id="modal-detalhe-itens">
      <v-toolbar dark color="#3a6861" elevation="0">
        <v-toolbar-title><h4>{{ tituloLote }}</h4></v-toolbar-title>
        <v-spacer/>
        <v-btn class="close__button" text @click="$emit('fechar')" icon>
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
                  Item
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
              <tr v-for="item in itens" :key="item.name">
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
import {actionTypes} from '@/core/constants'

export default {
    name: 'DetalhamentoItensTabela',
    props: {
        exibirModal:{
            type: Boolean,
            value: false
        },
        tituloLote:{
            type: String
        },
        loteId: Number,
        pregaoId: String
    },
    data() {
        return {
            itens: []
        }
    },
    async mounted() {
        this.ajustarBarraLateral('modal-detalhe-itens')
        await this.buscarItensLote()
    },
    methods:{
        async buscarItensLote(){
            const response = await this.$store.dispatch(actionTypes.FORNECEDOR.BUSCAR_ITENS_LOTE, {idLote: this.loteId, idPregao: this.pregaoId})
            if(response){
                this.itens = response.items
            }
        }
    }
}
</script>

<style lang="stylus">
.container-table-detalhes
    margin 20px

.modal-detalhes-text
  padding 0px !important
  overflow-x hidden
  height auto

.table-detalhe-header
  background-color #eee

.table-detalhe-coluna
  border-right 1px solid #ccc

.table-detalhe-lote
  border 1px solid #ccc
</style>