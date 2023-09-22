package br.com.molens.odontoDelta.gateway.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_EMPRESA", schema = "odonto")
@SequenceGenerator(name = "seq_empresa", sequenceName = "odonto.seq_empresa", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "em_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "em_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "em_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "em_usuario_alteracao"))
})
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
    @Column(name = "em_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "em_nome_fantasia")
    private String nome;

    @Column(name = "em_cnpj_cpf")
    private String cnpjCpf;

    @Column(name = "em_tipo_pessoa")
    private String tipoPessoa;

    @Column(name = "em_email")
    private String email;

    @Column(name = "em_razao_social")
    private String razaoSocial;

    @Column(name = "em_logra")
    private String logradouro;

    @Column(name = "em_logra_num")
    private String logradouroNumero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mu_id")
    private Municipio municipio;

    @Column(name = "em_telefone")
    private String telefone;

    @Column(name = "em_insc_est")
    private String inscricaoEstadual;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pla_id")
    private Plano plano;

}