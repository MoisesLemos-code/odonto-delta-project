import ExceptionHandler from './ExceptionHandler'
import { Alert, PageUtils } from '@/core/utils'
import ApiErrorValidations from '@/core/exceptions/ApiErrorValidations'
import { mensagens } from '@/core/constants'

jest.mock('@/core/utils')
jest.mock('@/core/store', () => {})
jest.mock('@/views/routers', () => {})

describe('ExeptionHandler', () => {
    beforeEach(() => {
        Alert.mockClear()
        PageUtils.mockClear()
    })

    it('tratarError', () => {
        const error = {
            message: 'teste'
        }
        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('teste')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })

    it('handleMultipleErrors', () => {
        const message = 'teste'
        const response = { data: { errorMessages: [{ message: 'erro1' }, { message: 'erro2' }] } }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('erro1 \n' + 'erro2')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })

    it('handleUnknown', () => {
        const message = 'teste'
        const response = { data: null }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('Erro desconhecido.')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })

    it('handleUnauthorized', () => {
        const message = 'teste'
        const response = { data: 'teste', status: 401 }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual(mensagens.LOST_SESSION)
        expect(PageUtils.prototype.reload.mock.calls.length).toEqual(1)
    })

    it('handleInternalError com mensagem', () => {
        const message = 'teste'
        const response = { data: { message: 'mensagem teste' }, status: 500 }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('mensagem teste')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })

    it('handleInternalError sem mensagem', () => {
        const message = 'teste'
        const response = { data: { message: null }, status: 500 }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('Erro desconhecido.')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })

    it('handleNotFound', () => {
        const message = 'teste'
        const response = { data: null, status: 400 }
        const error = new ApiErrorValidations(message, response)

        ExceptionHandler.execute(error)

        expect(Alert.prototype.showError.mock.calls[0][0]).toEqual('teste')
        expect(Alert.prototype.showError.mock.calls.length).toEqual(1)
    })
})
