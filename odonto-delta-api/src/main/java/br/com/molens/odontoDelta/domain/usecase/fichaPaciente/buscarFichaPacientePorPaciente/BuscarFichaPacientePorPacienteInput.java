package br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarFichaPacientePorPacienteInput {

    @NotNull(message = "O atributo 'pacienteId' não pode ser nulo.")
    private Long pacienteId;
    private Long empresaId;

}
