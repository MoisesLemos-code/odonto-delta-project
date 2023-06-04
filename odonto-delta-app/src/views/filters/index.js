import valorParaReal from './valor-para-real'
import textoSemValor from './texto-sem-valor'
import valorParaData from './valor-para-data'

export default {
    install(Vue) {
        Vue.filter('valorParaReal', valorParaReal)
        Vue.filter('textoSemValor', textoSemValor)
        Vue.filter('valorParaData', valorParaData)
    }

}