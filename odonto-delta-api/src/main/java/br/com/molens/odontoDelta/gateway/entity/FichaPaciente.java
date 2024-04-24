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
@Table(name = "TB_FICHA", schema = "odonto")
@SequenceGenerator(name = "seq_ficha", sequenceName = "odonto.seq_ficha", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "fi_dthr_cadastro")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "fi_dthr_alteracao")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "fi_usuario_cadastro")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "fi_usuario_alteracao"))
})
public class FichaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ficha")
    @Column(name = "fi_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pa_id")
    private Paciente paciente;

    @Column(name = "fi_descricao")
    private String descricao;

    @Column(name = "fi_tratamento")
    private Boolean tratamento;

    @Column(name = "fi_outros_medicamentos")
    private String outrosMedicamentos;

    @Column(name = "fi_anestesia_local")
    private Boolean anestesiaLocal;

    @Column(name = "fi_reacao_anestesia_local")
    private Boolean possuiReacaoAnestesiaLocal;

    @Column(name = "fi_uso_antibiotico")
    private Boolean usoAntibiotico;

    @Column(name = "fi_reacao_antibiotico")
    private Boolean possuiReacaoAntibiotico;

    @Column(name = "fi_nome_antibiotico")
    private String nomeAntibiotico;

    @Column(name = "fi_nome_medicamento_alergia")
    private String nomeMedicamentoAlergia;

    @Column(name = "fi_cicatrizacao_normal")
    private Boolean cicatrizacaoNormal;

    @Column(name = "fi_tique_mania")
    private String tiqueMania;

    @Column(name = "fi_esta_gravida")
    private Boolean estaGravida;

    @Column(name = "fi_fumante")
    private Boolean fumante;

    @Column(name = "fi_outras_informacoes_importantes")
    private String outrasInformacoesImportantes;

    @Column(name = "fi_cardiopatia")
    private Boolean cardiopatia;

    @Column(name = "fi_anemia")
    private Boolean anemia;

    @Column(name = "fi_ulcera")
    private Boolean ulcera;

    @Column(name = "fi_doenca_pulmonar")
    private Boolean doencaPulmonar;

    @Column(name = "fi_diabetes")
    private Boolean diabetes;

    @Column(name = "fi_artrite_reumatismo")
    private Boolean artriteReumatismo;

    @Column(name = "fi_doenca_infecciosa")
    private Boolean doencaInfecciosa;

    @Column(name = "fi_alergia")
    private Boolean alergia;

    @Column(name = "fi_cancer")
    private Boolean cancer;

    @Column(name = "fi_sinusite")
    private Boolean sinusite;

    @Column(name = "fi_gastrite")
    private Boolean gastrite;

    @Column(name = "fi_hepatite")
    private Boolean hepatite;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filtro extends FiltroBase {
        private Long empresaId;
        private Long pacienteId;
    }

}