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
            itemsPerPage: 6,
            multiSort: false,
            mustSort: false,
            page: 1,
            rowsPerPage: 6,
            sortBy: ['id'],
            descending: false,
            sortDesc: [false]
        }
    },
    rota: {
        origem: 'UsuarioListagem'
    }
}
