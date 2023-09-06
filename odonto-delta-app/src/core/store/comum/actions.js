import api from '@/core/apiclient'
import {actionTypes, mutationTypes} from '@/core/constants'

export default {

    async [actionTypes.COMUM.EFETUAR_LOGIN]({ commit }, dados) {
        const response = await api.usuario.efetuarLogin(dados)
        return response
    },

    async [actionTypes.COMUM.VERIFICAR_TOKEN]({ commit }){
        const { data } = await api.usuario.verificarLogin()
        console.log('---VERIFICAR_TOKEN')
        console.log(data)
        if(data){
            commit(mutationTypes.COMUM.SET_USUARIO_LOGADO_UPDATE, data)
        }
    }
}