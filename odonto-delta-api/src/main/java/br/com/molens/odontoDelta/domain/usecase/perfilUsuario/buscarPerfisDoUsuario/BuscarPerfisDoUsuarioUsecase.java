package br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario;

import br.com.molens.odontoDelta.domain.exception.BuscarPerfisDoUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilUsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import br.com.molens.odontoDelta.gateway.entity.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class BuscarPerfisDoUsuarioUsecase {

    private PerfilDataProvider perfilDataProvider;
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilUsuarioDataProvider perfilUsuarioDataProvider;

    public BuscarPerfisDoUsuarioOutput executar(BuscarPerfisDoUsuarioInput input){
        validarDadosEntrada(input);
        List<Perfil> perfilList = buscarPerfisAtivos(input);
        List<PerfilUsuario> perfilUsuarioList = buscarPerfisDoUsuario(input);

        return setarDados(perfilList, perfilUsuarioList);
    }

    private BuscarPerfisDoUsuarioOutput setarDados(List<Perfil> perfilList, List<PerfilUsuario> perfilUsuarioList) {
        BuscarPerfisDoUsuarioOutput output = new BuscarPerfisDoUsuarioOutput();
        List<BuscarPerfisDoUsuarioOutput.Perfil> perfisList = new ArrayList<>();

        for(Perfil perfil : perfilList){
            BuscarPerfisDoUsuarioOutput.Perfil perfilUsuario = new BuscarPerfisDoUsuarioOutput.Perfil();
            Optional<PerfilUsuario> isPerfilAtivo = perfilUsuarioList.stream().filter(item -> item.getPerfil().getId().equals(perfil.getId())).findFirst();

            perfilUsuario.setIdPerfil(perfil.getId());
            perfilUsuario.setNome(perfil.getNome());
            perfilUsuario.setDescricao(perfil.getDescricao());
            perfilUsuario.setAtivo(isPerfilAtivo.isPresent());

            List<PerfilPermissao> perfilPermissaoList = buscarPerfilPermissoesPorPerfil(perfil.getId());
            perfilUsuario.setPermissoes(setarPerfilPermissoes(perfilPermissaoList));

            perfisList.add(perfilUsuario);
        }

        perfisList.sort(new Comparator<BuscarPerfisDoUsuarioOutput.Perfil>() {
            @Override
            public int compare(BuscarPerfisDoUsuarioOutput.Perfil p1, BuscarPerfisDoUsuarioOutput.Perfil p2) {
                return p1.getNome().compareTo(p2.getNome());
            }
        });

        output.setItems(perfisList);

        return output;
    }

    private List<BuscarPerfisDoUsuarioOutput.Permissao> setarPerfilPermissoes(List<PerfilPermissao> perfilPermissaoList) {
        List<BuscarPerfisDoUsuarioOutput.Permissao> permissaoList = new ArrayList<>();

        for(PerfilPermissao perfilPermissao : perfilPermissaoList){
            BuscarPerfisDoUsuarioOutput.Permissao permissao = new BuscarPerfisDoUsuarioOutput.Permissao();

            permissao.setIdPermissao(perfilPermissao.getPermissao().getId());
            permissao.setNome(perfilPermissao.getPermissao().getNome());
            permissao.setDescricao(perfilPermissao.getPermissao().getDescricao());
            permissao.setAtivo(perfilPermissao.getAtivo());

            permissaoList.add(permissao);
        }

        permissaoList.sort(new Comparator<BuscarPerfisDoUsuarioOutput.Permissao>() {
            @Override
            public int compare(BuscarPerfisDoUsuarioOutput.Permissao p1, BuscarPerfisDoUsuarioOutput.Permissao p2) {
                return p1.getNome().compareTo(p2.getNome());
            }
        });

        return permissaoList;
    }

    private void validarDadosEntrada(BuscarPerfisDoUsuarioInput input) {

        if (Objects.isNull(input.getUsuarioId())) {
            throw new BuscarPerfisDoUsuarioException("Identificador de usuário inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarPerfisDoUsuarioException("Identificador de empresa inválido.");
        }
    }

    private List<Perfil> buscarPerfisAtivos(BuscarPerfisDoUsuarioInput input) {
        return perfilDataProvider.buscarAtivosPorEmpresaId(input.getEmpresaId());
    }

    private List<PerfilPermissao> buscarPerfilPermissoesPorPerfil(Long perfilId) {
        return perfilPermissaoDataProvider.buscarPorPerfil(perfilId);
    }

    private List<PerfilUsuario> buscarPerfisDoUsuario(BuscarPerfisDoUsuarioInput input) {
        return perfilUsuarioDataProvider.buscarPorUsuario(input.getUsuarioId());
    }


}
