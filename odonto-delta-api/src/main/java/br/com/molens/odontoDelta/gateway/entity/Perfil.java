package br.com.molens.odontoDelta.gateway.entity;


import br.com.molens.odontoDelta.domain.entity.BaseObject;
import br.com.molens.odontoDelta.domain.entity.FiltroBase;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PERFIL", schema = "odonto")
@SequenceGenerator(name = "seq_perfil", sequenceName = "odonto.seq_perfil", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pf_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pf_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pf_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pf_usuario_alteracao"))
})
public class Perfil extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_perfil")
    @Column(name = "pf_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pf_nome")
    private String nome;

    @Column(name = "pf_descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Empresa empresa;

    @Column(name = "pf_ativo")
    private Boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<PerfilPermissao> perfilPermissaoList;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<PerfilUsuario> perfilUsuarioList;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filtro extends FiltroBase {
        String conteudo;
        private Long empresaId;
    }
}