package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.converter;

import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdOutput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Estado;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.Objects;

public class BuscarUsuarioPorIdOutputConverter extends GenericConverter<Usuario, BuscarUsuarioPorIdOutput> {

    @Override
    public BuscarUsuarioPorIdOutput to(Usuario source) {
        BuscarUsuarioPorIdOutput target = super.to(source);

        target.setSenha(null);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(Municipio.builder()
                    .id(source.getMunicipio().getId())
                    .nome(source.getMunicipio().getNome())
                            .estado(Estado.builder()
                                    .id(source.getMunicipio().getEstado().getId())
                                    .nome(source.getMunicipio().getEstado().getNome())
                                    .sigla(source.getMunicipio().getEstado().getSigla())
                                    .build())
                    .build());
        }


        return target;
    }
}