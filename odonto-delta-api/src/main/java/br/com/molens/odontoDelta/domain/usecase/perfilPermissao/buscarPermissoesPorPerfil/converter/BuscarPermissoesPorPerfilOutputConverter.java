package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.converter;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuscarPermissoesPorPerfilOutputConverter {

    public BuscarPermissoesPorPerfilOutput to(List<PerfilPermissao> from) {
        BuscarPermissoesPorPerfilItemConverter outputDataItemConverter = new BuscarPermissoesPorPerfilItemConverter();

        return BuscarPermissoesPorPerfilOutput
                .builder()
                .items(from
                        .stream()
                        .map(outputDataItemConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

    private static class BuscarPermissoesPorPerfilItemConverter extends GenericConverter<PerfilPermissao, BuscarPermissoesPorPerfilOutput.Permissao> {

        @Override
        public BuscarPermissoesPorPerfilOutput.Permissao to(PerfilPermissao source) {
            BuscarPermissoesPorPerfilOutput.Permissao target = super.to(source);

            if (Objects.nonNull(source.getPerfil())) {
                target.setPerfil(Perfil.builder()
                                .id(source.getPerfil().getId())
                        .build());
            }

            return target;
        }

    }
}
