import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.USUARIO.BUSCAR_TODOS_USUARIOS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosUsuarios
        const {data} = await api.usuario.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.USUARIO.CADASTRAR_USUARIO](context, dados) {
        await api.usuario.cadastrar(dados)
    },

    async [actionTypes.USUARIO.EDITAR_USUARIO](context, dados) {
        const { data } = await api.usuario.editar(dados)
        return data
    },

    async [actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID](context, id) {
        const {data} = await api.usuario.buscarId(id)
        return data
    }
}
