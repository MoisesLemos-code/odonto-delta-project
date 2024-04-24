package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirFichaPacienteInput {

    @NotNull(message = "O atributo 'pacienteId' não pode ser nulo.")
    private Long pacienteId;
    private Long empresaId;
    private String descricao;
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
