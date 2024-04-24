package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.converter;

import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteInput;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;

public class AtualizarFichaPacienteOutputConverter extends GenericConverter<FichaPaciente, AtualizarFichaPacienteInput> {

    @Override
    public FichaPaciente from(AtualizarFichaPacienteInput source) {
        FichaPaciente target = super.from(source);


            if (Objects.nonNull(source.getPacienteId())) {
                target.setPaciente(Paciente.builder()
                        .id(source.getPacienteId())
                        .build());
            }

        return target;
    }
}