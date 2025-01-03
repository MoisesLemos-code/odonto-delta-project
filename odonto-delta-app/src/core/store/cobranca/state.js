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
            page: 1,
            size: 10,
            sort: 'codigo',
            direction: 'ASC',
        }
    },
    rota: {
        origem: 'CobrancaListagem'
    }
}