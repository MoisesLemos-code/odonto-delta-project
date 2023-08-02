export default {
    dadosGerais: {},
    resultadoBuscaTodosPacientes: {
        filtros: {
            tipo:{
                default: null,
                data: [ 'Começa com', 'Contém', 'Termina com'],
                value: ''
            },
            atributo: {
                default: null,
                data: [ 'nome', 'cpfOuCnpj', 'email', 'cidade'],
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
            sortBy: ['nome'],
            descending: false,
            sortDesc: [false],
            empresaId: 0,
        }
    },
    resultadoBuscaTodosPacientesOrtodontia: {
        filtros: {
            tipo:{
                default: null,
                data: [ 'Começa com', 'Contém', 'Termina com'],
                value: ''
            },
            atributo: {
                default: null,
                data: [ 'nome', 'cpfOuCnpj', 'email', 'cidade'],
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
            sortBy: ['nome'],
            descending: false,
            sortDesc: [false]
        }
    }
}