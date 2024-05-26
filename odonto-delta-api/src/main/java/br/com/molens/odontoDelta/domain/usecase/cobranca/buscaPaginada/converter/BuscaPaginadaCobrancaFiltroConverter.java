package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaInput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaPaginadaCobrancaFiltroConverter extends GenericConverter<BuscaPaginadaCobrancaInput, Cobranca.Filtro> {

}