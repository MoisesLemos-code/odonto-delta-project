<template>
    <v-dialog v-model="value" max-width="720" scrollable persistent>
        <v-card>
            <v-toolbar dark color="primary" elevation="0">
                <v-toolbar-title>Editar Permissões do usuário {{ nome_usuario }}
                </v-toolbar-title>
                <v-spacer/>
                <v-btn class="close__button" text @click="fecharModal" icon>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-toolbar>
            <v-card-text class="modal-permissao-container">
                <v-form class="form-content mt-0 pl-0 pr-0">
                    <v-container fluid grid-list-xl white>
                        <div :key="grupoHead.id" v-for="(grupoHead, index) in gruposHeader">
                            <v-row :id="index" wrap align-center white class="pl-5 pr-5">
                                <v-col cols="12" md="12" sm="12" xs="12">
                                    <v-divider :inset="false" class="divider"></v-divider>
                                    <span class="section-label">{{ grupoHead.nome }}</span>
                                </v-col>
                                <div :key="permissao.cod" v-for="(permissao, index) in permissoes[grupoHead.grupo]">
                                    <v-tooltip top :id="index">
                                        <template v-slot:activator="{ on }">
                                            <v-chip
                                                    v-on="on"
                                                    class="ma-2"
                                                    :color="permissao.atribuido ? 'green' : '#E4E4E4'"
                                                    :text-color="permissao.atribuido ? 'white' : ''"
                                                    :input-value="true"
                                                    :filter-icon="permissao.atribuido ? 'mdi-check' : 'mdi-plus'"
                                                    filter
                                                    @click="permissao.atribuido = !permissao.atribuido"
                                            >
                                                {{ permissao.nome | filterEnum(humanizaPermissao) }}
                                            </v-chip>
                                        </template>
                                        {{ permissao.descricao }}
                                    </v-tooltip>
                                </div>
                            </v-row>
                        </div>
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
import _ from 'lodash'
import {mapActions} from 'vuex'
import {actionTypes} from '@/core/constants'
import BotaoSalvar from '@/views/components/BotaoSalvar'
import BotaoCancelar from '@/views/components/BotaoCancelar'
import humanizaPermissao from '@/core/constants/enums/humanizaPermissao'
import VerificarPerfilUsuario from '@/core/utils/VerificarPerfilUsuario'


export default {
    name: 'GerenciarUsuarioPermissaoModal',
    components: {BotaoCancelar, BotaoSalvar},
    props: {
        value: Boolean,
        item: Object
    },
    data() {
        return {
            permissoes: {},
            dadosGerais: {},
            nome_usuario: null,
            gruposHeader: [
                {id: 1, nome: 'Administração', grupo: 'administracao'},
                {id: 2, nome: 'Usuários', grupo: 'usuarios'},
                {id: 3, nome: 'Pacientes', grupo: 'pacientes'},
                {id: 4, nome: 'Dentes', grupo: 'dentes'},
                {id: 5, nome: 'Finalizadores', grupo: 'finalizadores'},
                {id: 6, nome: 'Orçamentos', grupo: 'orcamentos'},
                {id: 7, nome: 'Pagamentos', grupo: 'pagamentos'},
                {id: 8, nome: 'Peças', grupo: 'pecas'},
                {id: 9, nome: 'Serviços', grupo: 'servicos'},
                {id: 10, nome: 'Relatórios', grupo: 'relatorios'}
            ],
            grupos: [
                'administracao',
                'usuarios',
                'pacientes',
                'dentes',
                'finalizadores',
                'orcamentos',
                'pagamentos',
                'pecas',
                'servicos',
                'relatorios'
            ],
            humanizaPermissao
        }
    },
    computed: {
        possuiPermissao() {
            return VerificarPerfilUsuario(['ADMINISTRADOR'])
        }
    },
    async mounted() {
        this.setarDadosGerais(this.item)
        await this.buscarPermissoes()
    },
    methods: {
        ...mapActions([
            actionTypes.USUARIO.EDITAR_USUARIO,
            actionTypes.PERFIL_PERMISSAO.BUSCAR_TODAS_PERMISSOES
        ]),
        async buscarPermissoes() {
            const resultado = await this.buscarTodasPermissoes(this.dadosGerais.id)
            if (resultado) {
                this.permissoes = resultado
            }
        },
        setarDadosGerais(objeto) {
            this.dadosGerais = _.cloneDeep(objeto)
            this.nome_usuario = this.dadosGerais.nome_completo
        },
        setarPermissoesEdit() {
            this.dadosGerais.permissao = []
            this.grupos.forEach(grupoNome => {
                this.permissoes[grupoNome].map(permissao => {
                    if (permissao.atribuido) {
                        this.dadosGerais.permissao.push(permissao.cod)
                    }
                })
            })
        },
        async tratarEventoEditar() {
            if (this.possuiPermissao) {
                this.setarPermissoesEdit()
                this.setMensagemLoading('Salvando alterações do usuário...')
                await this.editarUsuario(this.dadosGerais)
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