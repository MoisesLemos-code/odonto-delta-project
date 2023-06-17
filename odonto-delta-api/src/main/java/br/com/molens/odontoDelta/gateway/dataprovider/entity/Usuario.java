package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USUARIO", schema = "odonto")
@SequenceGenerator(name = "seq_usuario", sequenceName = "odonto.seq_usuario", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "us_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "us_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "us_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "us_usuario_alteracao"))
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
    @Column(name = "us_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "us_login")
    private String login;

    @Column(name = "us_nome")
    private String nome;

    @Column(name = "us_email")
    private String email;

    @Column(name = "us_cnpj_cpf")
    private String cnpjCpf;

    @Column(name = "us_telefone")
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mu_id")
    private Municipio municipio;

    @Column(name = "us_cep")
    private String cep;

    @Column(name = "us_bairro")
    private String bairro;

    @Column(name = "us_logra")
    private String logradouro;

    @Column(name = "us_logra_num")
    private String logradouroNumero;

    @Column(name = "us_complemento")
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_id")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Empresa empresa;

    @Column(name = "us_situacao")
    private Boolean situacao;

    @Column(name = "us_cargo")
    private String cargo;

    @Column(name = "us_departamento")
    private String departamento;

    @Column(name = "us_senha")
    private String senha;

    @Column(name = "us_dthr_ultimo_acesso")
    private LocalDateTime dataHoraUltimoAcesso;

}