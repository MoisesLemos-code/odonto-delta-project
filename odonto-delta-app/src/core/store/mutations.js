import Vue from 'vue'
import { mutationTypes, produto } from '@/core/constants'
import FileManagerUtils from '@/core/utils/FileManagerUtils'

export default {
    [mutationTypes.COMUM.SET_PRODUTO](state, data) {
        state.loki.product.id = data.id
        state.loki.product.name = data.nome
        state.loki.product.mainLogo = FileManagerUtils.createUrl(data.atributosExtendidos.logoMenu)
        state.loki.product.symbolLogo = FileManagerUtils.createUrl(data.atributosExtendidos.logoMenuRetraido)
        state.loki.product.logoMobile = FileManagerUtils.createUrl(data.atributosExtendidos.logoMobile)
        state.loki.product.version = produto.VERSAO
        state.loki.product.copywrite = produto.COPYRIGHT
        state.loki.product.logoutUrl = produto.LOGOUT_URL
    },

    [mutationTypes.COMUM.SET_RETRAIR_MENU](state) {
        Vue.set(state.loki, 'asideClosed', true)
    },

    [mutationTypes.COMUM.SET_USUARIO_LOGADO](state, usuario) {
        const user = {
            id: usuario.userId,
            name: usuario.userName,
            fullName: usuario.name,
            photo: FileManagerUtils.createThumbnailUrl(usuario.imageUrl),
            type: usuario.tipoUsuario,
            domainId: usuario.domainId,
            domainName: usuario.domainName,
            domainType: usuario.domainType,
            authorities: [{
                'name': 'Comprador.Normal',
                'hasAccess': true,
                'produtoId': 300
            }]
        }
        Vue.set(state.loki, 'user', user)
    },

    [mutationTypes.COMUM.SET_PERMISSOES_USUARIO](state, usuario) {

        const user = {
            id: usuario.userId,
            name: usuario.userName,
            fullName: usuario.name,
            photo: FileManagerUtils.createThumbnailUrl(usuario.imageUrl),
            type: usuario.tipoUsuario,
            domainId: usuario.domainId,
            domainName: usuario.domainName,
            domainType: usuario.domainType,
            authorities: [...state.loki.user.authorities, usuario.authorities[0]]
        }
        Vue.set(state.loki, 'user', user)
    },

    [mutationTypes.COMUM.SET_MENU_AVATAR](state) {
        const actions = {
            1: { title: 'Perfil', icon: 'person', path: '/perfil' }
        }
        Vue.set(state.loki, 'avatarActions', actions)
    },

    [mutationTypes.COMUM.SET_EXPANDIR_MENU](state) {
        Vue.set(state.loki, 'asideClosed', false)
    },

    [mutationTypes.COMUM.SET_ROTA_ORIGEM](state, rota) {
        Vue.set(state.comum.rota, 'origem', rota)
    },

    [mutationTypes.LOKI.ENABLE_AUTO_SAVING] () {
        this.commit(mutationTypes.LOKI.SET_AUTO_SAVED_MESSAGE, '<i class="fas fa-check mr-1"></i> Atualizado Automaticamente')
        this.commit(mutationTypes.LOKI.SET_SAVING_MESSAGE, 'Atualizado ${date} atrás')
        this.commit(mutationTypes.LOKI.SET_SAVING_START_MESSAGE, 'Atualizando...')
    },

    [mutationTypes.LOKI.SET_SAVING_START_MESSAGE](state, mensagem) {
        Vue.set(state.loki.autoSave, 'savingMessage', mensagem)
    },

    [mutationTypes.LICITACAO.SET_LICITACAO](state, licitacao) {
        Vue.set(state, 'licitacao', licitacao)
    },

}
