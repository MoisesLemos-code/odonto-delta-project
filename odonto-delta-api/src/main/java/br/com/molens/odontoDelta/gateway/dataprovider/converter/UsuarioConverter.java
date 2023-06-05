package br.com.molens.odontoDelta.gateway.dataprovider.converter;

import br.com.molens.odontoDelta.domain.entity.*;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PerfilEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.UsuarioEntity;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsuarioConverter extends GenericConverter<UsuarioEntity, Usuario> {

    @Override
    public Usuario to(UsuarioEntity source) {
        Usuario target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                            .id(source.getEmpresa().getId())
                            .razaoSocial(source.getEmpresa().getRazaoSocial())
                            .email(source.getEmpresa().getEmail())
                            .nome(source.getEmpresa().getNome())
                            .cnpjCpf(source.getEmpresa().getCnpjCpf())
                            .plano(Plano.builder()
                                    .id(source.getEmpresa().getPlano().getId())
                                    .ciclo(source.getEmpresa().getPlano().getCiclo())
                                    .build())
                    .build());
        }

        if (Objects.nonNull(source.getPerfil())) {
            target.setPerfil(Perfil.builder()
                            .id(source.getPerfil().getId())
                            .nome(source.getPerfil().getNome())
                            .ativo(source.getPerfil().getAtivo())
                    .build());
        }

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(Municipio.builder()
                            .id(source.getMunicipio().getId())
                            .nome(source.getMunicipio().getNome())
                    .build());
        }

        return target;
    }

    @Override
    public UsuarioEntity from(Usuario source) {
        UsuarioEntity target = super.from(source);

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(MunicipioEntity.builder()
                            .id(source.getMunicipio().getId())
                    .build());
        }

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(EmpresaEntity.builder()
                            .id(source.getEmpresa().getId())
                    .build());
        }

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(EmpresaEntity.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }

        if (Objects.nonNull(source.getPerfil())) {
            target.setPerfil(PerfilEntity.builder()
                            .id(source.getPerfil().getId())
                    .build());
        }

        return target;
    }
}
