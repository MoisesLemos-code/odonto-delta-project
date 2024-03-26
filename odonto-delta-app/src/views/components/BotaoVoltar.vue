<template>
    <v-btn color="primary" text depressed @click="voltar">
        <v-icon>chevron_left</v-icon>
        {{ text }}
    </v-btn>
</template>

<script>
export default {
    name: 'BotaoVoltar',
    props: {
        text: {
            type: String,
            required: true
        },
        route: Object
    },
    methods: {
        voltar() {
            if(this.route){
                this.$router.push(this.route)
                return
            }
            window.history.back()
        }
    }
}
</script>

<style scoped lang="stylus">
    .v-btn
        font-size 14px
        margin 0
        text-transform none
        position relative
        height unset
        padding 5px 15px
        box-shadow none
        font-weight bold

        i
            margin-right 5px
            font-size 18px
</style>