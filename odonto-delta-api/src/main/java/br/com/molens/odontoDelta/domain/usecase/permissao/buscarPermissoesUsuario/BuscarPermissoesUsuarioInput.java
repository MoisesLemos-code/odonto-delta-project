package br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPermissoesUsuarioInput {

    private Long empresaId;
    private Long usuarioId;
}
