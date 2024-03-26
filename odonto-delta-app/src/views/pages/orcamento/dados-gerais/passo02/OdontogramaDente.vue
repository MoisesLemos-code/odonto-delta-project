<template>
    <v-hover v-slot="{ hover }">
        <v-card :elevation="hover ? 5 : 2" class="container-dente">
            <div class="header-dente">
                <v-tooltip top>
                    <template v-slot:activator={on}>
                        <v-btn v-on="on" @click="controleRemoveDente" icon>
                            <v-icon v-if="item.possui" color="primary">mdi-checkbox-marked</v-icon>
                            <v-icon v-else color="red">mdi-close-box</v-icon>
                        </v-btn>
                    </template>
                    {{ item.possui ? 'Marcar como dente removido' : 'Desmarcar dente removido'}}
                </v-tooltip>
                <h2>{{ item.dente.numero }}</h2>
                <h4 v-if="item.dente.numero2 > 0">{{ item.dente.numero2 }}</h4>
            </div>
            <odontograma-faces @faceSelecionada="visualizarFaceDente"/>
            <v-tooltip top nudge-top="120">
                <template v-slot:activator={on}>
                    <div v-on="on" class="container-dente-img"
                         :class="{ 'dente-img-superior' : item.dente.maxilar === 'SUPERIOR', 'dente-img-inferior' : item.dente.maxilar === 'INFERIOR'}"
                         @click="visualizarDente">
                        <v-img :src="require('@/images/odontograma/dente/dente'+item.dente.numero +'.png')"/>
                    </div>
                </template>
                {{ item.dente.descricao }}
            </v-tooltip>
        </v-card>
    </v-hover>
</template>

<script>
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import OdontogramaFaces from './OdontogramaFaces'

export default {
    name: 'OdontogramaDente',
    components: {OdontogramaFaces},
    props: {
        item: Object
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.DENTE_ITEM.CADASTRAR_DENTE_ITEM,
            actionTypes.ORCAMENTO.DENTE_ITEM.EDITAR_DENTE_ITEM
        ]),
        async controleRemoveDente() {
            this.item.possui = !this.item.possui
            if (this.item.id && this.$route.params.orcamentoId) {
                await this.editarDente()
            } else if (this.$route.params.orcamentoId) {
                await this.salvarDente()
            } else {
                this.mostrarNotificacaoErro('Não foi possível salvar as informações do dente.')
            }
        },
        async salvarDente() {
            const dente = {
                orcamento_id: this.$route.params.orcamentoId,
                dente_id: this.item.dente.id,
                possui: this.item.possui
            }
            const resposta = await this.cadastrarDenteItem(dente)
            this.item.id = resposta.id
        },
        async editarDente() {
            const dente = {
                id: this.item.id,
                orcamento_id: this.$route.params.orcamentoId,
                dente_id: this.item.dente.id,
                possui: this.item.possui
            }
            await this.editarDenteItem(dente)
        },
        visualizarFaceDente(face) {
            this.$emit('visualizarFaceDente', {face, item: this.item})
        },
        visualizarDente() {
            this.$emit('visualizarDente', this.item)
        }
    }
}
</script>

<style scoped lang="stylus">
    .container-dente
        height 300px
        width 100px
        margin 5px
        overflow hidden

    .container-dente-img
        height 100%
        cursor pointer

    .dente-img-superior
        background-color #8E081D

    .dente-img-inferior
        background-color #A54A53

    .header-dente
        text-align center
        background-color white

</style>