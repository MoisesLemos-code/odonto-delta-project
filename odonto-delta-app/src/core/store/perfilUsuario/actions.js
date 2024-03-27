import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.PERFIL_USUARIO.BUSCAR_PERFIS_DO_USUARIO]() {
        const {data} = await api.perfilUsuario.buscarPerfisDoUsuario()
        return data
    },

}
