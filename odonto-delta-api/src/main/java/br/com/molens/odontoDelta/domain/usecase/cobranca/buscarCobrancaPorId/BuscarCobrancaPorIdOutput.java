package br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarCobrancaPorIdOutput {

    private Long id;
    private Long pacienteId;
    private Long orcamentoId;
    private Long empresaId;
    private String codigo;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    private String status;
    private String descricao;
    private String observacao;
    private String dataVencimento;
    private String codigoOrcamento;
    private String statusOrcamento;
}
