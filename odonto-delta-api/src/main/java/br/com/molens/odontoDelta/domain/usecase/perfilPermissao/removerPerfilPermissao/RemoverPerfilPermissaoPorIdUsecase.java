package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.RemoverPerfilPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class RemoverPerfilPermissaoPorIdUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    public void executar(RemoverPerfilPermissaoPorIdInput input) {
        validarDadosEntrada(input);
        buscarPermissao(input);
        removerPermissao(input);
    }

    private void validarDadosEntrada(RemoverPerfilPermissaoPorIdInput input) {

        if (Objects.isNull(input.getPerfilPermissaoId())) {
            throw new BuscarPacientePorIdException("Identificador de perfil permissão inválido.");
        }
    }

    private void buscarPermissao(RemoverPerfilPermissaoPorIdInput input) {
        Optional<PerfilPermissao> perfilPermissao = perfilPermissaoDataProvider.buscarPorId(input.getPerfilPermissaoId());

        if (!perfilPermissao.isPresent()) {
            throw new RemoverPerfilPorIdException("Perfil Permissão não encontrada.");
        }
    }

    private void removerPermissao(RemoverPerfilPermissaoPorIdInput input) {
        perfilPermissaoDataProvider.remover(input.getPerfilPermissaoId());
    }
}
