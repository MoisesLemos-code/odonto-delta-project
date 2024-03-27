package br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPerfisDoUsuarioInput {

    private Long usuarioId;
    private Long empresaId;
}
