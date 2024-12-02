export default {
    resultadoBuscaTodasCobrancas: {
        filtros: {
            tipo:{
                default: null,
                data: [ 'Começa com', 'Contém', 'Termina com'],
                value: ''
            },
            atributo: {
                default: null,
                data: [ 'Codigo'],
                value: ''
            },
            conteudo: {
                default: null,
                label: 'Pesquisa',
                value: ''
            },
            statusEnum: {
                default: null,
                label: 'Status',
                value: 'STATUS'
            },
            dataVencimento: {
                default: null,
                label: 'Data Vencimento',
                value: ''
            },
            pacienteId: {
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
        origem: 'CobrancaListagem'
    }
}