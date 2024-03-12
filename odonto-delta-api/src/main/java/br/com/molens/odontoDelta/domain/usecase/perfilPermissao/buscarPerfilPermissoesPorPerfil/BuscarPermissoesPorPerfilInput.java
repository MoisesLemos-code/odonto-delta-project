package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPermissoesPorPerfilInput {

    private Long perfilId;
    @NotNull(message = "O atributo 'empresa' não pode ser nulo.")
    private Long empresaId;
}
