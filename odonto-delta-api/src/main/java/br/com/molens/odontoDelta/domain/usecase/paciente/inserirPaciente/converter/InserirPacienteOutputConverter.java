package br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.converter;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Municipio;
import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteInput;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class InserirPacienteOutputConverter extends GenericConverter<Paciente, InserirPacienteInput> {

    @Override
    public Paciente from(InserirPacienteInput source) {
        Paciente target = super.from(source);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            target.setDataNascimento(sdf.parse(source.getDataNascimento()));

            if (Objects.nonNull(source.getEmpresaId())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresaId())
                        .build());
            }

            if (Objects.nonNull(source.getMunicipioId())) {
                target.setMunicipio(Municipio.builder()
                        .id(source.getMunicipioId())
                        .build());
            }

        } catch (ParseException e) {
            throw new DataEmFormatoIncorretoException();
        }

        return target;
    }
}