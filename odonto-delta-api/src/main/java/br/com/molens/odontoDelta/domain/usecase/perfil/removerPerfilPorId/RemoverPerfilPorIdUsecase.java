package br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId;

import br.com.molens.odontoDelta.domain.exception.RemoverPerfilPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class RemoverPerfilPorIdUsecase {

    private PerfilDataProvider perfilDataProvider;

    public void executar(RemoverPerfilPorIdInput input) {
        validarDadosEntrada(input);
        buscarPerfil(input);
        removerPerfil(input);
    }

    private void validarDadosEntrada(RemoverPerfilPorIdInput input) {

        if (Objects.isNull(input.getPerfilId())) {
            throw new RemoverPerfilPorIdException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new RemoverPerfilPorIdException("Identificador de empresa inválido.");
        }
    }

    private void buscarPerfil(RemoverPerfilPorIdInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId());

        if (!perfil.isPresent()) {
            throw new RemoverPerfilPorIdException("Perfil não encontrado.");
        }
    }

    private void removerPerfil(RemoverPerfilPorIdInput input) {
        perfilDataProvider.remover(input.getPerfilId());
    }
}
