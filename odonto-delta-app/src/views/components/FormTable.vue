<template>
  <div class="form">
    <div class="form-table">
    <slot name="table"/>
    </div>
    <div class="form-footer">
       <slot name="footer"/>
    </div>
  </div>
</template>

<script>
    export default {
        name: 'form-table'
    }
</script>

<style lang="stylus">
.action-link-icon
  text-decoration none
.table-actions
  text-align -webkit-center
.table-list
  tr
    color #777
    height 48px
  td:hover
    cursor pointer
  .v-select__selection
    font-size 13px
    color #777
.pagination
  display flex
  justify-content space-between
  font-size 13px
  .v-pagination
    justify-content left
    width auto
  .v-pagination__item
    height 24px
    width 24px
    font-size 13px
    margin 2px
    border 1px solid #ccc
    -webkit-box-shadow none
    box-shadow none
  .v-pagination__navigation
    height 24px
    width 24px
    font-size 13px
    margin 2px
    -webkit-box-shadow none
    box-shadow none
    background transparent !important
    .v-icon
      font-size 15px
    &--disabled
      display none
  .v-input
    width 100px
  .select-pagination
    align-items center
    display flex
    color #777777
    span
      color #777777
      margin-right 10px
    .v-select
      font-size 13px
      max-width 60px
    .v-select__selection--comma
      font-size 13px
      margin 10px 5px 5px 0 !important
.form
  height 100%
  padding 20px
.form-content-table
  padding 20px
  width 100%
  height 100%
  margin-top 10px
  .v-input__slot
    &:before
      border-color #ccc !important
  .text
    margin-bottom 8px
    label, legend
      color #777 !important
      font-weight bold
      font-size 13px
    legend
      width 100%
    p
      color #888 !important
      font-size 13px
  .v-text-field
    .v-label
      color #777 !important
      font-weight bold
      font-size 13px
      overflow unset
      pointer-events auto
      top 0
    input:-webkit-autofill
      background-color white !important
    input
      font-size 13px
      color #888 !important
    .v-label--active
      -webkit-transform translateY(-18px)
      transform translateY(-18px)
  .v-select__selection--comma
    font-size 13px
    color #888 !important
  .checkbox-label
    font-size 13px
    font-weight bold
    color #777 !important
  .v-input__icon i
    font-size 18px
  .checkbox
    i
      font-size 14px
      margin-bottom 3px
    .v-input--selection-controls__ripple
      cursor pointer
      height 20px
      width 20px
      left -1px
      top calc(50% - 10px)
    .v-input--selection-controls__input
      margin-right 0
      width 18px
    .accent--text
      color #777 !important
    .v-input
      margin-top 0 !important
      padding 0
      .v-label
        font-size 13px
        color #888 !important
      .accent--text
        label
          font-size 13px
          color #888 !important
  .v-textarea textarea
    font-size 13px
    color #888 !important
  .subtitle
    font-size 16px
    font-weight bold
    margin 10px 0
    border-bottom 1px solid #ccc
  .v-input--selection-controls
    margin-top 0 !important
    padding-top 0 !important
    legend
      margin-bottom 10px
  .v-radio
    label
      font-weight normal !important
      top 0 !important
  .v-file-input__text
    font-size 13px
    color #888 !important
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
@media (max-width 720px)
  .pagination
    display block !important
    .select-pagination
      height 10px
    .v-pagination
      height 50px
</style>