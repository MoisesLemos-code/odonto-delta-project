package br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.converter;

import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.Objects;

public class AtualizarPerfilOutputConverter extends GenericConverter<Perfil, AtualizarPerfilOutput> {

    @Override
    public AtualizarPerfilOutput to(Perfil source) {
        AtualizarPerfilOutput target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }


        return target;
    }

    public Perfil from(AtualizarPerfilInput source) {
        Perfil target = new Perfil();
            target.setId(source.getId());
            target.setNome(source.getNome());
            target.setDescricao(source.getDescricao());
            target.setAtivo(source.getAtivo());

            if (Objects.nonNull(source.getEmpresaId())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresaId())
                        .build());
            }

        return target;
    }
}