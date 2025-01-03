import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO](context, conteudo) {
        const {data} = await api.paciente.buscarTodosSemPaginacao(conteudo)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_TODOS_PACIENTES]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosPacientes
        const {data} = await api.paciente.buscarTodosPacientes(filtros, paginacao)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.CADASTRAR_PACIENTE](context, dados) {
        const {data} = await api.paciente.cadastrar(dados)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.EDITAR_PACIENTE](context, dados) {
        const {data} = await api.paciente.editar(dados)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.BUSCAR_PACIENTE_POR_ID](context, id) {
        const {data} = await api.paciente.buscarId(id)
        return data
    },

    async [actionTypes.CADASTROS.PACIENTE.EXCLUIR_PACIENTE](context, id) {
        await api.paciente.excluir(id)
    },
}