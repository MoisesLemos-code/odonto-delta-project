import {mutationTypes} from '@/core/constants'
import LocalStorageManager from '@/core/utils/LocalStorageManager'

export default {

    [mutationTypes.COMUM.SET_CURRENT_PAGE](state, to) {
        if (to.meta && to.meta.page) {
            state.comum.page.title = to.meta.page.title
            state.comum.page.subtitle = to.meta.page.subtitle
        }
    },

    [mutationTypes.COMUM.EFETUAR_LOGOUT]({ commit }){
        LocalStorageManager.removeItemStorage()
    },

    [mutationTypes.COMUM.SET_NOTIFICACAO](state, dados){
        state.notificacao = {
            cor: dados.cor,
            titulo: dados.titulo,
            mensagem: dados.mensagem,
            mostrar: dados.mostrar
        }
    },

    [mutationTypes.COMUM.SET_USUARIO_LOGADO](state, usuario) {
        LocalStorageManager.setItemStorage(usuario)
        console.log('--- SET_USUARIO_LOGADO')
        console.log(usuario)
        usuario.token = null
        state.comum.usuarioLogado = usuario
    },

    [mutationTypes.COMUM.SET_USUARIO_LOGADO_UPDATE](state, usuario) {
        LocalStorageManager.setUpdateItem(usuario)
        usuario.token = null
        state.comum.usuarioLogado = usuario
    },

    [mutationTypes.COMUM.SET_PARAMETROS](state, parametros) {
        state.comum.parametros = parametros
    },

    [mutationTypes.COMUM.SET_GLOBAL_LOADING](state, loading) {
        if (state.comum.isGlobalLoadingEnabled) {
            state.comum.isLoading = loading
        } else if (!loading) {
            state.comum.isLoading = loading
        }
    },

    [mutationTypes.COMUM.SET_LOADING_MESSAGE](state, message) {
        state.comum.loadingMessage = message
    },
}
