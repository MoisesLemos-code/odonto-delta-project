<template>
    <div class="actions-form">
        <v-btn
                @click="voltar"
                color="grey"
                text
                outlined>
            <v-icon size="15" class="mr-1">fas fa-arrow-left</v-icon>
            VOLTAR
        </v-btn>
        <v-btn
                @click="finalizar"
                v-if="final"
                color="primary"
                depressed>
            Finalizar
            <v-icon size="15" class="ml-1">fas fa-check</v-icon>
        </v-btn>
        <v-btn
                @click="continuar"
                v-if="controleContinuar && !final"
                color="primary"
                depressed>
            Continuar
            <v-icon size="15" class="ml-1">fas fa-arrow-right</v-icon>
        </v-btn>
        <v-tooltip
                v-if="!controleContinuar && !final"
                top
                nudge-top="30"
                max-width="200">
            <template v-slot:activator="{ on }">
                <div v-on="on" :class="{'btn-disabled': !controleContinuar}">
                    <v-btn
                            :disabled="!controleContinuar"
                            color="primary"
                            depressed>
                        Continuar
                        <v-icon size="15" class="ml-1">fas fa-arrow-right</v-icon>
                    </v-btn>
                </div>
            </template>
            É necessário que todos os campos obrigatórios sejam preenchidos para continuar.
        </v-tooltip>
    </div>
</template>

<script>
    export default {
        name: 'AcoesOrcamento',
        props: ['controleContinuar', 'final'],
        methods: {
            voltar() {
                this.$emit('voltar')
            },
            continuar() {
                this.$emit('continuar')
            },
            finalizar() {
                this.$emit('finalizarOrcamento')
            }
        }
    }
</script>

<style lang="stylus">

</style>
