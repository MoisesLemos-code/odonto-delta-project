package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PLANO", schema = "odonto")
@SequenceGenerator(name = "seq_plano", sequenceName = "odonto.seq_plano", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pla_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pla_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pla_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pla_usuario_alteracao"))
})
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_plano")
    @Column(name = "pla_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pla_ciclo")
    private String ciclo;

    @Column(name = "pla_descricao")
    private String descricao;

    @Column(name = "pla_valor")
    private BigDecimal valor;

    @Column(name = "pla_disponivel")
    private Boolean disponivel;

}