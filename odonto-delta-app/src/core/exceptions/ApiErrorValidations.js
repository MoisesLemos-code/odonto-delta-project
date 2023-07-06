import HttpStatus from '@/core/utils/HttpStatus'
import exceptionHandler from '@/core/exceptions/ExceptionHandler'


export default class ApiErrorValidations extends Error {
    constructor(message, response) {
        super(message)
        this.name = 'ApiErrorValidations'
        this.response = response
        this.httpStatus = new HttpStatus(response)

        if (Error.captureStackTrace) {
            if(this.unauthorized()){
                exceptionHandler.handleUnauthorized()
            }
            if(this.loginInvalid()){
               exceptionHandler.tratarError(response)
            }
            Error.captureStackTrace(this, ApiErrorValidations)
        }
    }

    disconnected() {
        return this.httpStatus.disconnected()
    }

    internalError() {
        return this.httpStatus.internalError()
    }

    badRequest(){
        return this.httpStatus.badRequest()
    }

    unauthorized() {
        return this.httpStatus.unauthorized()
    }

    loginInvalid() {
        return this.httpStatus.loginInvalid()
    }

    forbidden(){
        return this.httpStatus.forbidden()
    }

    notFound() {
        return this.httpStatus.notFound()
    }

    multipleErrors() {
        return this.response.data && this.response.data.errorMessages
    }
}