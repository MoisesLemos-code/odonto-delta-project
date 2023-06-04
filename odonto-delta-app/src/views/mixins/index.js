import alert from './alert'
import loading from './loading'
import file from './file'
import clipboard from './clipboard'
import colunas from './colunas'
import ajustarBarraNavegador from './ajustarBarraNavegador'

export default {
    install(Vue) {
        Vue.mixin(alert)
        Vue.mixin(loading)
        Vue.mixin(file)
        Vue.mixin(clipboard)
        Vue.mixin(colunas)
        Vue.mixin(ajustarBarraNavegador)
    }
}
