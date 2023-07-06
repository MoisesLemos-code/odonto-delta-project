<template>
    <v-btn
            id="botaoSalvar"
            color="primary"
            depressed
            :disabled="disabled"
            @click="$emit('salvar')">
        Salvar
    </v-btn>
</template>

<script>
    export default {
        name: 'BotaoSalvar',
        props:{
            disabled:{
                type: Boolean,
                default: false
            }
        }
    }
</script>