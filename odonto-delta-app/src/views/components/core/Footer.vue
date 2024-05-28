<template>
    <v-footer app inset dark>
        <v-spacer/>
       <span v-if="false">
         <router-link to="ajuda" class="copyright footer-items">Precisa de ajuda?</router-link>
      </span>
        <span class="copyright mr-2">{{$store.state.comum.nomeSistema}}  -</span>
        <span class="copyright">&copy; 2023</span>
      <v-img :src="require('@/images/Icon_dente_01.png')" max-height="24" max-width="24"/>
    </v-footer>
</template>

<script>
export default {
    name: 'core-footer'
}
</script>

<style lang="stylus" scoped>
    .footer-items
        margin-right 20px

    .copyright
      color #ccc
      letter-spacing 1px
      font-size 16px !important
      font-weight 300
      word-spacing 1px
      text-transform capitalize
</style>