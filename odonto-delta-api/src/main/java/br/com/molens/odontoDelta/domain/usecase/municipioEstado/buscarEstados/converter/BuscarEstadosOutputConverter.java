package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.converter;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.BuscarEstadosOutput;
import br.com.molens.odontoDelta.gateway.entity.Estado;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarEstadosOutputConverter {

    public BuscarEstadosOutput to(List<Estado> from) {
        BuscarEstadosOutputItemConverter outputDataItemConverter = new BuscarEstadosOutputItemConverter();

        return BuscarEstadosOutput
                .builder()
                .items(from
                        .stream()
                        .map(outputDataItemConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

    private static class BuscarEstadosOutputItemConverter extends GenericConverter<Estado, BuscarEstadosOutput.Item> {

        @Override
        public BuscarEstadosOutput.Item to(Estado source) {
            BuscarEstadosOutput.Item target = super.to(source);

            return target;
        }

    }
}
