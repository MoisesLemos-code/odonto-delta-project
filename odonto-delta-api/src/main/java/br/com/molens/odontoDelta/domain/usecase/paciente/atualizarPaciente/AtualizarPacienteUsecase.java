package br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente;

import br.com.molens.odontoDelta.domain.exception.AtualizarPacienteException;
import br.com.molens.odontoDelta.domain.exception.JaExistePacienteCnpjCpfException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.converter.AtualizarPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private AtualizarPacienteOutputConverter outputConverter;

    public AtualizarPacienteOuput executar(AtualizarPacienteInput input) {
        validarDadosEntrada(input);
        buscarPaciente(input);
        validarPacienteCnpjJaCadastrado(input);
        Paciente paciente = atualizarPaciente(outputConverter.from(input));
        return outputConverter.to(paciente);
    }

    private void validarDadosEntrada(AtualizarPacienteInput input) {

        if (Objects.isNull(input.getPacienteId())) {
            throw new AtualizarPacienteException("Identificador de paciente é obrigatório.");
        }
        if (input.getEmpresaId() == 0) {
            throw new AtualizarPacienteException("Identificador de empresa inválido.");
        }
    }

    private void buscarPaciente(AtualizarPacienteInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId());

        if (!paciente.isPresent()) {
            throw new AtualizarPacienteException("Paciente não encontrado.");
        }
    }

    private void validarPacienteCnpjJaCadastrado(AtualizarPacienteInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorCnpjCpf(input.getCnpjCpf());
        if (paciente.isPresent()) {
            if (paciente.get().getId() != input.getPacienteId()) {
                throw new JaExistePacienteCnpjCpfException();
            }
        }
    }

    private Paciente atualizarPaciente(Paciente paciente) {
        return pacienteDataProvider.atualizar(paciente);
    }

}