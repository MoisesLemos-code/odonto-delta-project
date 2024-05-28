package br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca;

import br.com.molens.odontoDelta.domain.exception.AtualizarCobrancaException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.converter.AtualizarCobrancaOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarCobrancaUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private PacienteDataProvider pacienteDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private AtualizarCobrancaOutputConverter outputConverter;

    public AtualizarCobrancaOutput executar(AtualizarCobrancaInput input) {
        validarDadosEntrada(input);
        validarCobranca(input);
        Cobranca cobranca = setarDados(input);
        atualizarCobranca(cobranca);
        return AtualizarCobrancaOutput.builder().cobrancaId(cobranca.getId()).build();
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

        if(Objects.nonNull(input.getValorPago())){

            if(input.getValorPago().compareTo(new BigDecimal(0)) == -1){
                throw new AtualizarCobrancaException("O valor pago não pode ser negativo!");
            }
            if(input.getValorPago().compareTo(input.getValorTotal()) == 1){
                throw new AtualizarCobrancaException("O valor pago é maior que o valor cobrado!");
            }
            if(input.getValorPago().compareTo(new BigDecimal(0)) == 1){
                input.setStatus(Cobranca.EnumStatusCobranca.PARCIALMENTE_PAGO.name());
            }
            if(input.getValorPago().compareTo(new BigDecimal(0)) == 0){
                input.setStatus(Cobranca.EnumStatusCobranca.PENDENTE.name());
            }
            if(input.getValorPago().compareTo(input.getValorTotal()) == 0){
                input.setStatus(Cobranca.EnumStatusCobranca.PAGO.name());
            }
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
       return cobrancaDataProvider.atualizar(cobranca);
    }
}
