import register from './engine/Register'
import triggerEvents from './engine/TriggerEvent'
import rootPageCreator from './engine/RootPageCreator'
import router from '@/views/routers'

export default class Application {
    static run() {
        register.registerAll()
        this.carregarTokenAutenticacao()
        triggerEvents
            .triggerOnStartEvents()
            .then(() => {
                rootPageCreator.createInstance()
            })
            .catch(() => {
                rootPageCreator.createBootstrapError()
            })
    }

    static carregarTokenAutenticacao() {
        const token = this.getToken()
        const redirect = this.getRedirect()
        if (token) {
            router.push({name: 'acesso', query: {token, redirect}})
        }
    }

    static getRedirect() {
        const redirectRota = router.currentRoute.query.redirect
        const redirectCookie = sessionStorage.getItem('redirect')
        return redirectRota ? redirectRota : redirectCookie
    }

    static getToken() {
        const tokenRota = router.currentRoute.query.token
        const tokenCookie = sessionStorage.getItem('jwt')
        return tokenRota ? tokenRota : tokenCookie
    }
}
