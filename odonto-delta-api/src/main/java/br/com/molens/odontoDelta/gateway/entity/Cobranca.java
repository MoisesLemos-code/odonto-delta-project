package br.com.molens.odontoDelta.gateway.entity;


import br.com.molens.odontoDelta.domain.entity.BaseObject;
import br.com.molens.odontoDelta.domain.entity.FiltroBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COBRANCA", schema = "odonto")
@SequenceGenerator(name = "seq_empresa", sequenceName = "odonto.seq_empresa", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "co_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "co_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "co_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "co_usuario_alteracao"))
})
public class Cobranca extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
    @Column(name = "co_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pa_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "or_id")
    private Orcamento orcamento;

    @Column(name = "co_codigo")
    private String codigo;

    @Column(name = "co_valor_total")
    private BigDecimal valorTotal;

    @Column(name = "co_valor_pago")
    private BigDecimal valorPago;

    @Column(name = "co_status")
    private String status;

    @Column(name = "co_descricao")
    private String descricao;

    @Column(name = "co_observacao")
    private String observacao;

    @Column(name = "co_data_vencimento")
    private Date dataVencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Empresa empresa;

    public enum EnumStatusCobranca {
        PENDENTE("Pagamento pendente"),
        PARCIALMENTE_PAGO("Parcialmente paga"),
        PAGO("Pago"),
        CANCELADA("Cancelada");

        private String valor;

        EnumStatusCobranca(String valor) {
            this.valor = valor;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filtro extends FiltroBase {
        private Long empresaId;
        private String conteudo;
        private String atributo;
        private String tipo;
        private Date dataVencimento;
        private Long pacienteId;
    }

}