<template>
        <v-toolbar-items class="toolbar-items">
            <v-tooltip bottom>
                <template v-slot:activator={on}>
                    <v-btn icon v-on="on" class="btn-sair desktop" @click="logout()">
                        <v-icon>mdi-exit-to-app</v-icon>
                    </v-btn>
                </template>
                Sair do sistema
            </v-tooltip>
        </v-toolbar-items>
</template>

<script>
    import {mutationTypes} from '@/core/constants'

    export default {
        name: 'core-toolbar',
        data: () => ({
            notifications: [],
        }),
        methods:{
            logout() {
                this.$store.commit(mutationTypes.COMUM.EFETUAR_LOGOUT)
                this.$router.push({ name: 'Login' })
            }
        }
    }
</script>

<style lang="stylus" scoped>
.toolbar-items
  display flex
  align-items center

.toolbar-icon
  text-decoration none

.btn-sair
  margin-left 20px
  color #bbb
  letter-spacing 1px
  font-size 16px !important
  font-weight bold
  word-spacing 1px
  text-transform capitalize
@media (max-width 720px)
  .desktop
     display none
</style>