package br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.converter;

import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdOutput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;

public class BuscarPacientePorIdOutputConverter extends GenericConverter<Paciente, BuscarPacientePorIdOutput> {

    @Override
    public BuscarPacientePorIdOutput to(Paciente source) {
        BuscarPacientePorIdOutput target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }


        return target;
    }
}