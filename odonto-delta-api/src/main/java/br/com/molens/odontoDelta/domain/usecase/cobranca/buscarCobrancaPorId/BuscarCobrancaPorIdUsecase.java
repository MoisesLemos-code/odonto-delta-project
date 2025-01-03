package br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId;

import br.com.molens.odontoDelta.domain.exception.BuscarCobrancaPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.converter.BuscarCobrancaPorIdOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class BuscarCobrancaPorIdUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private BuscarCobrancaPorIdOutputConverter outputConverter;

    public BuscarCobrancaPorIdOutput executar(BuscarCobrancaPorIdInput input){
        validarDadosEntrada(input);
        return outputConverter.to(buscarCobranca(input));
    }

    private void validarDadosEntrada(BuscarCobrancaPorIdInput input) {

        if(Objects.isNull(input.getCobrancaId())){
            throw new BuscarCobrancaPorIdException("Identificador de cobrança inválido.");
        }
        if(Objects.isNull(input.getEmpresaId())){
            throw new BuscarCobrancaPorIdException("Identificador de empresa inválido.");
        }
    }

    private Cobranca buscarCobranca(BuscarCobrancaPorIdInput input) {
        return cobrancaDataProvider.buscarPorId(input.getCobrancaId())
                .orElseThrow(() -> new BuscarCobrancaPorIdException("Cobrança não encontrada."));
    }
}
