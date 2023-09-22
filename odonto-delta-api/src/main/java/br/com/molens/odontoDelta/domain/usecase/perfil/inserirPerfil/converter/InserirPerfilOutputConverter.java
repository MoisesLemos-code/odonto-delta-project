package br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.converter;

import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilInput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.Objects;

public class InserirPerfilOutputConverter  extends GenericConverter<Perfil, InserirPerfilInput> {

    @Override
    public Perfil from(InserirPerfilInput source) {
        Perfil target = super.from(source);

            if (Objects.nonNull(source.getEmpresaId())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresaId())
                        .build());
            }

        return target;
    }
}