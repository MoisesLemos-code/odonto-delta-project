package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.converter;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoOutput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import java.util.List;
import java.util.stream.Collectors;

public class BuscaDeCidadesPorEstadoOutputConverter {

    public BuscaDeCidadesPorEstadoOutput to(List<Municipio> from) {
        BuscaDeCidadesOutputItemConverter outputDataItemConverter = new BuscaDeCidadesOutputItemConverter();

        return BuscaDeCidadesPorEstadoOutput
                .builder()
                .items(from
                        .stream()
                        .map(outputDataItemConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

    private static class BuscaDeCidadesOutputItemConverter extends GenericConverter<Municipio, BuscaDeCidadesPorEstadoOutput.Item> {

        @Override
        public BuscaDeCidadesPorEstadoOutput.Item to(Municipio source) {
            BuscaDeCidadesPorEstadoOutput.Item target = super.to(source);

            return target;
        }

    }
}
