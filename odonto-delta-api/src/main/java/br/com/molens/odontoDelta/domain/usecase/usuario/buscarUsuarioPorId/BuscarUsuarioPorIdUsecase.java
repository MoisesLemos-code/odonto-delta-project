package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.BuscarUsuarioPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.converter.BuscarUsuarioPorIdOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class BuscarUsuarioPorIdUsecase {

    private UsuarioDataProvider usuarioDataProvider;
    private BuscarUsuarioPorIdOutputConverter outputConverter;

    public BuscarUsuarioPorIdOutput executar(BuscarUsuarioPorIdInput input) {
        validarDadosEntrada(input);
        return outputConverter.to(buscarUsuario(input));
    }

    private void validarDadosEntrada(BuscarUsuarioPorIdInput input) {

        if (Objects.isNull(input.getUsuarioId())) {
            throw new BuscarUsuarioPorIdException("Identificador de usuário inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarUsuarioPorIdException("Identificador de empresa inválido.");
        }
    }
    private Usuario buscarUsuario(BuscarUsuarioPorIdInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarPorIdEmpresaId(input.getUsuarioId(), input.getEmpresaId());

        if (!usuario.isPresent()) {
            throw new BuscarPacientePorIdException("Usuário não encontrado.");
        }
        return usuario.get();
    }

}
