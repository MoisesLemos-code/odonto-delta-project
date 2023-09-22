package br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.converter;

import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.Objects;

public class BuscarPerfilPorIdOutputConverter extends GenericConverter<Perfil, BuscarPerfilPorIdOutput> {

    @Override
    public BuscarPerfilPorIdOutput to(Perfil source) {
        BuscarPerfilPorIdOutput target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }


        return target;
    }
}