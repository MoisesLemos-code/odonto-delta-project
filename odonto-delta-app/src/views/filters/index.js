import textoSemValor from './texto-sem-valor'
import filterEnum from './filterEnum'
import filterDate from './filterDate'
import valorParaReal from './valor-para-real'
import formatarCpfCnpj from './formatarCpfCnpj'
import formatarTelefone from './formatarTelefone'
import formatarCEP from './formatarCEP'

export default {
    install(Vue) {
        Vue.filter('textoSemValor', textoSemValor)
        Vue.filter('filterEnum', filterEnum)
        Vue.filter('filterDate', filterDate)
        Vue.filter('valorParaReal', valorParaReal)
        Vue.filter('formatarCpfCnpj', formatarCpfCnpj)
        Vue.filter('formatarTelefone', formatarTelefone)
        Vue.filter('formatarCEP', formatarCEP)
    }
}
