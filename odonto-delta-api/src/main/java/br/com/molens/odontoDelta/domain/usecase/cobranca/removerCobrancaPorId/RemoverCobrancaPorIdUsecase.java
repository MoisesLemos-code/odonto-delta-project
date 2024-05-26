package br.com.molens.odontoDelta.domain.usecase.cobranca.removerCobrancaPorId;

import br.com.molens.odontoDelta.domain.exception.RemoverCobrancaPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class RemoverCobrancaPorIdUsecase {

    private CobrancaDataProvider cobrancaDataProvider;

    public void executar(RemoverCobrancaPorIdInput input) {
        validarDadosEntrada(input);
        buscarCobranca(input);
        removerCobranca(input);
    }

    private void validarDadosEntrada(RemoverCobrancaPorIdInput input) {

        if (Objects.isNull(input.getCobrancaId())) {
            throw new RemoverCobrancaPorIdException("Identificador de cobrança inválido.");
        }
    }

    private void buscarCobranca(RemoverCobrancaPorIdInput input) {
        Optional<Cobranca> cobranca = cobrancaDataProvider.buscarPorId(input.getCobrancaId());

        if (!cobranca.isPresent()) {
            throw new RemoverCobrancaPorIdException("Cobrança não encontrada.");
        }
    }

    private void removerCobranca(RemoverCobrancaPorIdInput input) {
        cobrancaDataProvider.remover(input.getCobrancaId());
    }
}
