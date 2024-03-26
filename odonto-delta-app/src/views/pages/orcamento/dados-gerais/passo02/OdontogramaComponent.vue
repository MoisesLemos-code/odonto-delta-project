<template>
    <div>
        <odontograma-modal-dente
                v-if="faceDenteModal"
                v-model="faceDenteModal"
                :item="denteSelecionado"
                :face="faceSelecionada"
                @fecharModalDente="fecharModalVisualizarFaceDente"
                @atualizarOdontograma="buscarDentesItens"
        />
        <v-container class="pa-0" fluid>
            <v-row>
                <v-col cols="12" md="6" sm="6" xs="6" class="pa-0">
                    <div class="quadrante-superior pt-5 pl-5" :class="{'quadrante-1' : coresQuadrante}">
                        <odontograma-dente :item="dentes.quadrante1[0]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[1]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[2]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[3]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[4]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[5]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[6]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante1[7]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                    </div>
                </v-col>
                <v-col cols="12" md="6" sm="6" xs="6" class="pa-0">
                    <div class="quadrante-superior pt-5 pr-5" :class="{'quadrante-2' : coresQuadrante}">
                        <odontograma-dente :item="dentes.quadrante2[0]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[1]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[2]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[3]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[4]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[5]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[6]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante2[7]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                    </div>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6" sm="6" xs="6" class="pa-0">
                    <div class="quadrante-inferior pb-10 pl-5" :class="{'quadrante-4' : coresQuadrante}">
                        <odontograma-dente :item="dentes.quadrante4[0]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[1]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[2]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[3]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[4]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[5]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[6]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante4[7]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                    </div>
                </v-col>
                <v-col cols="12" md="6" sm="6" xs="6" class="pa-0">
                    <div class="quadrante-inferior pb-10 pr-5" :class="{'quadrante-3' : coresQuadrante}">
                        <odontograma-dente :item="dentes.quadrante3[0]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[1]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[2]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[3]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[4]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[5]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[6]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                        <odontograma-dente :item="dentes.quadrante3[7]"
                                           @visualizarFaceDente="abrirModalVisualizarFaceDente"
                                           @visualizarDente="abrirModalVisualizarDente"/>
                    </div>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import _ from 'lodash'
import OdontogramaDente from './OdontogramaDente'
import OdontogramaModalDente from './OdontogramaModalDente'
import {actionTypes} from '@/core/constants'
import {mapActions} from 'vuex'

