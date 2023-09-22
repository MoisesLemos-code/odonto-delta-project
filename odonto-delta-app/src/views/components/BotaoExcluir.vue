<template>
    <v-btn
            id="botaoExcluir"
            outlined
            :disabled="disabled"
            icon
            :color="color"
            class="botao-excluir"
            @click="$emit('excluir')">
        <v-icon>mdi-delete</v-icon>
    </v-btn>
</template>

<script>
export default {
    name: 'BotaoExcluir',
    props: {
        disabled: {
            type: Boolean,
            default: false
        },
        color: {
            type: String,
            default: 'white'
        }
    }
}
</script>

<style lang="stylus" scoped>

    .botao-excluir:hover
        color red !important
</style>
