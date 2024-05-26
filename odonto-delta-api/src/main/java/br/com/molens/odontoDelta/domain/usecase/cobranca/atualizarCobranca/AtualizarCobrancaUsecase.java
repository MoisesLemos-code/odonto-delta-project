package br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca;

import br.com.molens.odontoDelta.domain.exception.AtualizarCobrancaException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.converter.AtualizarCobrancaOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarCobrancaUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private PacienteDataProvider pacienteDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private AtualizarCobrancaOutputConverter outputConverter;

    public Cobranca executar(AtualizarCobrancaInput input) {
        validarDadosEntrada(input);
        validarCobranca(input);
        Cobranca cobranca = setarDados(input);

        return atualizarCobranca(cobranca);
    }

    private void validarDadosEntrada(AtualizarCobrancaInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarCobrancaException("Identificador de cobrança inválido.");
        }

        if (Objects.isNull(input.getEmpresaId())) {
            throw new AtualizarCobrancaException("Identificador de empresa inválido.");
        }

        if (Objects.isNull(input.getPacienteId())) {
            throw new AtualizarCobrancaException("Identificador de paciente inválido.");
        }
    }

    private void validarCobranca(AtualizarCobrancaInput input) {
        Optional<Cobranca> cobranca = cobrancaDataProvider.buscarPorId(input.getId());

        if (!cobranca.isPresent()) {
            throw new AtualizarCobrancaException("Cobrança não encontrada.");
        }
    }

    private Cobranca setarDados(AtualizarCobrancaInput input) {

        return outputConverter.from(input);
    }

    private Cobranca atualizarCobranca(Cobranca cobranca) {
        Cobranca cobrancaSalva = cobrancaDataProvider.atualizar(cobranca);

        return cobrancaSalva;
    }
}
