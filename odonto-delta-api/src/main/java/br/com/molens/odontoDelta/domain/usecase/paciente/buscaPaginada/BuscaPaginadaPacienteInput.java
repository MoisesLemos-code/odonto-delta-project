package br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaPacienteInput {

    private int page;
    private int size;
    private String sort;
    private String direction;
    private String conteudo;
    @NotNull(message = "O atributo 'empresaId' não pode ser nulo.")
    private Long empresaId;
}
