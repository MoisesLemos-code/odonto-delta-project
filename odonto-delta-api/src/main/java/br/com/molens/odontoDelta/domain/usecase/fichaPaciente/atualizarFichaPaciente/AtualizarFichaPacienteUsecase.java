package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente;

import br.com.molens.odontoDelta.domain.exception.AtualizarFichaPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.converter.AtualizarFichaPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class AtualizarFichaPacienteUsecase {

    private FichaPacienteDataProvider fichaPacienteDataProvider;
    private AtualizarFichaPacienteOutputConverter outputConverter;

    public AtualizarFichaPacienteOutput executar(AtualizarFichaPacienteInput input) {
        validarDadosEntrada(input);
        buscarFichaDePaciente(input);
        return atualizarFicha(input);
    }

    private void validarDadosEntrada(AtualizarFichaPacienteInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarFichaPacienteException("Identificador de ficha de paciente inválido.");
        }
        if (Objects.isNull(input.getPacienteId())) {
            throw new AtualizarFichaPacienteException("Identificador de paciente inválido.");
        }
    }

    private void buscarFichaDePaciente(AtualizarFichaPacienteInput input) {
        fichaPacienteDataProvider.buscarPorId(input.getId()).orElseThrow(() -> new AtualizarFichaPacienteException("Ficha de paciente não encontrada!"));
    }

    private AtualizarFichaPacienteOutput atualizarFicha(AtualizarFichaPacienteInput input) {
        FichaPaciente fichaPaciente = fichaPacienteDataProvider.atualizar(outputConverter.from(input));
        return AtualizarFichaPacienteOutput.builder().fichaPacienteId(fichaPaciente.getId()).build();
    }
}
