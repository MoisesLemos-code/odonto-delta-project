package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilInput;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarPerfilPermissaolUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    public void executar(AtualizarPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        atualizarPerfilPermissao(input);
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

    private void atualizarPerfilPermissao(AtualizarPerfilPermissaoInput input) {
        List<PerfilPermissao> perfilPermissaoList = new ArrayList<>();

        if(!input.getItems().isEmpty()){
            for(AtualizarPerfilPermissaoInput.Permissao item : input.getItems()){
                PerfilPermissao perfilPermissao = new PerfilPermissao();

                perfilPermissao.setId(item.getId());
                perfilPermissao.setPerfil(Perfil.builder().id(input.getPerfilId()).build());
                perfilPermissao.setPermissao(Permissao.builder().id(item.getPermissaoId()).build());
                perfilPermissao.setAtivo(item.getAtivo());

                perfilPermissaoList.add(perfilPermissao);
            }
        }

        perfilPermissaoDataProvider.inserirTodos(perfilPermissaoList);
    }

}
