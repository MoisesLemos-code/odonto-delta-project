package br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente;

import br.com.molens.odontoDelta.domain.enumeration.SituacaoPacienteEnum;
import br.com.molens.odontoDelta.utils.validate.DateFormat;
import br.com.molens.odontoDelta.utils.validate.enumeration.EnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPacienteOuput {

    private Long pacienteId;
}
