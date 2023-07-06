<template>
    <div class="faces-dente">
        <div class="container-face-top">
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <div v-on="on"
                         :class="{ 'face-item-grande': tipoEstilo === 'grande', 'face-item': tipoEstilo === 'normal', 'face-ativa': lingual}"
                         @click="selecionarFace('Lingual/Palatal')"/>
                </template>
                Lingual/Palatal
            </v-tooltip>
        </div>
        <div class="container-face-mid">
            <v-tooltip left>
                <template v-slot:activator={on}>
                    <div v-on="on"
                         :class="{ 'face-item-grande': tipoEstilo === 'grande', 'face-item': tipoEstilo === 'normal', 'face-ativa': distal}"
                         @click="selecionarFace('Distal')"/>
                </template>
                Distal
            </v-tooltip>
            <v-tooltip top>
                <template v-slot:activator={on}>
                    <div v-on="on"
                         :class="{ 'face-item-grande': tipoEstilo === 'grande', 'face-item': tipoEstilo === 'normal', 'face-ativa': oclusal}"
                         @click="selecionarFace( 'Oclusal')"/>
                </template>
                Oclusal
            </v-tooltip>
            <v-tooltip right>
                <template v-slot:activator={on}>
                    <div v-on="on"
                         :class="{ 'face-item-grande': tipoEstilo === 'grande', 'face-item': tipoEstilo === 'normal', 'face-ativa': mesial}"
                         @click="selecionarFace( 'Mesial')"/>
                </template>
                Mesial
            </v-tooltip>
        </div>
        <div class="container-face-bot">
            <v-tooltip bottom>
                <template v-slot:activator={on}>
                    <div v-on="on"
                         :class="{ 'face-item-grande': tipoEstilo === 'grande', 'face-item': tipoEstilo === 'normal', 'face-ativa': vestibular}"
                         @click="selecionarFace('Vestibular')"/>
                </template>
                Vestibular
            </v-tooltip>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'OdontogramaFaces',
        props: {
            tipoEstilo: {
                type: String,
                default: 'normal'
            },
            ativar: Boolean,
            face: String
        },
        data() {
            return {
                lingual: false,
                distal: false,
                oclusal: false,
                mesial: false,
                vestibular: false
            }
        },
        mounted() {
            this.ativarFace(this.face)
        },
        methods: {
            selecionarFace(face) {
                if (this.ativar) {
                    this.$emit('faceSelecionada', this.ativarFace(face))
                    this.desativarFace(face)
                } else {
                    this.$emit('faceSelecionada', face)
                }
            },
            ativarFace(face) {
                if (face === 'Lingual/Palatal') {
                    if (this.lingual) {
                        this.lingual = false
                        return null
                    } else {
                        this.lingual = true
                        return face
                    }
                }
                if (face === 'Distal') {
                    if (this.distal) {
                        this.distal = false
                        return null
                    } else {
                        this.distal = true
                        return face
                    }
                }
                if (face === 'Oclusal') {
                    if (this.oclusal) {
                        this.oclusal = false
                        return null
                    } else {
                        this.oclusal = true
                        return face
                    }
                }
                if (face === 'Mesial') {
                    if (this.mesial) {
                        this.mesial = false
                        return null
                    } else {
                        this.mesial = true
                        return face
                    }
                }
                if (face === 'Vestibular') {
                    if (this.vestibular) {
                        this.vestibular = false
                        return null
                    } else {
                        this.vestibular = true
                        return face
                    }
                }
            },
            desativarFace(face) {
                if (face !== 'Lingual/Palatal') this.lingual = false
                if (face !== 'Distal') this.distal = false
                if (face !== 'Oclusal') this.oclusal = false
                if (face !== 'Mesial') this.mesial = false
                if (face !== 'Vestibular') this.vestibular = false
            }
        }
    }
</script>

<style scoped lang="stylus">

    .faces-dente
        width 100%
        height 30px
        background-color white

    .container-face-top
        display flex
        flex-direction row
        justify-content center

    .container-face-mid
        display flex
        flex-direction row
        justify-content center

    .container-face-bot
        display flex
        flex-direction row
        justify-content center

    .face-item
        height 10px
        width 15px
        border 1px solid #487B9C

    .face-item:hover
        cursor pointer
        background-color #487B9C

    .face-item-grande
        height 25px
        width 45px
        border 1px solid #487B9C

    .face-item-grande:hover
        cursor pointer
        background-color #487B9C

    .face-ativa
        background-color #487B9C
</style>