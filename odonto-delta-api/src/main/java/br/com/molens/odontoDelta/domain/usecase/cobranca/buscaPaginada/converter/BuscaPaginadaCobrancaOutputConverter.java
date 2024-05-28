package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaOutput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuscaPaginadaCobrancaOutputConverter {

    public BuscaPaginadaCobrancaOutput to(ListaPaginada<Cobranca> from) {
        BuscarCobrancasOutputItemConverter outputDataItemConverter = new BuscarCobrancasOutputItemConverter();

        return BuscaPaginadaCobrancaOutput
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

    private static class BuscarCobrancasOutputItemConverter extends GenericConverter<Cobranca, BuscaPaginadaCobrancaOutput.Item> {

        @Override
        public BuscaPaginadaCobrancaOutput.Item to(Cobranca source) {
            BuscaPaginadaCobrancaOutput.Item target = super.to(source);

            if (Objects.nonNull(source.getOrcamento())) {
                target.setOrcamentoId(source.getOrcamento().getId());
            }

            if(Objects.nonNull(source.getDataVencimento())){
                target.setDataVencimento(source.getDataVencimento().toString());

                Date data = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(data);
                calendar.add(Calendar.DATE, -1);
                data = calendar.getTime();

                target.setIsVencido(source.getDataVencimento().before(data));
            }

            return target;
        }
    }
}
