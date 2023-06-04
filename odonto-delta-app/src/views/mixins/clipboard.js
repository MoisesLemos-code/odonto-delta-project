export default {
    methods: {
        copiarParaAreaDeTransferencia(conteudo) {
            const textArea = document.createElement('textarea')
            textArea.setAttribute('readonly', '')
            textArea.style.position = 'absolute'
            textArea.style.left = '-9999px'
            textArea.value = conteudo

            document.body.appendChild(textArea)
            textArea.select()
            document.execCommand('copy')
            document.body.removeChild(textArea)
        }
    }
}
