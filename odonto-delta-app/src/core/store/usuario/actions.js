import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.USUARIO.BUSCAR_TODOS_USUARIOS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosUsuarios
        const {data} = await api.usuario.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.USUARIO.CADASTRAR_USUARIO]({commit}, dados) {
        await api.usuario.cadastrar(dados)
    },

    async [actionTypes.USUARIO.EDITAR_USUARIO]({commit}, dados) {
        console.log('--- EDITAR_USUARIO')
        console.log(dados)
        const { data } = await api.usuario.editar(dados)
        console.log(data)
        return data
    },

    async [actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID](context, id) {
        console.log('--- BUSCAR_USUARIO_POR_ID')
        console.log(id)
        const {data} = await api.usuario.buscarId(id)
        console.log(data)
        return data
    }
}
