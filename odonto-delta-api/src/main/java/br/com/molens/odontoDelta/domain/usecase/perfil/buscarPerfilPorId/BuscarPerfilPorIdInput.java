package br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPerfilPorIdInput {

    @NotNull(message = "O atributo 'perfilId' não pode ser nulo.")
    private Long perfilId;
    @NotNull(message = "O atributo 'empresaId' não pode ser nulo.")
    private Long empresaId;
}
