package br.com.molens.odontoDelta.gateway.dataprovider.converter;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Estado;
import br.com.molens.odontoDelta.domain.entity.Municipio;
import br.com.molens.odontoDelta.domain.entity.Plano;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PlanoEntity;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmpresaConverter extends GenericConverter<EmpresaEntity, Empresa> {

    @Override
    public Empresa to(EmpresaEntity source) {
        Empresa target = super.to(source);

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

        if (Objects.nonNull(source.getPlano())) {
            target.setPlano(Plano.builder()
                            .id(source.getPlano().getId())
                            .ciclo(source.getPlano().getCiclo())
                            .descricao(source.getPlano().getDescricao())
                            .disponivel(source.getPlano().getDisponivel())
                            .valor(source.getPlano().getValor())
                    .build());
        }

        return target;
    }

    @Override
    public EmpresaEntity from(Empresa source) {
        EmpresaEntity target = super.from(source);

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(MunicipioEntity.builder()
                    .id(source.getMunicipio().getId())
                    .build());
        }

        if (Objects.nonNull(source.getPlano())) {
            target.setPlano(PlanoEntity.builder()
                    .id(source.getPlano().getId())
                    .build());
        }

        return target;
    }
}
