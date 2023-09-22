package br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.converter.BuscarPacientePorIdOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

@Builder
@AllArgsConstructor
public class BuscarPacientePorIdUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private BuscarPacientePorIdOutputConverter outputConverter;

    public BuscarPacientePorIdOutput executar(BuscarPacientePorIdInput input) {
        validarDadosEntrada(input);
        return outputConverter.to(buscarPaciente(input));
    }

    private void validarDadosEntrada(BuscarPacientePorIdInput input) {

        if (input.getPacienteId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de paciente inválido.");
        }
        if (input.getEmpresaId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de empresa inválido.");
        }
    }

    private Paciente buscarPaciente(BuscarPacientePorIdInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId());

        if (!paciente.isPresent()) {
            throw new BuscarPacientePorIdException("Paciente não encontrado.");
        }
        return paciente.get();
    }

}
