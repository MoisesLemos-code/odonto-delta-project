import Vue from 'vue'
import file from './file'
import { shallowMount } from '@vue/test-utils'
import Vuex from 'vuex'

Vue.use(Vuex)

describe('File', () => {
    let store, mockState, mockComponente, wrapper

    mockState = {
        loki: {
            file: {
                api: 'api_teste/'
            }
        }
    }
    store = new Vuex.Store({
        state: mockState
    })

    beforeEach(() => {
        mockComponente = {
            render() {},
            mixins: [file]
        }
        wrapper = shallowMount(mockComponente, { store })
    })

    it('criarLinkDownload', () => {
        const link = wrapper.vm.criarLinkDownload('teste')

        expect(link).toEqual('api_teste/teste')
    })

    it('criarLinkDownloadArquivo', () => {
        const id = 1
        const link = wrapper.vm.criarLinkDownloadArquivo(id)

        expect(link).toEqual(`api/arquivos/${id}/download`)
    })

    it('downloadArquivo', () => {
        const btnMock = {
            setAttribute: jest.fn(),
            click: jest.fn(),
            remove: jest.fn()
        }
        document.createElement = jest.fn().mockReturnValue(btnMock)
        document.body.appendChild = jest.fn()
        const id = 1
        const nome = 'arquivo'
        wrapper.vm.downloadArquivo(id, nome)

        expect(document.createElement).toHaveBeenCalledWith('a')
        expect(btnMock.setAttribute.mock.calls[0]).toEqual(['href', 'api/arquivos/1/download'])
        expect(btnMock.setAttribute.mock.calls[1]).toEqual(['download', 'arquivo'])
        expect(document.body.appendChild).toHaveBeenCalled()
        expect(btnMock.click).toHaveBeenCalled()
        expect(btnMock.remove).toHaveBeenCalled()
    })
})
