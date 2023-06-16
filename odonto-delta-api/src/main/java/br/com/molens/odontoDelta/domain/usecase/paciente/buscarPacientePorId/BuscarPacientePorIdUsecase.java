package br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId;

import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class BuscarPacientePorIdUsecase {

    private PacienteDataProvider pacienteDataProvider;

    public Paciente executar(BuscarPacientePorIdInput input) {
        validarDadosEntrada(input);
        return buscarPaciente(input);
    }

    private void validarDadosEntrada(BuscarPacientePorIdInput input) {

        if (Objects.isNull(input.getPacienteId())) {
            throw new BuscarPacientePorIdException("Identificador de paciente inválido.");
        }
    }

    private Paciente buscarPaciente(BuscarPacientePorIdInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorId(input.getPacienteId());

        if (!paciente.isPresent()) {
            throw new BuscarPacientePorIdException("Paciente não encontrado.");
        }
        return paciente.get();
    }

}
