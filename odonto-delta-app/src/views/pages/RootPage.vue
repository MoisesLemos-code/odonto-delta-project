<template>
    <div>
        <div v-if="telaUnica">
            <v-app>
                <az-alert />
                <router-view/>
            </v-app>
        </div>
    <az-template-default v-else showMainHeader class="secondary">
        <router-view/>
    </az-template-default>
    </div>
</template>

<script>

export default {
    computed:{
        telaUnica() {
            return this.$route.meta.independente
        }
    }
}
</script>

<style lang="stylus">
    flowbee-logo-color = #cdd1d5
    flowbee-text-color = #777

    .container-height
        min-height 62vh

    .btn-disabled
        pointer-events visible !important
        cursor not-allowed

    .fb-tabela-tokens .active
    .fb-tabela-categorias .active
    .fb-tabela-repositorios .active
        background-color transparent !important

    .fb-text-color
        color flowbee-text-color

    html, body, .application
        font-family 'Open Sans', sans-serif

    .az-template-default
        background-color black

    .notification i.white--text
        color rgb(119, 119, 119) !important

    table.v-table thead tr
        height auto !important

    .az-table-list tbody tr
        border-bottom 1px solid #e9e9e9

        td
            font-weight 400
            height 40px
            color #777
            font-size 13px

    .az-menu
        .v-list
            &__tile__action i
                font-size 15px
                color flowbee-logo-color

            &__tile__title
                font-weight bold

    .az-container .az-table-list thead tr:first-child th
        white-space normal

    .az-logo
        .main
            width 80%

        .symbol
            width 60%

    .table-actions
        cursor pointer !important
        text-align center !important

        a
            color #777

        i
            font-size 16px

    .az-aside, .az-aside.primary
        background-color var(--v-secondary-base) !important

    .pr-area
        padding 20px 10px
        text-align center

    .pr-acoes-formulario
        padding 0

    .pr-titulo
        color #777
        margin-bottom 10px

    .az-form-content
        margin-top 0

        .v-text-field
            .v-label
                font-size 14px !important
                top -5px

        .az-date
            .v-text-field
                .v-label
                    top 0

        .v-input--radio-group__input
            .v-label
                font-size 14px !important
                top -5px

        .col
            padding 8px 12px !important

        .v-textarea textarea
            max-height 100px
            overflow-y auto

    @media (max-width: 720px)
        .az-pagination .az-select-pagination
            height 50px
</style>
