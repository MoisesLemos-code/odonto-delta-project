package br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca;

import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.utils.validate.DateFormat;
import br.com.molens.odontoDelta.utils.validate.enumeration.EnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirCobrancaInput {

    @NotNull(message = "O atributo 'pacienteId' não pode ser nulo.")
    private Long pacienteId;
    private Long orcamentoId;
    private Long empresaId;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    @EnumType(enumClass = Cobranca.EnumStatusCobranca.class, message = "O atributo 'status' é inválido.")
    private String status;
    private String descricao;
    private String observacao;
    @DateFormat(message="O parametro 'dataVencimento' é inválido, o formato esperado yyyy-MM-dd")
    private String dataVencimento;
}
