import {shallowMount} from '@vue/test-utils'
import Vuex from 'vuex'
import Vue from 'vue'
import applicationTestBuilder from '@/application/ApplicationTestBuilder'
import ModalReiniciarEtapaFechada from './ModalReiniciarEtapaFechada'
import {mutationTypes} from "@/core/constants";

describe('ModalReiniciarEtapaFechada', () => {
    let wrapper, mutations, actions, localVue, router, state, store

    const defaultMount = (stubs) => shallowMount(ModalReiniciarEtapaFechada, {
        localVue,
        router,
        stubs,
        store
    })
    beforeEach(() => {
        localVue = applicationTestBuilder.build()

        router = {
            init: jest.fn(),
            push: jest.fn(),
            history: {current: {
                params:{
                    idPregao: 129
                }
            }}
        }

        state = {
            loki: {
                user: {
                    domainId: 1,
                    type: 'INTERNO',
                }
            }
        }

        mutations = {
            [mutationTypes.LOKI.SHOW_ALERT]: jest.fn()
        }

        actions = {}
        Vue.use(Vuex)
        store = new Vuex.Store({state, mutations, actions})
    })

    afterEach(() => {
        if (wrapper) {
            wrapper.destroy()
            wrapper = null
        }
    })

    describe('Methods', () => {

        it('Deve emitir o evento de fechar o modal', () => {
            wrapper = defaultMount()
            wrapper.vm.fecharModal()
            expect(wrapper.emitted().fecharModalReinicio).toBeTruthy()
        })

        it('Deve reniciar a etapa fechada do lote', () => {
            wrapper = defaultMount()
            wrapper.vm.justificativa = 'teste'
            wrapper.vm.reiniciarLote()
            expect(wrapper.emitted().reiniciarEtapa).toBeTruthy()
        })


        it('Deve falhar ao reniciar a etapa fechada do lote', () => {
            wrapper = defaultMount()
            wrapper.vm.justificativa = ''
            wrapper.vm.reiniciarLote()
            expect(wrapper.emitted().reiniciarEtapa).toBeTruthy()
            expect(mutations[mutationTypes.LOKI.SHOW_ALERT]).toHaveBeenCalled()
        })

    })
})
