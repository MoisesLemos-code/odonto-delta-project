<template>
    <div class="chat-legado-container">
        <div class="chat-legado-header">
            <label>Mensagens</label>
        </div>
        <div class="chat-legado-lotes-info">
            <span class="mr-2">Lote(s):</span>
            <div :key="lote.idLote" v-for="lote in lotesSelecionados">
                <span class="mr-2">{{ lote.sequencia }}, </span>
            </div>
        </div>
        <div class="chat-legado-text">
            <v-text-field
                    v-model="mensagem"
                    placeholder="Digite aqui..."
                    dense
                    outlined
                    clearable
                    :maxlength="100"
                    class="input-text"
                    @keyup.enter.native="enviarMensagem"
            />
            <v-btn
                    class="chat-legado-btn"
                    :class="{ 'btn-disabled': !mensagem }"
                    @click="enviarMensagem"
                    :disabled="!mensagem"
            >
                Enviar Mensagem
            </v-btn>
        </div>
        <div class="chat-legado-filtro">
            <span class="mr-4 mt-3">Filtrar Mensagem do Lote:</span>
            <div class="chat-legado-select-container">
                <v-select
                        v-model="filtroSelecionado"
                        :items="prefixoSelect"
                        item-text="sequencia"
                        item-value="sequencia"
                        dense
                        outlined
                />
            </div>
        </div>
        <div class="chat-legado-table-container">
            <v-data-table
                    :headers="colunasTabela"
                    :items="mensagensRecebidas"
                    hide-default-footer
                    :items-per-page="500"
                    no-data-text="Não há mensagens enviadas..."
                    :search="filtroSelecionado"
                    :custom-filter="filtrarPorLote"
                    class="chat-legado-table elevation-1"
            >
                <template v-slot:item.loteSequencia="{ item }">
                    <span v-if="item.tipoEvento === 'CANCELAMENTO_LANCE'"
                          class="texto-vermelho">{{ item.loteSequencia }}</span>
                    <span v-if="item.tipoEvento === 'SOLICITACAO_CANCELAMENTO_LANCE' || item.tipoEvento === 'MENSAGEM_FORNECEDOR'"
                          class="texto-laranja">{{ item.loteSequencia }}</span>
                  <span v-if="item.tipoEvento === 'MENSAGEM_SERVIDOR'" class="texto-verde">{{ item.loteSequencia }}</span>
                  <span v-if="item.tipoEvento === 'LANCE_FORNECEDOR'">{{ item.loteSequencia }}</span>
                  <span v-if="!item.tipoEvento">{{ item.loteSequencia }}</span>
                </template>
                <template v-slot:item.dataEvento="{ item }">
                    <span v-if="item.tipoEvento === 'CANCELAMENTO_LANCE'"
                          class="texto-vermelho">{{ item.dataEvento | valorParaData('DD/MM/YYYY HH:mm:ss') }}</span>
                   <span v-if="item.tipoEvento === 'SOLICITACAO_CANCELAMENTO_LANCE' || item.tipoEvento === 'MENSAGEM_FORNECEDOR'" class="texto-laranja">
                     {{ item.dataEvento | valorParaData('DD/MM/YYYY HH:mm:ss') }}</span>
                  <span v-if="item.tipoEvento === 'MENSAGEM_SERVIDOR'" class="texto-verde">{{ item.dataEvento | valorParaData('DD/MM/YYYY HH:mm:ss') }}</span>
                  <span v-if="item.tipoEvento === 'LANCE_FORNECEDOR'">{{ item.dataEvento | valorParaData('DD/MM/YYYY HH:mm:ss') }}</span>
                  <span v-if="!item.tipoEvento">{{ item.dataEvento | valorParaData('DD/MM/YYYY HH:mm:ss') }}</span>
                </template>
                <template v-slot:item.apelido="{ item }">
                    <span v-if="item.tipoEvento === 'CANCELAMENTO_LANCE'"
                          class="d-inline-block text-truncate max-10 texto-vermelho">{{ item.apelido }}</span>
                    <span v-if="item.tipoEvento === 'SOLICITACAO_CANCELAMENTO_LANCE' || item.tipoEvento === 'MENSAGEM_FORNECEDOR'"
                        class="d-inline-block text-truncate max-10 texto-laranja">{{ item.apelido }}</span>
                  <span v-if="item.tipoEvento === 'MENSAGEM_SERVIDOR'" class="d-inline-block text-truncate max-10 texto-verde">{{ item.apelido }}</span>
                  <span v-if="item.tipoEvento === 'LANCE_FORNECEDOR'" class="d-inline-block text-truncate max-10">{{ item.apelido }}</span>
                  <span v-if="!item.tipoEvento" class="d-inline-block text-truncate max-10">{{ item.apelido }}</span>
                </template>
                <template v-slot:item.descricao="{ item }">
                  <span v-if="item.tipoEvento === 'CANCELAMENTO_LANCE'" class="texto-vermelho">{{ item.descricao }}</span>
                  <span v-if="item.tipoEvento === 'SOLICITACAO_CANCELAMENTO_LANCE' || item.tipoEvento === 'MENSAGEM_FORNECEDOR'"
                        class="texto-laranja">{{ item.descricao }}</span>
                  <span v-if="item.tipoEvento === 'MENSAGEM_SERVIDOR'" class=" texto-verde">{{ item.descricao }}</span>
                  <span v-if="item.tipoEvento === 'LANCE_FORNECEDOR'">{{ item.descricao }}</span>
                  <span v-if="!item.tipoEvento">{{ item.descricao }}</span>
                </template>
            </v-data-table>
        </div>
    </div>
