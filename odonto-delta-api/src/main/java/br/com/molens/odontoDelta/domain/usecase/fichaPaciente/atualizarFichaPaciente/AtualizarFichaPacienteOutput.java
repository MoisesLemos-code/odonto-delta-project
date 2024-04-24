package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarFichaPacienteOutput {

    private Long fichaPacienteId;
}
