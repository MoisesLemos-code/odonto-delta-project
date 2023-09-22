package br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoverPerfilPorIdInput {

    @NotNull(message = "O atributo 'perfilId' não pode ser nulo.")
    private Long perfilId;
    @NotNull(message = "O atributo 'empresaId' não pode ser nulo.")
    private Long empresaId;
}
