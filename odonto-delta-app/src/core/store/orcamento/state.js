export default {
    dadosGerais: {},
    resultadoBuscaTodosOrcamentos: {
        filtros: {
            tipo:{
                default: null,
                data: [ 'Começa com', 'Contém', 'Termina com'],
                value: ''
            },
            atributo: {
                default: null,
                data: [ 'paciente', 'cpfOuCnpj', 'codigo'],
                value: ''
            },
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
    resultadoBuscaTodosOrcamentosPorPaciente: {
        filtros: {
            conteudo: {
                default: null,
                label: 'Pesquisa',
                value: ''
            },
            idPaciente: {
                default: null,
                value: ''
            },
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
        origem: 'OrcamentoListagem'
    }
}