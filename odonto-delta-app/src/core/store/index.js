import Vuex from 'vuex'
import { actions, getters, mutations, state } from './comum'
import usuario from './usuario'
import menu from './menu'
import cidade from './cadastros/cidade'
import estado from './cadastros/estado'
import orcamento from './orcamento'
import paciente from './cadastros/paciente'
import ficha from './cadastros/ficha'
import servico from './servico'
import peca from './peca'
import denteItem from './denteItem'
import servicoItem from './servicoItem'
import pecaItem from './pecaItem'
import relatorios from './relatorios'
import perfil from './perfil'
import permissao from './permissao'
import perfilPermissao from './perfilPermissao'
import perfilUsuario from './perfilUsuario'
import cobranca from './cobranca'

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions,
    modules: {
        usuario,
        menu,
        cidade,
        estado,
        orcamento,
        paciente,
        ficha,
        servico,
        peca,
        denteItem,
        servicoItem,
        pecaItem,
        relatorios,
        perfil,
        permissao,
        perfilPermissao,
        perfilUsuario,
        cobranca
    }
})
