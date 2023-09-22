package br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.RemoverPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.RemoverPerfilPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.removerPacientePorId.RemoverPacientePorIdInput;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;

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

        if (input.getPerfilId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de paciente inválido.");
        }
        if (input.getEmpresaId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de empresa inválido.");
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
