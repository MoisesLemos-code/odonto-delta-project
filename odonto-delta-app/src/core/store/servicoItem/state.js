export default {
    resultadoBuscaTodosServicosItem: {
        filtros: {
            idDenteItem: {
                default: null,
                label: 'Pesquisa',
                value: ''
            }
        },
        paginacao: {
            groupBy: [],
            groupDesc: [],
            itemsPerPage: 5,
            multiSort: false,
            mustSort: false,
            page: 1,
            rowsPerPage: 5,
            sortBy: ['id'],
            descending: false,
            sortDesc: [false]
        }
    }
}