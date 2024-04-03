export default {
    resultadoBuscaTodosUsuarios: {
        filtros: {
            conteudo: {
                default: null,
                label: 'Pesquisa',
                value: ''
            }
        },
        paginacao: {
            groupBy: [],
            groupDesc: [],
            multiSort: false,
            mustSort: false,
            page: 1,
            size: 10,
            sort: ['nome'],
            descending: false,
            sortDesc: [false]
        }
    },
    rota: {
        origem: 'UsuarioListagem'
    }
}
