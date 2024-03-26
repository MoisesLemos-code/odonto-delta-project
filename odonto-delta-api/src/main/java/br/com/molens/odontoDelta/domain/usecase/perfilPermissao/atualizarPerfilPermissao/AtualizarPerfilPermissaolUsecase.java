package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilInput;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarPerfilPermissaolUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    public void executar(AtualizarPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        List<PerfilPermissao> perfilPermissao = buscarPermissoes(input);
        atualizarPermissao(perfilPermissao, input);
    }

    private void validarDadosEntrada(AtualizarPerfilPermissaoInput input) {
        if (Objects.isNull(input.getPerfilId())) {
            throw new AtualizarPerfilPermissaoException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new AtualizarPerfilPermissaoException("Identificador de empresa inválido.");
        }
        if (Objects.isNull(input.getItems()) || input.getItems().isEmpty()) {
            throw new AtualizarPerfilPermissaoException("Identificador de permissões inválido.");
        }
    }

    private List<PerfilPermissao> buscarPermissoes(AtualizarPerfilPermissaoInput input){
        return perfilPermissaoDataProvider.buscarPorPerfil(input.getPerfilId());
    }

    private void atualizarPermissao(List<PerfilPermissao> perfilPermissaoList, AtualizarPerfilPermissaoInput input) {

        for(PerfilPermissao permissao : perfilPermissaoList){
            Optional<AtualizarPerfilPermissaoInput.Permissao> permissaoInput = input.getItems().stream().filter(item -> item.getId().equals(permissao.getId())).findFirst();
            permissaoInput.ifPresent(value -> permissao.setAtivo(value.getAtivo()));
        }

        perfilPermissaoDataProvider.inserirTodos(perfilPermissaoList);
    }

}
