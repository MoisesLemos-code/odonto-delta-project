<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Editar Permissões do usuário {{ this.item.nome }}
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-permissao-container">
                <v-form class="form-content mt-0 pl-0 pr-0">
                    <v-container fluid grid-list-xl white>
                    </v-container>
                </v-form>
            </v-card-text>
            <v-container>
                <v-row wrap align-center white class="pl-10 pr-10 row-bottom">
                    <botao-cancelar @cancelar="fecharModal"/>

                    <botao-salvar @salvar="tratarEventoEditar" v-if="possuiPermissao"/>
                    <v-tooltip
                            v-else
                            top
                            nudge-top="30"
                            max-width="200">
                        <template v-slot:activator="{ on }">
                            <div v-on="on" class="btn-disabled">
                                <botao-salvar disabled/>
                            </div>
                        </template>
                        Você não possui permissão para salvar este formulário.
                    </v-tooltip>
                </v-row>
            </v-container>
        </v-card>
    </v-dialog>
</template>

<script>
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'


export default {
    name: 'GerenciarUsuarioPermissaoModal',
    components: {BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            perfis: {},
            dadosGerais: {},
        }
    },
    computed: {
        possuiPermissao() {
            return false
        }
    },
    async mounted() {
        await this.buscarPerfisUsuario()
    },
    methods: {
        async buscarPerfisUsuario() {
            const resultado = await this.$store.dispatch(actionTypes.PERFIL_USUARIO.BUSCAR_PERFIS_DO_USUARIO)
            if (resultado) {
                this.perfis = resultado.items
                console.log('----buscar')
                console.log(this.perfis)
            }
        },
        async tratarEventoEditar() {
            if (this.possuiPermissao) {
                this.setMensagemLoading('Salvando alterações do usuário...')
                this.mostrarNotificacaoSucessoDefault()
            }
        },
        fecharModal() {
            this.$emit('fecharEdicaoPermissao')
        }
    }
}
</script>

<style scoped lang="stylus">

    .row-bottom
        border-top-width 1px
        border-top-style solid
        border-top-color #ccc
        box-sizing border-box
        padding-top 10px
        padding-bottom 10px
        display flex
        justify-content space-between

    .modal-permissao-container
        padding 0px !important
        overflow-x hidden
        height auto

</style>