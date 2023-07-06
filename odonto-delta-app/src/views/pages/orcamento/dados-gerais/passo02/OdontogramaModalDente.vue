<template>
    <v-dialog v-model="value" max-width="840" scrollable @click:outside="fecharModal">
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Dente {{ item.dente.numero }}{{ item.dente.numero2 ? '/' + item.dente.numero2 : ''}} -
                    {{ item.dente.descricao }}
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="card-text-container pa-0">
                <v-container fluid grid-list-xl white>
                    <v-row wrap align-center white>
                        <v-col cols="12" md="3" sm="3" xs="12">
                            <v-img :src="require('@/images/odontograma/dente/dente'+item.dente.numero +'.png')"
                                   class="img-dente-modal"/>
                            <odontograma-faces @faceSelecionada="selecionarFaceDente" tipo-estilo="grande" ativar
                                               :face="faceSelecionada" class="mt-5"/>
                        </v-col>
                        <v-col cols="12" md="9" sm="9" xs="12" class="pa-0">
                            <v-tabs v-model="tab">
                                <v-tabs-slider color="tertiary"></v-tabs-slider>

                                <v-tab v-for="aba in abas" :key="aba">
                                    {{ aba }}
                                </v-tab>
                            </v-tabs>
                            <v-tabs-items v-model="tab">
                                <v-tab-item v-for="aba in abas" :key="aba">
                                    <div>
                                        <odontograma-modal-dente-servicos-realizados
                                                v-if="aba === 'Serviços'"
                                                :face="faceSelecionada"
                                                :denteItem="item"
                                                @atualizarDadosOdontograma="atualizarDadosOdontograma"
                                                @atualizarValorServico="atualizarValorServico"
                                        />
                                        <odontograma-modal-dente-pecas-realizadas
                                                v-if="aba === 'Peças'"
                                                :face="faceSelecionada"
                                                :denteItem="item"
                                                @atualizarDadosOdontograma="atualizarDadosOdontograma"
                                                @atualizarValorPeca="atualizarValorPeca"
                                        />
                                    </div>
                                </v-tab-item>
                            </v-tabs-items>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <div class="card-footer">
                <span class="footer-text">Total Serviços: {{ totalServicos | valorParaReal }}</span>
                <span class="footer-text">Total Peças: {{ totalPecas | valorParaReal }}</span>
                <span class="footer-text-total">Valor Total: {{ valorTotal | valorParaReal }}</span>
            </div>
        </v-card>
    </v-dialog>
</template>

<script>

    import OdontogramaFaces from './OdontogramaFaces'
    import OdontogramaModalDenteServicosRealizados from './servicoItem/OdontogramaModalDenteServicosRealizados'
    import OdontogramaModalDentePecasRealizadas from './pecaItem/OdontogramaModalDentePecasRealizadas'

    export default {
        name: 'OdontogramaModalDente',
        components: {OdontogramaModalDentePecasRealizadas, OdontogramaModalDenteServicosRealizados, OdontogramaFaces},
        props: {
            value: Boolean,
            item: Object,
            face: String
        },
        data() {
            return {
                tab: null,
                abas: [
                    'Serviços', 'Peças'
                ],
                faceSelecionada: this.face,
                totalServicos: 0,
                totalPecas: 0
            }
        },
        computed: {
            valorTotal() {
                return this.totalServicos + this.totalPecas
            }
        },
        methods: {
            selecionarFaceDente(face) {
                this.faceSelecionada = face
            },
            atualizarDadosOdontograma() {
                this.$emit('atualizarOdontograma')
            },
            fecharModal() {
                this.$emit('fecharModalDente')
            },
            atualizarValorServico(valor){
                this.totalServicos = valor
            },
            atualizarValorPeca(valor){
                this.totalPecas = valor
            }
        }
    }
</script>

<style scoped lang="stylus">

    .card-text-container
        padding 0px !important
        overflow-x hidden
        height 600px

    .img-dente-modal
        border 2px solid #777
        border-radius 10px

    .card-footer
        padding 10px
        margin-right 50px
        text-align right

        .footer-text
            font-size 14px
            font-weight bold
            color #757575
            margin-right 30px

        .footer-text-total
            font-size 18px
            font-weight bold
            color #555555
</style>