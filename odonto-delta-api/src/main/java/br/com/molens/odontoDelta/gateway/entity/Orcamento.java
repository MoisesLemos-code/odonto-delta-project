package br.com.molens.odontoDelta.gateway.entity;


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

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ORCAMENTO", schema = "odonto")
@SequenceGenerator(name = "seq_empresa", sequenceName = "odonto.seq_empresa", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "or_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "or_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "or_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "or_usuario_alteracao"))
})
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
    @Column(name = "or_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pa_id")
    private Paciente paciente;

    @Column(name = "or_codigo")
    private String codigo;

    @Column(name = "or_status")
    private EnumStatusOrcamento status;

    @Column(name = "or_observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Empresa empresa;

    public enum EnumStatusOrcamento {
        ABERTO("Aberto"),
        FINALIZADO("Finalizado"),
        CANCELADO("Cancelado");

        private String valor;

        EnumStatusOrcamento(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
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
        private Long pacienteId;
    }
}