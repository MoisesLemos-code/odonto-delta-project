export default {
    COMUM: {
        EFETUAR_LOGIN: 'efetuarLogin',
        VERIFICAR_TOKEN: 'verificarToken'
    },
    USUARIO: {
        BUSCAR_TODOS_USUARIOS: 'buscarTodosUsuarios',
        CADASTRAR_USUARIO: 'cadastrarUsuario',
        EDITAR_USUARIO: 'editarUsuario',
        BUSCAR_USUARIO_POR_ID: 'buscarUsuarioPorId'
    },
    PERFIL: {
        BUSCAR_TODOS_PERFIS: 'buscarTodosPerfis',
        CADASTRAR_PERFIL: 'cadastrarPerfil',
        EDITAR_PERFIL: 'editarPerfil',
        BUSCAR_PERFIL_POR_ID: 'buscarPerfilPorId',
        REMOVER_PERFIL: 'removerPerfil'
    },
    PERFIL_PERMISSAO: {
        BUSCAR_POR_PERFIL: 'buscarPorPerfil',
        CADASTRAR_PERMISSAO: 'cadastrarPermissao',
        EDITAR_PERMISSAO: 'editarPermissao',
        REMOVER_PERMISSAO: 'removerPermissao'
    },
    PERMISSAO: {
        BUSCAR_TODAS_PERMISSOES: 'buscarTodasPermissoes',
    },
    ROTULOS_PERSONALIZADOS: {
        GET_ALL_ROTULOS_PERSONALIZADOS: 'getAllRotulosPersonlizados',
    },
    ORCAMENTO: {
        BUSCAR_TODOS_ORCAMENTOS: 'buscarTodosOrcamentos',
        CADASTRAR_ORCAMENTO: 'cadastrarOrcamento',
        EDITAR_ORCAMENTO: 'editarOrcamento',
        EXCLUIR_ORCAMENTO: 'excluirOrcamento',
        BUSCAR_ORCAMENTO_POR_ID: 'buscarOrcamentoPorId',
        BUSCAR_ORCAMENTO_POR_ID_SIMPLES: 'buscarOrcamentoPorIdSimples',
        BURCAR_ORCAMENTO_MODAL_POR_ID: 'buscarOrcamentoModalPorId',
        BUSCAR_ORCAMENTOS_POR_PACIENTE: 'buscarOrcamentosPorPaciente',
        BUSCAR_DENTES_ITENS_ORCAMENTO: 'buscarDentesItensOrcamento',
        FINALIZAR_ORCAMENTO: 'finalizarOrcamento',
        SERVICO:{
            BUSCAR_TODOS_SERVICOS: 'buscarTodosServicos',
            BUSCAR_TODOS_SERVICOS_SEM_PAGINACAO: 'buscarTodosServicosSemPaginacao',
            CADASTRAR_SERVICO: 'cadastrarServico',
            EDITAR_SERVICO: 'editarServico',
            EXCLUIR_SERVICO: 'excluirServico',
            BUSCAR_SERVICO_POR_ID: 'buscarServicoPorId',
        },
        PECA:{
            BUSCAR_TODAS_PECAS: 'buscarTodasPecas',
            BUSCAR_TODAS_PECAS_SEM_PAGINACAO: 'buscarTodasPecasSemPaginacao',
            CADASTRAR_PECA: 'cadastrarPeca',
            EDITAR_PECA: 'editarPeca',
            EXCLUIR_PECA: 'excluirPeca',
            BUSCAR_PECA_POR_ID: 'buscarPecaPorId',
        },
        DENTE_ITEM:{
            BUSCAR_TODOS_DENTES_ITEM: 'buscarTodosDentesItem',
            BUSCAR_TODOS_DENTES_SEM_PAGINACAO_ITEM: 'buscarTodosDentesItemSemPaginacao',
            CADASTRAR_DENTE_ITEM: 'cadastrarDenteItem',
            EDITAR_DENTE_ITEM: 'editarDenteItem',
            EXCLUIR_DENTE_ITEM: 'excluirDenteItem',
            BUSCAR_DENTE_ITEM_POR_ID: 'buscarDenteItemPorId'
        },
        SERVICO_ITEM:{
            BUSCAR_TODOS_SERVICOS_ITEM: 'buscarTodosServicosItem',
            BUSCAR_TODOS_SERVICOS_ITEM_SEM_PAGINACAO: 'buscarTodosServicosItemSemPaginacao',
            CADASTRAR_SERVICO_ITEM: 'cadastrarServicoItem',
            EDITAR_SERVICO_ITEM: 'editarServicoItem',
            EXCLUIR_SERVICO_ITEM: 'excluirServicoItem',
            BUSCAR_SERVICO_ITEM_POR_ID: 'buscarServicoItemPorId',
            BUSCAR_SERVICOS_ITEM_POR_DENTE_ITEM: 'buscarServicosItemPorDenteItem'
        },
        PECA_ITEM:{
            BUSCAR_TODAS_PECAS_ITEM: 'buscarTodasPecasItem',
            BUSCAR_TODAS_PECAS_ITEM_SEM_PAGINACAO: 'buscarTodasPecasItemSemPaginacao',
            CADASTRAR_PECA_ITEM: 'cadastrarPecaItem',
            EDITAR_PECA_ITEM: 'editarPecaItem',
            EXCLUIR_PECA_ITEM: 'excluirPecaItem',
            BUSCAR_PECA_ITEM_POR_ID: 'buscarPecaItemPorId',
            BUSCAR_PECAS_ITEM_POR_DENTE_ITEM: 'buscarPecasItemPorDenteItem'
        },
    },
    CADASTROS: {
        PACIENTE: {
            BUSCAR_TODOS_PACIENTES_SEM_PAGINACAO: 'buscarTodosPacientesSemPaginacao',
            BUSCAR_TODOS_PACIENTES: 'buscarTodosPacientes',
            BUSCAR_TODOS_PACIENTES_ORTODONTIA: 'buscarTodosPacientesOrtodontia',
            CADASTRAR_PACIENTE: 'cadastrarPaciente',
            EDITAR_PACIENTE: 'editarPaciente',
            EXCLUIR_PACIENTE: 'excluirPaciente',
            BUSCAR_PACIENTE_POR_ID: 'buscarPacientePorId',
            FICHA:{
                EDITAR_FICHA: 'editarFicha',
                BUSCAR_POR_PACIENTE: 'buscarPorPaciente'
            }
        },
        CIDADE: {
            BUSCAR_TODAS_CIDADES: 'buscarTodasCidades',
            BUSCAR_TODAS_CIDADES_SEM_PAGINACAO: 'buscarTodasCidadesSemPaginacao',
            CADASTRAR_CIDADE: 'cadastrarCidade',
            EDITAR_CIDADE: 'editarCidade',
            EXCLUIR_CIDADE: 'excluirCidade',
            BUSCAR_CIDADE_POR_ID: 'buscarCidadePorId'
        },
        ESTADO: {
            BUSCAR_TODOS_ESTADOS_SEM_PAGINACAO: 'buscarTodosEstadosSemPaginacao'
        }
    },
    RELATORIOS:{
        GERAR_RELATORIO_PACIENTES: 'gerarRelatorioPacientes',
        GERAR_RELATORIO_ORCAMENTOS: 'gerarRelatorioOrcamentos'
    }
}
