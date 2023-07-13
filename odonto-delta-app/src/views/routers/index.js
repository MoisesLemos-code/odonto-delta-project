import Router from 'vue-router'
import goTo from 'vuetify/es5/services/goto'
import {actionTypes, mutationTypes} from '@/core/constants'
import store from '@/core/store'
import LocalStorageManager from '@/core/utils/LocalStorageManager'
import VerificarPerfilUsuario from '@/core/utils/VerificarPerfilUsuario'

import comum from './routes/comum'
import login from './routes/login'
import orcamento from './routes/orcamento'
import cadastros from './routes/cadastros/cadastros'
import configuracao from './routes/configuracao'
import relatorios from './routes/relatorios'

let router = new Router({
    routes: [
        {
            path: '/',
            name: 'Inicio',
            redirect: () => {
                return { name: 'PacienteListagem' }
            },
        },
        ...comum,
        ...login,
        //...orcamento,
        ...cadastros,
        ...configuracao,
        //...relatorios
    ],
    scrollBehavior: (to, from, savedPosition) => {
        let scrollTo = 0

        if (to.hash) {
            scrollTo = to.hash
        } else if (savedPosition) {
            scrollTo = savedPosition.y
        }

        return goTo(scrollTo)
    },
})

router.beforeEach(async (to, from, next) => {
    let usuario = LocalStorageManager.getItemStorage()

    if(to.matched.some(record => record.meta.requiresAuth)) {
        if (!usuario || usuario.token == null) {
            next({ name: 'Login'})
        } else {
            store.dispatch(actionTypes.COMUM.VERIFICAR_TOKEN)
            usuario = LocalStorageManager.getItemStorage()
            if(to.matched.some(record => record.meta.perfis)) {
                const possuiPerfil = VerificarPerfilUsuario(to.meta.perfis)
                if(usuario && possuiPerfil){
                    next()
                }
                else{
                    next({ name: 'perfilSemAcesso'})
                }
            }else {
                next()
            }
        }
    } else if(to.matched.some(record => record.meta.guest)) {
        if(!usuario || usuario.token == null){
            next()
        }
        else{
            next({ name: 'OrcamentoListagem'})
        }
    }else {
        next()
    }
})

router.afterEach(to => {
    store.commit(mutationTypes.COMUM.SET_CURRENT_PAGE, to)
})

export default router