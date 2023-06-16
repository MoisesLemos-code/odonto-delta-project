package br.com.molens.odontoDelta.gateway.dataprovider.converter;

import br.com.molens.odontoDelta.domain.entity.Estado;
import br.com.molens.odontoDelta.domain.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EstadoEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MunicipioConverter extends GenericConverter<MunicipioEntity, Municipio> {

    @Override
    public Municipio to(MunicipioEntity source) {
        Municipio target = super.to(source);

        if (Objects.nonNull(source.getEstado())) {
            target.setEstado(Estado.builder()
                    .id(source.getEstado().getId())
                    .nome(source.getEstado().getNome())
                    .sigla(source.getEstado().getSigla())
                    .build());
        }

        return target;
    }

    @Override
    public MunicipioEntity from(Municipio source) {
        MunicipioEntity target = super.from(source);

        if (Objects.nonNull(source.getEstado())) {
            target.setEstado(EstadoEntity.builder()
                    .id(source.getEstado().getId())
                    .build());
        }

        return target;
    }
}
