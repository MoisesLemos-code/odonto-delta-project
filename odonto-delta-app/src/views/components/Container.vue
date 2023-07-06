<template>
  <div class="container-component">
    <slot />
  </div>
</template>

<script>
    export default {
        name: 'container-component'
    }
</script>

<style scoped lang="stylus">
.container-component .table-list thead tr th
  padding 0 10px !important
.primary--text
  color #777777 !important
.v-list
  padding 0
  &-item
    color #7f7f7f
    height 38px
    &__title
      font-size 14px
    &__action
      min-width unset
      padding-right 10px
      .material-icons
        font-size 13px
.container-component.grid-list-xl
  padding 0
.container-component.grid-list-xl .layout .flex
  padding 0 10px
.tabs
  margin 20px
  .v-tabs__div
    text-transform none
  .v-tabs__item--active
    font-weight bold
    border-bottom 1px solid white
    border-left 1px solid #ccc
    border-right 1px solid #ccc
    border-top 1px solid #ccc
    border-radius 2px
  .v-tabs__item
    color #777777
    padding 10px 20px
  .v-tabs__slider-wrapper
    display none
  .v-tabs__container-component
    border-bottom 1px solid #ccc
  .v-text-field__slot
    font-size 14px
  .v-select__selections
    font-size 14px
.v-content__wrap
  background-color #eee
.container-component
  background-color white !important
  padding 0px
  .table-list.v-data-table
    thead
      border none
      tr:first-child
        height 35px !important
        border-bottom 2px solid #e9e9e9 !important
      tr th
        font-weight bold
        padding 0 10px !important
</style>