</template>

<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'

export default {
    name: 'ChatLegado',
    props: {
        lotes: Array,
        lotesSelecionados: Array,
        ehPregoeiro: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            pregaoId: null,
            estaConectado: false,
            mensagem: null,
            nome: this.$store.state.loki.user.fullName,
            mensagensRecebidas: [],
            socket: null,
            stompClient: null,
            filtroSelecionado: 'Não Filtrar',
        }
    },
    async mounted() {
        this.setarConexao()
        this.conectar()
        await this.buscarMensagensDoChat()
    },
    computed:{
        prefixoSelect(){
            let filtroPadrao = [{nome: 'Não Filtrar', sequencia: 'Não Filtrar'}]
            filtroPadrao = filtroPadrao.concat(this.lotes)
            return filtroPadrao
        },
        colunasTabela(){
            return this.criarColunas(4,
                ['LOTE', 'DATA/HORA', 'APELIDO', 'MENSAGEM'],
                ['loteSequencia', 'dataEvento', 'apelido', 'descricao'],
                [false, false, false, false],
                ['left', 'left', 'left', 'left'],
                ['10%', '15%', '10%', '65%'],
                'gray--text')
        }
    },
    methods: {
        ...mapActions([
            actionTypes.CHAT.BUSCAR_MENSAGENS_ATA
        ]),
        setarConexao() {
            if (this.$route.params.idPregao) {
                this.pregaoId = this.$route.params.idPregao
            }
        },
        async buscarMensagensDoChat(){
            let filtroLote = this.filtroSelecionado
            if(this.filtroSelecionado === 'Não Filtrar')
                filtroLote = 0

            const resposta = await this.buscarMensagensAta({idLote: filtroLote ,idPregao: this.pregaoId})
            if(resposta){
                this.mensagensRecebidas = this.mensagensRecebidas.concat(resposta.items)
            }
        },
        filtrarPorLote(value, filtroSelecionado, item) {
            if (filtroSelecionado === 'Não Filtrar') {
                return item
            }
            return value != null && item.loteSequencia === filtroSelecionado
        },
        enviarMensagem() {
            if (this.lotesSelecionados.length === 0) {
                this.mostrarNotificacaoErro('Escolha um ou mais Lotes da lista para enviar a mensagem.')
                return
            }

            if (this.stompClient && this.stompClient.connected) {
                for (var i = 0; i < this.lotesSelecionados.length; i++) {
                    if(!this.lotesSelecionados[i].chatHabilitado && !this.ehPregoeiro) {
                        this.mostrarNotificacaoErro(`Chat inativo para o lote: ${this.lotesSelecionados[i].sequencia}`)
                    } else {
                        const msg = this.montarMensagem(this.lotesSelecionados[i])
                        this.stompClient.send(`/app/chatlegado=${this.pregaoId}`, JSON.stringify(msg), {})
                    }

                }
                this.mensagem = null
            }
        },
        montarMensagem(loteSelecionado) {
            return {
                mensagem: this.mensagem,
                nome: loteSelecionado.apelido ? loteSelecionado.apelido : 'PREGOEIRO',
                lote: {
                    sequencia: loteSelecionado.sequencia,
                    loteId: loteSelecionado.idLote?  loteSelecionado.idLote : loteSelecionado.lote.id,
                    fase: loteSelecionado.fase
                },
                pregaoId:this.pregaoId
            }
        },
        conectar() {
            this.socket = new SockJS(this.getSocketURL())
            this.stompClient = Stomp.over(this.socket)
            this.stompClient.debug = () => {}
            this.stompClient.connect(
                {},
                () => {
                    this.estaConectado = true
                    this.stompClient.subscribe(`/topic/mensagemChat=${this.pregaoId}`, tick => {
                        this.mensagensRecebidas.unshift(JSON.parse(tick.body))
                    })
                },
                error => {
                    console.log('FALHA AO CONECTAR O CHAT')
                    console.log(error)
                    this.estaConectado = false
                }
            )
            this.$emit('conexaoRealizada')
        },
        getSocketURL() {
            return window.location.protocol + '//' + window.location.hostname + '/pregao/api/sockjs'
        }
    }
}
</script>

<style lang="stylus">

    .chat-legado-container
        background-color white
        border-radius 5px
        width 1239px
        margin 0px 20px
        padding-bottom 30px

    .chat-legado-header
        background-color white
        border 1px solid #C9C9C9
        color #777
        padding 5px
        width 110px
        text-align center
        margin-left 30px
        position relative
        top -20px

    .chat-legado-lotes-info
        height 20px
        padding 0 30px
        color #777
        display flex
        flex-direction row

    .chat-legado-text
        margin-top 5px
        padding 0 30px
        display flex
        align-items center

    .texto-vermelho
        color #BB371A

    .texto-laranja
        color #EBA83A

    .texto-verde
        color #5F9B37

    .input-text
        border-radius 0
        margin 0px

    .chat-legado-btn
        height 40px !important
        background-color #3a6861 !important
        color white !important
        text-transform none
        margin-bottom 32px

    .chat-legado-filtro
        padding 0 30px
        display flex
        font-size 14px
        color #777

    .chat-legado-select-container
        width 150px

    .chat-legado-table-container
        margin 0 30px
        max-height 300px
        overflow-y auto

    .chat-legado-table
        thead > tr > th
            background-color #EEEEEE
            border 1px solid #ccc

        td
            border 1px solid #ccc
            color: #777

        td:hover
            cursor pointer

    .max-10
        max-width 10vw

    .max-50a
        max-width 40vw
</style>
