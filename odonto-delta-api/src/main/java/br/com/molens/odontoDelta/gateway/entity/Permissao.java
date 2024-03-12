package br.com.molens.odontoDelta.gateway.entity;


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
@Table(name = "TB_PERMISSAO", schema = "odonto")
@SequenceGenerator(name = "seq_permissao", sequenceName = "odonto.seq_permissao", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pe_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pe_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pe_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pe_usuario_alteracao"))
})
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_permissao")
    @Column(name = "pp_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pe_nome")
    private String nome;

    @Column(name = "pe_descricao")
    private String descricao;
}