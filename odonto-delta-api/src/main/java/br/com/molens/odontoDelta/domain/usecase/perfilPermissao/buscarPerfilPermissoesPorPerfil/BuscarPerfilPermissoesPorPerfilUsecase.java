package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil;

import br.com.molens.odontoDelta.domain.exception.BuscarPermissaoPorPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarPerfilPermissoesPorPerfilUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PermissaoDataProvider permissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;

    public BuscarPermissoesPorPerfilOutput executar(BuscarPermissoesPorPerfilInput input) {
        validarDadosEntrada(input);
        Perfil perfil = validarPerfil(input);
        List<PerfilPermissao> perfilPermissaoList = buscar(input);

        if(perfilPermissaoList.isEmpty()){
            List<Permissao> permissaoList = buscarTodasPermissoes();
            perfilPermissaoList = criarPermissoesPerfil(permissaoList, perfil);
        }

        return setarDados(perfilPermissaoList);
    }

    private void validarDadosEntrada(BuscarPermissoesPorPerfilInput input) {

        if (Objects.isNull(input.getPerfilId())) {
            throw new BuscarPermissaoPorPerfilException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarPermissaoPorPerfilException("Identificador de empresa inválido.");
        }
    }

    private Perfil validarPerfil(BuscarPermissoesPorPerfilInput input) {
        return perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId()).orElseThrow( () -> new BuscarPermissaoPorPerfilException("Perfil não identificado."));
    }

    private List<PerfilPermissao> buscar(BuscarPermissoesPorPerfilInput input){
        return perfilPermissaoDataProvider.buscarPorPerfil(input.getPerfilId());
    }

    private List<Permissao> buscarTodasPermissoes() {
        return permissaoDataProvider.buscarTodas();
    }

    private List<PerfilPermissao> criarPermissoesPerfil(List<Permissao> permissaoList, Perfil perfil) {
        List<PerfilPermissao> perfilPermissaoList = new ArrayList<>();
        for(Permissao permissao : permissaoList){
            PerfilPermissao perfilPermissao = PerfilPermissao.builder()
                    .perfil(perfil)
                    .permissao(permissao)
                    .ativo(Boolean.FALSE)
                    .build();

            perfilPermissaoList.add(perfilPermissao);
        }
        perfilPermissaoDataProvider.inserirTodos(perfilPermissaoList);
        return perfilPermissaoList;
    }

    private BuscarPermissoesPorPerfilOutput setarDados(List<PerfilPermissao> perfilPermissaoList) {
        List<BuscarPermissoesPorPerfilOutput.Permissao> permissaoList = new ArrayList<>();

        for(PerfilPermissao perfilPermissao : perfilPermissaoList){
            BuscarPermissoesPorPerfilOutput.Permissao item = new BuscarPermissoesPorPerfilOutput.Permissao();
            item.setId(perfilPermissao.getId());
            item.setPerfilId(perfilPermissao.getPerfil().getId());
            item.setPermissaoId(perfilPermissao.getPermissao().getId());
            item.setNome(perfilPermissao.getPermissao().getNome());
            item.setDescricao(perfilPermissao.getPermissao().getDescricao());
            item.setAtivo(perfilPermissao.getAtivo());
            permissaoList.add(item);
        }

        permissaoList.sort(new Comparator<BuscarPermissoesPorPerfilOutput.Permissao>() {
            @Override
            public int compare(BuscarPermissoesPorPerfilOutput.Permissao p1, BuscarPermissoesPorPerfilOutput.Permissao p2) {
                return p1.getNome().compareTo(p2.getNome());
            }
        });

        return BuscarPermissoesPorPerfilOutput.builder()
                .items(permissaoList)
                .build();
    }

}