export default {
    name: 'OdontogramaComponent',
    components: {OdontogramaModalDente, OdontogramaDente},
    props: {
        coresQuadrante: Boolean,
    },
    data() {
        return {
            dentes: {
                quadrante1: [
                    {dente:{ id: 1, numero: 18, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Terceiro molar (Superior direito)'}, possui: true},
                    {dente:{ id: 2, numero: 17, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Segundo molar (Superior direito)'}, possui: true},
                    {dente:{ id: 3, numero: 16, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Primeiro molar (Superior direito)'}, possui: true},
                    {dente:{ id: 4, numero: 15, numero2: 55, maxilar: 'SUPERIOR', descricao: 'Segundo premolar (Superior direito)'}, possui: true},
                    {dente:{ id: 5, numero: 14, numero2: 54, maxilar: 'SUPERIOR', descricao: 'Primeiro premolar (Superior direito)'}, possui: true},
                    {dente:{ id: 6, numero: 13, numero2: 53, maxilar: 'SUPERIOR', descricao: 'Canino (Superior direito)'}, possui: true},
                    {dente:{ id: 7, numero: 12, numero2: 52, maxilar: 'SUPERIOR', descricao: 'Incisivo lateral (Superior direito)'}, possui: true},
                    {dente:{ id: 8, numero: 11, numero2: 51, maxilar: 'SUPERIOR', descricao: 'Incisivo central (Superior direito)'}, possui: true},
                ],
                quadrante2: [
                    {dente:{ id: 9, numero: 21, numero2: 61, maxilar: 'SUPERIOR', descricao: 'Incisivo central (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 10, numero: 22, numero2: 62, maxilar: 'SUPERIOR', descricao: 'Incisivo lateral (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 11, numero: 23, numero2: 63, maxilar: 'SUPERIOR', descricao: 'Canino superior (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 12, numero: 24, numero2: 64, maxilar: 'SUPERIOR', descricao: 'Primeiro premolar (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 13, numero: 25, numero2: 65, maxilar: 'SUPERIOR', descricao: 'Segundo premolar (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 14, numero: 26, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Primeiro molar (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 15, numero: 27, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Segundo molar (Superior esquerdo)'}, possui: true},
                    {dente:{ id: 16, numero: 28, numero2: 0, maxilar: 'SUPERIOR', descricao: 'Terceiro molar (Superior esquerdo)'}, possui: true},
                ],
                quadrante3: [
                    {dente:{ id: 17, numero: 31, numero2: 71, maxilar: 'INFERIOR', descricao: 'Incisivo central (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 18, numero: 32, numero2: 72, maxilar: 'INFERIOR', descricao: 'Incisivo lateral (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 19, numero: 33, numero2: 73, maxilar: 'INFERIOR', descricao: 'Canino (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 20, numero: 34, numero2: 74, maxilar: 'INFERIOR', descricao: 'Primeiro premolar (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 21, numero: 35, numero2: 75, maxilar: 'INFERIOR', descricao: 'Segundo premolar (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 22, numero: 36, numero2: 0, maxilar: 'INFERIOR', descricao: 'Primeiro molar (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 23, numero: 37, numero2: 0, maxilar: 'INFERIOR', descricao: 'Segundo molar (Inferior esquerdo)'}, possui: true},
                    {dente:{ id: 24, numero: 38, numero2: 0, maxilar: 'INFERIOR', descricao: 'Terceiro molar (Inferior esquerdo)'}, possui: true},
                ],
                quadrante4: [
                    {dente:{ id: 32, numero: 48, numero2: 0, maxilar: 'INFERIOR', descricao: 'Terceiro molar (Inferior direito)'}, possui: true},
                    {dente:{ id: 31, numero: 47, numero2: 0, maxilar: 'INFERIOR', descricao: 'Segundo molar (Inferior direito)'}, possui: true},
                    {dente:{ id: 30, numero: 46, numero2: 0, maxilar: 'INFERIOR', descricao: 'Primeiro molar (Inferior direito)'}, possui: true},
                    {dente:{ id: 29, numero: 45, numero2: 85, maxilar: 'INFERIOR', descricao: 'Segundo premolar (Inferior direito)'}, possui: true},
                    {dente:{ id: 28, numero: 44, numero2: 84, maxilar: 'INFERIOR', descricao: 'Primeiro premolar (Inferior direito)'}, possui: true},
                    {dente:{ id: 27, numero: 43, numero2: 83, maxilar: 'INFERIOR', descricao: 'Canino (Inferior direito)'}, possui: true},
                    {dente:{ id: 26, numero: 42, numero2: 82, maxilar: 'INFERIOR', descricao: 'Incisivo lateral (Inferior direito)'}, possui: true},
                    {dente:{ id: 25, numero: 41, numero2: 81, maxilar: 'INFERIOR', descricao: 'Incisivo central (Inferior direito)'}, possui: true},
                ]
            },
            faceDenteModal: false,
            denteSelecionado: null,
            faceSelecionada: null,
            orcamentoId: null,
            dentesItens: []
        }
    },
    async mounted() {
        this.setOrcamentoId()
        await this.buscarDentesItens()
    },
    methods: {
        ...mapActions([
            actionTypes.ORCAMENTO.BUSCAR_DENTES_ITENS_ORCAMENTO
        ]),
        setOrcamentoId() {
            if (this.$route.params.orcamentoId) {
                this.orcamentoId = this.$route.params.orcamentoId
            }
        },
        async buscarDentesItens() {
            this.setMensagemLoading('Buscando dados do orçamento...')
            this.dentesItens = await this.buscarDentesItensOrcamento(this.orcamentoId)
            this.vincularDentesQuadrante1()
            this.vincularDentesQuadrante2()
            this.vincularDentesQuadrante3()
            this.vincularDentesQuadrante4()
        },
        abrirModalVisualizarDente(item) {
            this.faceDenteModal = true
            this.denteSelecionado = item
            this.faceSelecionada = null
        },
        abrirModalVisualizarFaceDente(denteFace) {
            this.faceDenteModal = true
            this.denteSelecionado = denteFace.item
            this.faceSelecionada = denteFace.face
        },
        fecharModalVisualizarFaceDente() {
            this.faceDenteModal = false
            this.denteSelecionado = null
            this.faceSelecionada = null
        },
        vincularDentesQuadrante1(){
            this.dentes.quadrante1 = this.dentes.quadrante1.map(item =>{
                let novoItem = item
                this.dentesItens.forEach(denteOrcamento =>{
                    if(item.dente.numero === denteOrcamento.dente.numero)
                        novoItem = denteOrcamento
                })
                return novoItem
            })
        },
        vincularDentesQuadrante2(){
            this.dentes.quadrante2 = this.dentes.quadrante2.map(item =>{
                let novoItem = item
                this.dentesItens.forEach(denteOrcamento =>{
                    if(item.dente.numero === denteOrcamento.dente.numero)
                        novoItem = denteOrcamento
                })
                return novoItem
            })
        },
        vincularDentesQuadrante3(){
            this.dentes.quadrante3 = this.dentes.quadrante3.map(item =>{
                let novoItem = item
                this.dentesItens.forEach(denteOrcamento =>{
                    if(item.dente.numero === denteOrcamento.dente.numero)
                        novoItem = denteOrcamento
                })
                return novoItem
            })
        },
        vincularDentesQuadrante4(){
            this.dentes.quadrante4 = this.dentes.quadrante4.map(item =>{
                let novoItem = item
                this.dentesItens.forEach(denteOrcamento =>{
                    if(item.dente.numero === denteOrcamento.dente.numero)
                        novoItem = denteOrcamento
                })
                return novoItem
            })
        },
    }
}
</script>

<style scoped lang="stylus">

    .quadrante-superior
        display flex
        flex-direction row
        margin-bottom 10px
        padding 1px

    .quadrante-inferior
        display flex
        flex-direction row
        padding 1px

    .quadrante-1
        background-color #EFF7EA
        padding 0px
        border-right 1px solid #363636
        border-bottom 1px solid #363636

    .quadrante-2
        background-color #FEFDE4
        padding 0px
        border-left 1px solid #363636
        border-bottom 1px solid #363636

    .quadrante-3
        background-color #E8EFF9
        padding 0px
        border-left 1px solid #363636
        border-top 1px solid #363636

    .quadrante-4
        background-color #C3F1F1
        padding 0px
        border-right 1px solid #363636
        border-top 1px solid #363636

</style>