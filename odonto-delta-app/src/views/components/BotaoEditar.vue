<template>
    <v-btn
            id="botaoEditar"
            :outlined="outlined"
            :disabled="disabled"
            icon
            :color="color"
            class="botao-editar"
            @click="$emit('editar')">
        <v-icon>mdi-pencil</v-icon>
    </v-btn>
</template>

<script>
export default {
    name: 'BotaoEditar',
    props:{
        disabled: {
            type: Boolean,
            default: false
        },
        outlined: {
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

</style>
