<template>
    <v-dialog v-model="value" max-width="720" scrollable  @click:outside="fecharModal">
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Visualização do esquema de quadrantes
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="pa-0">
                    <v-img :src="require('@/images/odontograma/numeracao_quadrante.png')" aspect-ratio="1.1"/>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>

export default {
    name: 'OdontogramaModalQuadrante',
    props: {
        value: Boolean
    },
    methods: {

        fecharModal() {
            this.$emit('fecharQuadrante')
        },
    }
}
</script>

<style scoped>

</style>