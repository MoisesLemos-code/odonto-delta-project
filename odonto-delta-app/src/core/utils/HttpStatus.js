export default class HttpStatus {

    constructor(response) {
        this.response = response
    }

    disconnected() {
        return !(this.response)
    }

    badRequest(){
        return this.response.status === 400
    }

    unauthorized() {
        return this.response.status === 401
    }

    forbidden() {
        return this.response.status === 403
    }

    notFound() {
        return this.response.status === 404
    }

    loginInvalid() {
        return this.response.status === 406
    }

    internalError() {
        return this.response.status === 500
    }

}