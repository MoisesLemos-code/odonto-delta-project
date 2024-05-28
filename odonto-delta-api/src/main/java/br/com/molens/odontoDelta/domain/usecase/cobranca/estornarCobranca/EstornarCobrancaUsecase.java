package br.com.molens.odontoDelta.domain.usecase.cobranca.estornarCobranca;

import br.com.molens.odontoDelta.domain.exception.EstornarCobrancaException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class EstornarCobrancaUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private EmpresaDataProvider empresaDataProvider;

    public EstornarCobrancaOutput executar(EstornarCobrancaInput input) {
        validarDadosEntrada(input);
        Cobranca cobranca = buscarCobranca(input);
        estornarCobranca(cobranca);

        return EstornarCobrancaOutput.builder().cobrancaId(cobranca.getId()).build();
    }

    private void validarDadosEntrada(EstornarCobrancaInput input) {
        if (Objects.isNull(input.getId())) {
            throw new EstornarCobrancaException("Identificador de cobrança inválido.");
        }

        if (Objects.isNull(input.getEmpresaId())) {
            throw new EstornarCobrancaException("Identificador de empresa inválido.");
        }
    }

    private Cobranca buscarCobranca(EstornarCobrancaInput input) {
        Optional<Cobranca> cobranca = cobrancaDataProvider.buscarPorId(input.getId());

        if (!cobranca.isPresent()) {
            throw new EstornarCobrancaException("Cobrança não encontrada.");
        }

        return cobranca.get();
    }

    private void estornarCobranca(Cobranca cobranca) {

        cobranca.setValorPago(new BigDecimal(0));
        cobranca.setStatus(Cobranca.EnumStatusCobranca.PENDENTE.name());

        cobrancaDataProvider.atualizar(cobranca);
    }

}
