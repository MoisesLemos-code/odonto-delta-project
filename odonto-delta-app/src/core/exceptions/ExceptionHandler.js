import router from '@/views/routers'
import store from '@/core/store'
import {Alert, PageUtils} from '@/core/utils'
import ApiErrorValidations from '@/core/exceptions/ApiErrorValidations'
import {mensagens, mutationTypes} from '@/core/constants'

const alert = new Alert(store)
const pageUtils = new PageUtils(router)

class ExceptionHandler {
    execute(error) {
        if (error instanceof ApiErrorValidations) {
            this.tratarValidationError(error)
        } else {
            this.tratarError(error)
        }
        store.commit(mutationTypes.COMUM.SET_GLOBAL_LOADING, false)
        store.commit(mutationTypes.COMUM.SET_LOADING_MESSAGE, store.state.defaultLoadingMessage)
    }

    tratarValidationError(error) {
        if (error.multipleErrors()) {
            this.handleMultipleErrors(error.response.data.errors)
        }else if (error.forbidden()) {
            this.tratarAlertPadrao(error.response.data)
        } else if (error.disconnected()) {
            this.handleDisconnected()
        } else if (error.unauthorized()) {
            this.handleUnauthorized()
        } else if (error.internalError()) {
            this.handleInternalError(error)
        } else if (error.notFound()) {
            this.handleNotFound(error)
        } else if (error.badRequest()){
            if(error.response.data.errors && error.response.data.errors.length > 0) {
                this.handleMultipleErrors(error.response.data.errors)
            }else{
                this.tratarErrorPadrao(error.response.data)
            }
        } else {
            this.handleUnknown()
        }
    }

    tratarErrorPadrao(error) {
        alert.showError(error.msg)
    }

    tratarAlertPadrao(error) {
        alert.showAlert(error.msg)
    }

    tratarError(error) {
        alert.showError(error.data.message)
    }

    handleMultipleErrors(errors) {
        alert.showError(errors[0].message)
    }

    handleDisconnected() {
        alert.showError(mensagens.DISCONNECTED)
        pageUtils.goToHome()
    }

    handleUnauthorized() {
        alert.showError(mensagens.LOST_SESSION)
        localStorage.removeItem('login')
        pageUtils.reload()
    }

    handleNotFound(error) {
        this.handleInternalError(error)
        pageUtils.goToHome()
    }

    handleInternalError(error) {
        if(typeof error.response.data.message !== 'undefined'){
            const msgFormatted = error.data.errors[0]
            if (msgFormatted) {
                alert.showError(msgFormatted)
            } else {
                this.handleUnknown()
            }
        }else{
            var dataView = new DataView(error.data)
            var decoder = new TextDecoder('utf8')
            var response = JSON.parse(decoder.decode(dataView))
            var message = response['message']
            alert.showError(message)
        }
    }

    handleUnknown() {
        alert.showError(mensagens.UNKNOWN)
    }
}

export default new ExceptionHandler()
