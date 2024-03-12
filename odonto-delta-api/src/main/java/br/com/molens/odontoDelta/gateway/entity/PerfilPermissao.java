package br.com.molens.odontoDelta.gateway.entity;


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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PERFIL_PERMISSAO", schema = "odonto")
@SequenceGenerator(name = "seq_perfil_permissao", sequenceName = "odonto.seq_perfil_permissao", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pp_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pp_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pp_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pp_usuario_alteracao"))
})
public class PerfilPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_perfil_permissao")
    @Column(name = "pp_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_id")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_id")
    private Permissao permissao;

    @Column(name = "pp_ativo")
    private Boolean ativo;

}