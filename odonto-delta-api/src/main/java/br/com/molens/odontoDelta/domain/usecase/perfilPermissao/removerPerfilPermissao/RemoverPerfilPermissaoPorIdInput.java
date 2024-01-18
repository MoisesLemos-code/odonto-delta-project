package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoverPerfilPermissaoPorIdInput {

    @NotNull(message = "O atributo 'perfilId' não pode ser nulo.")
    private Long permissaoId;
}
