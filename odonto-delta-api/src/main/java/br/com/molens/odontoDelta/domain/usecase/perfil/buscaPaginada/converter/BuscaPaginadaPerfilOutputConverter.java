package br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuscaPaginadaPerfilOutputConverter {

    public BuscaPaginadaPerfilOutput to(ListaPaginada<Perfil> from) {
        BuscarPerfilOutputItemConverter outputDataItemConverter = new BuscarPerfilOutputItemConverter();

        return BuscaPaginadaPerfilOutput
                .builder()
                .totalElements(from.getTotalElements())
                .totalPages(from.getTotalPages())
                .items(from
                        .getItems()
                        .stream()
                        .map(outputDataItemConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

    private static class BuscarPerfilOutputItemConverter extends GenericConverter<Perfil, BuscaPaginadaPerfilOutput.Item> {

        @Override
        public BuscaPaginadaPerfilOutput.Item to(Perfil source) {
            BuscaPaginadaPerfilOutput.Item target = super.to(source);

            if (Objects.nonNull(source.getEmpresa())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresa().getId())
                        .build());
            }

            return target;
        }

    }
}
