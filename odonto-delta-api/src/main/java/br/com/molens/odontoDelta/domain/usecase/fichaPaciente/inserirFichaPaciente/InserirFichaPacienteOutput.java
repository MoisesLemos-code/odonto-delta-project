package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirFichaPacienteOutput {

    private Long fichaPacienteId;
}
