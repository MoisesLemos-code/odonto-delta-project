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
            page: 1,
            size: 10,
            sort: 'nome',
            direction: 'ASC',
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
            page: 1,
            size: 10,
            sort: 'nome',
            direction: 'ASC',
        }
    }
}