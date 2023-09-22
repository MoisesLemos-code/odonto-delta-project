<template>
  <v-flex class="container-card-item" :id="index + '-'">
      <v-hover v-slot="{ hover }">
      <v-card :elevation="hover ? 5 : 2" class="item">
        <v-container>
            <div class="text-head d-inline-block d-inline-block text-truncate max-width-item">
              <span class="text-info">{{ value.id | textoSemValor }}</span> |
              <span class="text-info">{{ value.nome | textoSemValor }}</span><br/>
            </div><br/>
            <span class="text-info d-inline-block d-inline-block text-truncate max-width-item">
              {{ value.descricao | textoSemValor }}</span><br/>
        </v-container>
        <v-overlay :value="hover" absolute>
          <div>
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" @click="editarItem" icon class="mr-5">
                  <v-icon size="18" >fas fa-pen</v-icon>
                </v-btn>
              </template>
              Editar perfil
            </v-tooltip>
          </div>
        </v-overlay>
      </v-card>
      </v-hover>
  </v-flex>
</template>

<script>
export default {
    name: 'GerenciarPerfilCard',
    props: {
        value: {
            default: () => {
                return {}
            },
        },
        index: {
            type: Number,
        },
    },
    methods: {
        editarItem(){
            this.$emit('editarItem', this.value)
        },
    }
}
</script>


<style lang="stylus" scoped>

.item
  border 1px solid #d9d9d9

.text-info
  color #777 !important

.text-head
  font-weight 600
  font-size 20px

.max-width-item
  max-width 99%

</style>