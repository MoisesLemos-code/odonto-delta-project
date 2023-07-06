import textoSemValor from './texto-sem-valor'
import filterEnum from './filterEnum'
import filterDate from './filterDate'
import valorParaReal from './valor-para-real'

export default {
    install(Vue) {
        Vue.filter('textoSemValor', textoSemValor)
        Vue.filter('filterEnum', filterEnum)
        Vue.filter('filterDate', filterDate)
        Vue.filter('valorParaReal', valorParaReal)
    }
}
