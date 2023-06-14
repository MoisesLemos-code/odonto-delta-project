package br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente;


import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.converter.InserirPacienteOutputConverter;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.exception.InserirPacienteException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private InserirPacienteOutputConverter inserirPacienteOutputConverter;

    public Paciente executar(InserirPacienteInput input){
        validarDadosEntrada(input);
        validarEmpresa(input);
        return pacienteDataProvider.inserir(inserirPacienteOutputConverter.from(input));
    }

    private void validarDadosEntrada(InserirPacienteInput input) {

        if(Objects.isNull(input.getEmpresaId()) || input.getEmpresaId() == 0){
            throw new InserirPacienteException("Identificador de empresa inválido.");
        }
    }

    private void validarEmpresa(InserirPacienteInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());
        if(!empresa.isPresent()){
            throw new InserirPacienteException("Empresa não identificada.");
        }
    }
}
