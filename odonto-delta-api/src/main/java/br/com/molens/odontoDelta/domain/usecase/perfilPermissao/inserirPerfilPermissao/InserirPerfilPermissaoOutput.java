package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPerfilPermissaoOutput {

    private Long permissaoId;
}
