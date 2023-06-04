import {shallowMount} from '@vue/test-utils'
import Vuex from 'vuex'
import Vue from 'vue'
import {actionTypes} from '@/core/constants'
import applicationTestBuilder from '@/application/ApplicationTestBuilder'
import ModalVisualizarPropostas from './ModalVisualizarPropostas'

describe('ModalVisualizarPropostas', () => {
    let wrapper, mutations, actions, localVue, router, state, store

    const lotesPropostas = {
        items: [
            {id: 1, resumoDescricao: 'LOTE 001'},
            {id: 2, resumoDescricao: 'LOTE 002'},
        ]
    }
    const propostas = {
        items: [
            {idFornecedor: 1, apelido: 'FORNECEDOR 01', valorLanceFinal: 100, microEmpresa: true},
            {idFornecedor: 2, apelido: 'FORNECEDOR 02', valorLanceFinal: 200, microEmpresa: false},
        ]
    }

    const loteItem =  {id: 1, resumoDescricao: 'LOTE 001'}

    const defaultMount = (stubs) => shallowMount(ModalVisualizarPropostas, {
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
            },

            comum: {
                dadosAutenticacao: {
                    payload: {
                        pregaoId : 129,
                        usuarioId : 1
                    }
                }

            }
        }

        mutations = {}

        actions = {
            [actionTypes.PREGOEIRO.BUSCAR_LOTES_PROPOSTAS]: jest.fn().mockReturnValue(lotesPropostas),
            [actionTypes.PREGOEIRO.BUSCAR_PROPOSTAS_DETALHE_POR_LOTE]: jest.fn().mockReturnValue(propostas)
        }
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

        it('Deve setar o id pregão', () => {
            wrapper = defaultMount()
            expect(wrapper.vm.idPregao).toEqual(129)
        })

        it('Deve buscar os lotes', async () => {
            wrapper = defaultMount()
            expect(actions[actionTypes.PREGOEIRO.BUSCAR_LOTES_PROPOSTAS]).toHaveBeenCalled()
        })

        it('Deve buscar as propostas', async () => {
            wrapper = defaultMount()
            wrapper.vm.buscarPropostasDetalhe(10)
            expect(actions[actionTypes.PREGOEIRO.BUSCAR_PROPOSTAS_DETALHE_POR_LOTE]).toHaveBeenCalled()
        })

        it('Deve emitir o evento de fechar o modal', () => {
            wrapper = defaultMount()
            wrapper.vm.fecharModal()
            expect(wrapper.emitted().fecharModalVisualizarPropostas).toBeTruthy()
        })

        it('Deve abrir o modal de detalhes de propostas', () => {
            wrapper = defaultMount()
            wrapper.vm.abrirModalDetalhesPropostas(loteItem)
            expect(wrapper.vm.participanteSelecionado).toEqual(loteItem)
            expect(wrapper.vm.exibirModalDetalhesProposta).toBeTruthy()
        })

        it('Deve fechar o modal de detalhes de propostas', () => {
            wrapper = defaultMount()
            wrapper.vm.fecharModalDetalhesProposta()
            expect(wrapper.vm.exibirModalDetalhesProposta).toBeFalsy()
        })

    })
})
