package br.com.molens.odontoDelta.gateway.dataprovider.converter;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Estado;
import br.com.molens.odontoDelta.domain.entity.Municipio;
import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PacienteEntity;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PacienteConverter extends GenericConverter<PacienteEntity, Paciente> {

    @Override
    public Paciente to(PacienteEntity source) {
        Paciente target = super.to(source);

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(Municipio.builder()
                    .id(source.getMunicipio().getId())
                    .nome(source.getMunicipio().getNome())
                    .codigoIbge(source.getMunicipio().getCodigoIbge())
                    .estado(Estado.builder()
                            .id(source.getMunicipio().getEstado().getId())
                            .sigla(source.getMunicipio().getEstado().getSigla())
                            .nome(source.getMunicipio().getEstado().getNome())
                            .build())
                    .build());
        }

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                            .id(source.getEmpresa().getId())
                            .nome(source.getEmpresa().getNome())
                            .razaoSocial(source.getEmpresa().getRazaoSocial())
                            .cnpjCpf(source.getEmpresa().getCnpjCpf())
                    .build());
        }

        return target;
    }

    @Override
    public PacienteEntity from(Paciente source) {
        PacienteEntity target = super.from(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(EmpresaEntity.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(MunicipioEntity.builder()
                    .id(source.getMunicipio().getId())
                    .build());
        }

        return target;
    }
}
