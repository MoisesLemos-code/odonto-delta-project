package br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.converter;

import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdOutput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class BuscarCobrancaPorIdOutputConverter extends GenericConverter<Cobranca, BuscarCobrancaPorIdOutput> {

    @Override
    public BuscarCobrancaPorIdOutput to(Cobranca source) {
        BuscarCobrancaPorIdOutput target = super.to(source);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (Objects.nonNull(source.getDataVencimento())) {
            target.setDataVencimento(sdf.format(source.getDataVencimento()));
        }

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresaId(source.getEmpresa().getId());
        }

        if (Objects.nonNull(source.getPaciente())) {
            target.setPacienteId(source.getPaciente().getId());
        }

        if (Objects.nonNull(source.getOrcamento())) {
            target.setOrcamentoId(source.getOrcamento().getId());
        }


        return target;
    }
}