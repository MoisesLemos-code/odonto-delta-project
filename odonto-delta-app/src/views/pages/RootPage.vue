<template>
  <core-template-default v-if="this.$route.meta.requiresAuth === true"/>
  <v-app v-else>
    <notificacao />
    <loading />
    <router-view />
  </v-app>
</template>

<script>
import Loading from '@/views/components/Loading'
import {mutationTypes} from '@/core/constants'
import Notificacao from '@/views/components/Notificacao'
export default {
    name: 'RootPage',
    components: {Notificacao, Loading},
    mounted() {
        this.onResponsiveInverted()
    },
    methods: {
        onResponsiveInverted() {
            if (window.innerWidth <= 720) {
                this.$store.commit(mutationTypes.DRAWER.SET_ASIDE_HIDE, false)
            }
        }
    }
}
</script>

<style lang="stylus">
    .menu
        width 100%
        height 100%
        display flex
        flex-direction row

    .menu-content
        width 100%
        height 100%
        display flex
        flex-direction column

    html, body, .application
        font-family 'Open Sans', sans-serif

    .pagination
      display flex
      align-items center

    .altura-componente-container
      min-height 50vh

    .btn-disabled
      pointer-events visible !important
      cursor not-allowed

    .erro-inicializacao
      width 100%
      padding 0
      text-align center
      color #555
      height 80vh
      display flex
      justify-content center
      align-items center

      i
        font-size 36px

      p
        font-size 18px
        margin 10px 0 0
        font-weight 600

    .actions-form
      padding 0 20px 20px
      margin-top 20px
      justify-content space-between
      display flex
      font-size 13px

      .align-left
        .action-secundary
          padding 10px 15px
          margin-right 10px
          color #555555
          border 1px solid #555555
          border-radius 2px
          &:hover
            color white
            border 1px solid #555555
            background-color #555555
        .action-delete
          padding 10px 15px
          margin-right 10px
          color #cccccc
          border 1px solid #cccccc
          border-radius 2px
          &:hover
            color white
            border 1px solid #a03232
            background-color #a03232
      .align-right
        .action-primary
          padding 10px 15px
          border 1px solid #7aa329
          background-color #7aa329
          color white
          margin-left 10px
          border-radius 2px
          &:hover
            background-color lighten(#7aa329, 10%)
            border 1px solid lighten(#7aa329, 10%)
        .action-secundary
          padding 10px 15px
          margin-left 10px
          color #777777
          &:hover
            color darkred

    .form-content
      margin-top 0

      .section-label
        font-size 20px !important
        color #777 !important
        font-weight 400

      .v-text-field
        .v-label
          font-size 20px !important
          color #777 !important
          top -5px
          height 25px

      .az-date
        .v-text-field
          .v-label
            top 0

      .v-input--radio-group__input
        .v-label
          font-size 14px !important
          color #777 !important

      .col
        padding 8px 12px !important

      .v-textarea textarea
        max-height 100px
        overflow-y auto

    .v-tooltip__content
      padding 10px
      font-size 12px
      opacity 1 !important
      background #333333 !important
      text-align justify !important
      line-height 1.4

    .v-tooltip__content.menuable__content__active
      max-width 500px

    @keyframes fade
      from
        opacity 0
        transform scale(0.5)
      to
        opacity 1
        transform scale(1)

    @keyframes moveX
      from
        opacity 0
        transform translateX(-35%)
      to
        opacity 1
        transform translateX(0%)

    @keyframes moveXRight
      from
        opacity 0
        transform translateX(95%)
      to
        opacity 1
        transform translateX(0%)

    @keyframes moveY
      from
        opacity 0
        transform translateY(-35%)
      to
        opacity 1
        transform translateY(0%)

    @keyframes moveYBack
      from
        opacity 0
        transform translateY(95%)
      to
        opacity 1
        transform translateY(0%)

</style>
