import clipboard from './clipboard'
import { shallowMount } from '@vue/test-utils'

describe('Clipboard', () => {
    let mockComponente, wrapper

    beforeEach(() => {
        mockComponente = {
            render() {},
            mixins: [clipboard]
        }
        wrapper = shallowMount(mockComponente)
    })

    it('copiarParaAreaDeTransferencia', () => {
        document.body.appendChild = jest.fn()
        document.body.removeChild = jest.fn()
        document.execCommand = jest.fn()
        const element = {
            setAttribute: jest.fn(),
            style: {},
            value: '',
            select: jest.fn()
        }
        document.createElement = jest.fn().mockReturnValue(element)

        wrapper.vm.copiarParaAreaDeTransferencia('teste')

        expect(document.createElement).toHaveBeenCalledWith('textarea')
        expect(element.value).toEqual('teste')
        expect(document.body.appendChild).toHaveBeenCalled()
        expect(element.select).toHaveBeenCalled()
        expect(document.execCommand).toHaveBeenCalledWith('copy')
        expect(document.body.removeChild).toHaveBeenCalled()
    })
})
