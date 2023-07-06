<template>
  <v-flex class="container-card-item">
      <v-hover v-slot="{ hover }">
      <v-card :elevation="hover ? 5 : 2" class="item">
        <v-container>
            <div class="text-head d-inline-block d-inline-block text-truncate max-width-item">
              <span class="text-info">{{ value.id | textoSemValor }}</span> |
              <span class="text-info">{{ value.nome | textoSemValor }}</span><br/>
            </div><br/>
            <span class="text-info d-inline-block d-inline-block text-truncate max-width-item">
              {{ value.nome_completo | textoSemValor }}</span><br/>
            <span class="text-info d-inline-block d-inline-block text-truncate max-width-item">
              {{ value.email | textoSemValor }}</span>
        </v-container>
        <v-overlay :value="hover" absolute>
          <div>
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" @click="editarUsuario" icon class="mr-5">
                  <v-icon size="18" >fas fa-pen</v-icon>
                </v-btn>
              </template>
              Editar usuário
            </v-tooltip>
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" @click="inserirPermissoes"  icon >
                  <v-icon size="20">fas fa-user-shield</v-icon>
                </v-btn>
              </template>
              Gerenciar permissões
            </v-tooltip>
          </div>
        </v-overlay>
      </v-card>
      </v-hover>
  </v-flex>
</template>

<script>
    export default {
        name: 'GerenciarUsuarioItem',
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
            editarUsuario(){
                this.$emit('editarUsuario', this.value)
            },
            inserirPermissoes(){
                this.$emit('permissaoUsuario', this.value)
            },
        }
    }
</script>


<style lang="stylus" scoped>

.container-card-item
  float left
  display inline-flex
  margin-right 10px
  margin-top 10px

.item
  width 300px
  border 1px solid #d9d9d9

.text-info
  color #777 !important

.text-head
  font-weight 600
  font-size 20px

.max-width-item
  max-width 280px

</style>