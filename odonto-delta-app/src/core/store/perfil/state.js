export default {
    resultadoBuscaTodosPerfis: {
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
            itemsPerPage: 10,
            multiSort: false,
            mustSort: false,
            page: 1,
            rowsPerPage: 10,
            sortBy: ['id'],
            descending: false,
            sortDesc: [false]
        }
    },
    rota: {
        origem: 'GerenciarPerfis'
    }
}
