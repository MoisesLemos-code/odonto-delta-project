package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_PERFIL_USUARIO", schema = "odonto")
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pfu_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pfu_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pfu_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pfu_usuario_alteracao"))
})
public class PerfilUsuario {

    @EmbeddedId
    private PerfilUsuarioPK perfilUsuarioPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_id", insertable = false, updatable = false)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", insertable = false, updatable = false)
    private Usuario usuario;

}