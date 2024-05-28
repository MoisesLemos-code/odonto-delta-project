package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaCobrancaOutput {

    private List<Item> items;
    private Long totalPages;
    private Long totalElements;

    @Data
    @NoArgsConstructor
    public static class Item {
        private Long id;
        private Long orcamentoId;
        private String codigo;
        private BigDecimal valorTotal;
        private BigDecimal valorPago;
        private String status;
        private String descricao;
        private String observacao;
        private String dataVencimento;
        private Boolean isVencido;
    }
}
