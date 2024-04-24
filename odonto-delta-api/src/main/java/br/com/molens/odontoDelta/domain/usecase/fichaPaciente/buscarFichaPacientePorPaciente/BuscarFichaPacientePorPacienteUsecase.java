package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente;

import br.com.molens.odontoDelta.domain.exception.BuscarFichaPacientePorPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.converter.BuscarFichaPacientePorPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarFichaPacientePorPacienteUsecase {

    private FichaPacienteDataProvider fichaPacienteDataProvider;
    private BuscarFichaPacientePorPacienteOutputConverter outputConverter;
    
    public BuscarFichaPacientePorPacienteOutput executar(BuscarFichaPacientePorPacienteInput input){
        validarDadosEntrada(input);
        FichaPaciente fichaPaciente = buscarFichaPaciente(input);
        return outputConverter.to(fichaPaciente);
    }

    private void validarDadosEntrada(BuscarFichaPacientePorPacienteInput input) {

        if (Objects.isNull(input.getPacienteId())) {
            throw new BuscarFichaPacientePorPacienteException("Identificador de paciente inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarFichaPacientePorPacienteException("Identificador de empresa inválido.");
        }
    }

    private FichaPaciente buscarFichaPaciente(BuscarFichaPacientePorPacienteInput input) {
        List<FichaPaciente> fichaPacienteList =  fichaPacienteDataProvider.buscarPorPaciente(input.getPacienteId());

        if(!fichaPacienteList.isEmpty())
            return fichaPacienteList.get(0);

        return FichaPaciente.builder().build();
    }
}
