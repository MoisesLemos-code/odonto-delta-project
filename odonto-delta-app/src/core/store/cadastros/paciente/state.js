export default {
    dadosGerais: {},
    rotaOrigem: {name: 'PacienteListagem'},
    resultadoBuscaTodosPacientes: {
        filtros: {
            tipo:{
                default: null,
                data: [ 'Começa com', 'Contém', 'Termina com'],
                value: ''
            },
            atributo: {
                default: null,
                data: [ 'Nome', 'Documento', 'E-mail', 'Telefone'],
                value: ''
            },
            conteudo: {
                default: null,
                label: 'Pesquisa',
                value: ''
            },
            ortodontia: {
                default: null,
                label: 'Ortodontia',
                value: '',
                valueId: null,
            },
            estadoId: {
                default: null,
                label: 'Estado',
                value: '',
                valueId: null
            },
            municipioId: {
                default: null,
                label: 'Cidade',
                value: '',
                valueId: null
            },
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