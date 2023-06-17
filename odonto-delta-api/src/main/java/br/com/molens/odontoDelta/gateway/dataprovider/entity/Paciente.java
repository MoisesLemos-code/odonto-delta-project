package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import br.com.molens.odontoDelta.domain.entity.FiltroBase;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PACIENTE", schema = "odonto")
@SequenceGenerator(name = "seq_paciente", sequenceName = "odonto.seq_paciente", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "pa_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pa_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "pa_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "pa_usuario_alteracao"))
})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_paciente")
    @Column(name = "pa_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pa_nome")
    private String nome;

    @Column(name = "pa_email")
    private String email;

    @Column(name = "pa_cnpj_cpf")
    private String cnpjCpf;

    @Column(name = "pa_telefone")
    private String telefone;

    @Column(name = "pa_rg")
    private String rg;

    @Column(name = "pa_data_nascimento")
    private Date dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mu_id")
    private Municipio municipio;

    @Column(name = "pa_cep")
    private String cep;

    @Column(name = "pa_bairro")
    private String bairro;

    @Column(name = "pa_logra")
    private String logradouro;

    @Column(name = "pa_logra_num")
    private String logradouroNumero;

    @Column(name = "pa_complemento")
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Empresa empresa;

    @Column(name = "pa_situacao")
    private String situacao;

    @Column(name = "pa_dthr_ultima_visita")
    private LocalDateTime ultimaVisita;

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