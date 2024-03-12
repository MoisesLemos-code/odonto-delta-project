package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPerfilPermissaoInput {

    @NotNull(message = "O atributo 'perfilId' não pode ser nulo.")
    private Long perfilId;
    @NotNull(message = "O atributo 'permissaoId' não pode ser nulo.")
    private Long permissaoId;
    private Long empresaId;
}
