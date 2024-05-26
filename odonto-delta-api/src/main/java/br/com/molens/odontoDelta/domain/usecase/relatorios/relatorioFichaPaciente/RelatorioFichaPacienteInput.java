package br.com.molens.odontoDelta.domain.usecase.relatorios.relatorioFichaPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioFichaPacienteInput {

    @NotNull(message = "O atributo 'pacienteId' não pode ser nulo.")
    private Long pacienteId;
    @NotNull(message = "O atributo 'empresaId' não pode ser nulo.")
    private Long empresaId;
}
