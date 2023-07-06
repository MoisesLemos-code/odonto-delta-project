import Vue from 'vue'
import '@babel/polyfill'
import '@/plugins'
import mixins from '@/views/mixins'
import filters from '@/views/filters'
import moment from 'moment'

class Register {

    registerAll() {
        Vue.use(mixins)
        Vue.use(filters)
        Vue.prototype.moment = moment
    }

}

export default new Register()
