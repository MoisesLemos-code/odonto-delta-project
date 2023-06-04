export default {
    methods: {
        criarLinkDownload(uri) {
            return `${this.$store.state.loki.file.api}${uri}`
        },

        criarLinkDownloadArquivo(id) {
            return `api/arquivos/${id}/download`
        },

        async downloadArquivo(arquivoId, nome) {
            const url = this.criarLinkDownloadArquivo(arquivoId)
            const btn = document.createElement('a')
            btn.setAttribute('href', url)
            btn.setAttribute('download', nome)
            document.body.appendChild(btn)
            btn.click()
            btn.remove()
        }
    }
}
