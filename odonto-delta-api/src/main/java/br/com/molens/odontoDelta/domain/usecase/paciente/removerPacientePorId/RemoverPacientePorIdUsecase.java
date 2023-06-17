package br.com.molens.odontoDelta.domain.usecase.paciente.removerPacientePorId;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.RemoverPacientePorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

@Builder
@AllArgsConstructor
public class RemoverPacientePorIdUsecase {

    private PacienteDataProvider pacienteDataProvider;

    public void executar(RemoverPacientePorIdInput input) {
        validarDadosEntrada(input);
        buscarPaciente(input);
        removerPaciente(input);
    }

    private void validarDadosEntrada(RemoverPacientePorIdInput input) {

        if (input.getPacienteId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de paciente inválido.");
        }
        if (input.getEmpresaId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de empresa inválido.");
        }
    }

    private void buscarPaciente(RemoverPacientePorIdInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId());

        if (!paciente.isPresent()) {
            throw new RemoverPacientePorIdException("Paciente não encontrado.");
        }
    }

    private void removerPaciente(RemoverPacientePorIdInput input) {
        pacienteDataProvider.remover(input.getPacienteId());
    }
}
