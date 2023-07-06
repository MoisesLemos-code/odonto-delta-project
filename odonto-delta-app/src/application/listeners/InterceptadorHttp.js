import axios from 'axios'
import store from '@/core/store'
import {LoadingScreen} from '@/core/utils'
import ApiErrorValidations from '@/core/exceptions/ApiErrorValidations'
import LocalStorageManager from '@/core/utils/LocalStorageManager'

const loading = new LoadingScreen(store)

class InterceptadorHttp {
    async execute() {
        return new Promise((resolve) => {
            this.registrarInterceptadores()
            this.setarVariaveisFixasNoHeaderDaRequest()
            resolve()
        })
    }

    registrarInterceptadores() {
        axios.defaults.baseURL = process.env.VUE_APP_BASE_API_URL
        axios.interceptors.request.use(this.tratarRequest, this.tratarErros)
        axios.interceptors.response.use(this.tratarResponse, this.tratarErros)
    }

    setarVariaveisFixasNoHeaderDaRequest() {
        axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
    }

    tratarRequest(config) {
        const usuario = LocalStorageManager.getItemStorage()
        let request = config
        if(usuario && usuario.token){
            request.headers = {... request.headers, authorization: usuario.token }
        }
        loading.start()
        return request
    }

    tratarResponse(response) {
        loading.stop()
        return response
    }

    tratarErros(error) {
        loading.stop()
        throw new ApiErrorValidations(error.response.data, error.response)
    }
}

export default new InterceptadorHttp()
