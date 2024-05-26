package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarFichaPacienteInput {

    private Long id;
    private Long pacienteId;
    private Boolean tratamento;
    private String outrosMedicamentos;
    private Boolean anestesiaLocal;
    private Boolean possuiReacaoAnestesiaLocal;
    private Boolean usoAntibiotico;
    private Boolean possuiReacaoAntibiotico;
    private String nomeAntibiotico;
    private String nomeMedicamentoAlergia;
    private Boolean cicatrizacaoNormal;
    private String tiqueMania;
    private Boolean estaGravida;
    private Boolean fumante;
    private String outrasInformacoesImportantes;
    private Boolean cardiopatia;
    private Boolean anemia;
    private Boolean ulcera;
    private Boolean doencaPulmonar;
    private Boolean diabetes;
    private Boolean artriteReumatismo;
    private Boolean doencaInfecciosa;
    private Boolean alergia;
    private Boolean cancer;
    private Boolean sinusite;
    private Boolean gastrite;
    private Boolean hepatite;
}
