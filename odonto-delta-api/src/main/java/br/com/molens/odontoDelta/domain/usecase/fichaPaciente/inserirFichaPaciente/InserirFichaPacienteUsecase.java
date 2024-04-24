package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente;

import br.com.molens.odontoDelta.domain.exception.InserirFichaPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.converter.InserirFichaPacienteOutputConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class InserirFichaPacienteUsecase {

    private FichaPacienteDataProvider fichaPacienteDataProvider;
    private PacienteDataProvider pacienteDataProvider;
    private InserirFichaPacienteOutputConverter outputConverter;

    public InserirFichaPacienteOutput executar(InserirFichaPacienteInput input){
        validarDadosEntrada(input);
        validarPaciente(input);
        return inserirFichaPacinete(input);
    }

    private void validarDadosEntrada(InserirFichaPacienteInput input) {

        if (Objects.isNull(input.getPacienteId())) {
            throw new InserirFichaPacienteException("Identificador de paciente inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirFichaPacienteException("Identificador de empresa inválido.");
        }
    }

    private void validarPaciente(InserirFichaPacienteInput input) {
        pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId()).orElseThrow(() -> new InserirFichaPacienteException("Paciente não encontrado!"));
    }

    private InserirFichaPacienteOutput inserirFichaPacinete(InserirFichaPacienteInput input) {
        Long fichaPacienteId = fichaPacienteDataProvider.inserir(outputConverter.from(input));

        return new InserirFichaPacienteOutput(fichaPacienteId);
    }
}
