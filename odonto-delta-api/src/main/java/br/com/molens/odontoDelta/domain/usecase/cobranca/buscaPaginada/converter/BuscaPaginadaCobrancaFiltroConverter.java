package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaInput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@AllArgsConstructor
public class BuscaPaginadaCobrancaFiltroConverter extends GenericConverter<BuscaPaginadaCobrancaInput, Cobranca.Filtro> {

    @Override
    public Cobranca.Filtro to(BuscaPaginadaCobrancaInput source) {
        Cobranca.Filtro target = super.to(source);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            target.setDataVencimento(sdf.parse(source.getDataVencimento()));

        } catch (ParseException e) {
            throw new DataEmFormatoIncorretoException();
        }

        return target;
    }
}