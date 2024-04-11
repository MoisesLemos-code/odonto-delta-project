package br.com.molens.odontoDelta.domain.usecase.permissao.validarPermissoesUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidarPermissaoUsuarioInput {

    private Long empresaId;
    private Long usuarioId;
    private String permissao;
}
