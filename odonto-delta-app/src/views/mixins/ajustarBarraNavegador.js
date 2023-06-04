
export default {
    methods: {
        ajustarBarraLateral(elementoId) {
            let elemento = document.getElementById(elementoId)
            if(!elemento) return
            const scrollHeight = elemento.scrollHeight
            elemento.scrollTop = scrollHeight
            try{
                window.parent.scroll(0, scrollHeight)
            }catch (e){
                console.log('----- FALHA AO AJUSTAR BARRA LATERAL DO ELEMENTO: ' + elementoId)
                console.log(e)
            }
        },

    }
}
