package br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPacienteOutput {

    private Long pacienteId;
}
