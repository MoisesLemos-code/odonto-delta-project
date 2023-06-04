<template>
    <span v-if="this.decrescente" class="base-timer-label decrescente-timer">{{ tempoFormatadodecrescente }}</span>
    <span v-else class="base-timer-label contador-timer">{{ tempoFormatadoContador }}</span>
</template>

<script>
export default {
    name: 'ContadorTempo',
    props: {
        tempo: {
            type: Number,
            default: 120
        },
        decrescente: {
            type: Boolean,
            default: false
        },
        recebeSegundos:{
            type: Boolean,
            default: false
        },
        tempoDecorrido:{
            typoe: Number,
            default: null
        }
    },
    data() {
        return {
            tempoOrigem: 0,
            tempoPassado: 0,
            timerInterval: null
        }
    },
    mounted() {
        if(this.recebeSegundos){
            this.tempoOrigem = this.tempo
        }else{
            this.converterMinutosParaSegundos()
        }

        if(this.tempoDecorrido != null){
            this.tempoPassado = this.tempoDecorrido
        }
        this.iniciarTempo()
        window.addEventListener('resize', this.verificarResize, false)
    },
    computed: {
        tempoFormatadodecrescente() {
            const timeLeft = this.tempodecrescente
            return this.formatarTempo(timeLeft)
        },
        tempoFormatadoContador() {
            const timeLeft = this.tempoContador
            return this.formatarTempo(timeLeft)
        },
        tempodecrescente() {
            let subtracao = this.tempoOrigem - this.tempoPassado
            if(subtracao > 0){
                return  subtracao
            }
            this.tempoZerado()
            return 0
        },
        tempoContador() {
            return this.tempoPassado
        }
    },
    watch: {
        tempo: {
            handler: function (newValue) {
                this.tempoOrigem = newValue
                this.tempoPassado = 0
            },
            immediate: true
        }

    },
    methods: {
        verificarResize() {
            location.reload()
            return false
        },
        converterMinutosParaSegundos(){
            this.tempoOrigem = this.tempo * 60
        },
        tempoZerado() {
            this.$emit('timerZero')
            clearInterval(this.timerInterval)
        },
        tempoIniciado(){
            this.$emit('timerAtivado')
        },
        iniciarTempo() {
            this.timerInterval = setInterval(() => (this.tempoPassado += 1), 1000)
        },
        formatarTempo(input) {
            let hours   = Math.floor(input / 3600)
            let minutes = Math.floor((input - (hours * 3600)) / 60)
            let seconds = input - (hours * 3600) - (minutes * 60)

            if (hours   < 10) {hours   = '0'+hours}
            if (minutes < 10) {minutes = '0'+minutes}
            if (seconds < 10) {seconds = '0'+seconds}

            if(hours > 0)
                return `${hours}:${minutes}:${seconds}`
            return `${minutes}:${seconds}`
        },
    }
}
</script>

<style scoped lang="stylus">

    .base-timer-label
        font-size 14px

    .decrescente-timer
        color red

    .contador-timer
        color #666

</style>
