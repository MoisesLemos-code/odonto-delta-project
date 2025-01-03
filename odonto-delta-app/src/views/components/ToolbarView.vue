<template>
  <div class="toolbar">

  <div class="container-toolbar">
    <div class="actions">
      <slot name="actions"></slot>
    </div>
    <div class="actions-second">
      <slot name="actions-second"></slot>
    </div>
  </div>

    <div class="search">
      <slot name="simpleSearch"></slot>
    </div>
  </div>
</template>

<script>
import mutationTypes from '@/core/constants/mutationTypes'
export default {
    name: 'ToolbarView',
    data() {
        return {
            isClosed: true
        }
    },
    methods: {
        toggle() {
            this.isClosed = !this.isClosed
            this.closeAsideMenu()
        },
        close() {
            this.isClosed = true
        },
        closeAsideMenu() {
            this.$store.commit(mutationTypes.DRAWER.SET_ASIDE, true)
        }
    }
}
</script>

<style scoped lang="stylus">
.v-toolbar__content
  padding: 0 20px
.toolbar
  width: 100%
  height: 90px
  background-color: #f6f9f9
  border-bottom: 1px solid #ddd
  align-items: center
  display: flex
  justify-content: space-between
  .search
    top: 15px
    display: flex
    .clear
      margin-right: 10px
      padding: 5px
      color: #777777
  .ripple__container
    display: none
  .actions
    padding: 0 20px
    a
      font-size: 14px
      padding: 7px 10px
      border-radius: 5px
      margin-right: 10px
      i
        margin-right: 5px
        font-size: 18px
.container-toolbar
  display flex
  @media (max-width 720px)
    .actions
      padding: 0 5px 0 10px
</style>