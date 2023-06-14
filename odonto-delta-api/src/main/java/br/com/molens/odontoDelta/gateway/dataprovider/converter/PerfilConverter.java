package br.com.molens.odontoDelta.gateway.dataprovider.converter;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Perfil;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PerfilEntity;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PerfilConverter extends GenericConverter<PerfilEntity, Perfil> {

    @Override
    public Perfil to(PerfilEntity source) {
        Perfil target = super.to(source);

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
    public PerfilEntity from(Perfil source) {
        PerfilEntity target = super.from(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(EmpresaEntity.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }

        return target;
    }
}
