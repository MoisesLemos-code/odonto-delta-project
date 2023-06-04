import Vue from 'vue'
import alert from './alert'
import { shallowMount } from '@vue/test-utils'
import { mutationTypes } from '@/core/constants/'
import Vuex from 'vuex'

Vue.use(Vuex)

describe('Alert', () => {
    let mutations, store, mockComponente, wrapper

    mutations = {
        [mutationTypes.LOKI.SHOW_ALERT]: jest.fn()
    }
    store = new Vuex.Store({
        mutations
    })

    beforeEach(() => {
        mockComponente = {
            render() {},
            mixins: [alert]
        }
        wrapper = shallowMount(mockComponente, { store })
    })

    it('mostrarNotificacaoErro', () => {
        wrapper.vm.mostrarNotificacaoErro('teste')

        expect(mutations[mutationTypes.LOKI.SHOW_ALERT]).toHaveBeenCalled()
    })

    it('mostrarNotificacaoErroDefault', () => {
        wrapper.vm.mostrarNotificacaoErroDefault('teste')

        expect(mutations[mutationTypes.LOKI.SHOW_ALERT]).toHaveBeenCalled()
    })

    it('mostrarNotificacaoSucesso', () => {
        wrapper.vm.mostrarNotificacaoSucesso('teste')

        expect(mutations[mutationTypes.LOKI.SHOW_ALERT]).toHaveBeenCalled()
    })

    it('mostrarNotificacaoSucessoDefault', () => {
        wrapper.vm.mostrarNotificacaoSucessoDefault('teste')

        expect(mutations[mutationTypes.LOKI.SHOW_ALERT]).toHaveBeenCalled()
    })
})
