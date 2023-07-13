package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUsuarioPorIdInput {

    @NotNull(message = "O atributo 'usuarioId' não pode ser nulo.")
    private Long usuarioId;
}
