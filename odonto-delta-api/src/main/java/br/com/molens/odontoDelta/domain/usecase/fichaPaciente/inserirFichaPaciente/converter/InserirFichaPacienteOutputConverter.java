package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.converter;

import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteInput;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;

public class InserirFichaPacienteOutputConverter extends GenericConverter<FichaPaciente, InserirFichaPacienteInput> {

    @Override
    public FichaPaciente from(InserirFichaPacienteInput source) {
        FichaPaciente target = super.from(source);


            if (Objects.nonNull(source.getPacienteId())) {
                target.setPaciente(Paciente.builder()
                        .id(source.getPacienteId())
                        .build());
            }

        return target;
    }
}