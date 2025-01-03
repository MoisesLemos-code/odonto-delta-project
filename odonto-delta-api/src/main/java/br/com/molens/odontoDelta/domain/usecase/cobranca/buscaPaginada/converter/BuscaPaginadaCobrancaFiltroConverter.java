package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaInput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@AllArgsConstructor
public class BuscaPaginadaCobrancaFiltroConverter extends GenericConverter<BuscaPaginadaCobrancaInput, Cobranca.Filtro> {

    @Override
    public Cobranca.Filtro to(BuscaPaginadaCobrancaInput source) {
        Cobranca.Filtro target = super.to(source);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(Objects.nonNull(source.getDataVencimento())){
                target.setDataVencimento(sdf.parse(source.getDataVencimento()));
            }

            if(Objects.nonNull(source.getStatusEnum()) && source.getStatusEnum().equals("STATUS")){
                target.setStatusEnum(null);
            }

        } catch (ParseException e) {
            throw new DataEmFormatoIncorretoException();
        }

        return target;
    }
}