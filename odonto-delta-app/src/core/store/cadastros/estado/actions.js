import api from '@/core/apiclient'
import {actionTypes } from '@/core/constants'

export default {

    async [actionTypes.CADASTROS.ESTADO.BUSCAR_TODOS_ESTADOS_SEM_PAGINACAO]() {
        const {data} = await api.estado.buscarTodosSemPaginacao()
        return data
    },
}