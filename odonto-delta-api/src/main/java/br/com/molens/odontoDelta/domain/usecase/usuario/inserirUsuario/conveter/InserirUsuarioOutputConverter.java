package br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.conveter;

import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioInput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;

public class InserirUsuarioOutputConverter extends GenericConverter<Usuario, InserirUsuarioInput> {

    @Override
    public Usuario from(InserirUsuarioInput source) {
        Usuario target = super.from(source);

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

        if (Objects.nonNull(source.getPerfilId())) {
            target.setPerfil(Perfil.builder()
                    .id(source.getPerfilId())
                    .build());
        }

        return target;
    }
}