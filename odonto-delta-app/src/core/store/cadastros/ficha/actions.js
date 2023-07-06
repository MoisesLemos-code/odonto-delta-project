import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.CADASTROS.PACIENTE.FICHA.EDITAR_FICHA]({commit}, dados) {
        await api.ficha.editar(dados)
    },

    async [actionTypes.CADASTROS.PACIENTE.FICHA.BUSCAR_POR_PACIENTE](context, id) {
        const {data} = await api.ficha.buscarPorPaciente(id)
        return data
    },

}