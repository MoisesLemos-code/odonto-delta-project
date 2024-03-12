package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPerfilPermissaoInput {

    @NotNull(message = "O atributo 'id' não pode ser nulo.")
    private Long id;
    @NotNull(message = "O atributo 'empresa' não pode ser nulo.")
    private Long empresaId;
    private Boolean ativo;

}
