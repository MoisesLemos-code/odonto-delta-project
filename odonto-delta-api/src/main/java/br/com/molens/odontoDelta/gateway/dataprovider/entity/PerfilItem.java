package br.com.molens.odontoDelta.gateway.dataprovider.entity;


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
@Table(name = "TB_PERFIL_ITEM", schema = "odonto")
@SequenceGenerator(name = "seq_perfil_item", sequenceName = "odonto.seq_perfil_item", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pfi_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pfi_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pfi_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pfi_usuario_alteracao"))
})
public class PerfilItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_perfil_item")
    @Column(name = "pfi_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_id")
    private Perfil perfil;

    @Column(name = "pfi_nome")
    private String nome;

    @Column(name = "pfi_descricao")
    private String descricao;

    @Column(name = "pfi_ativo")
    private Boolean ativo;

}