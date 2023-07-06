package br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirUsuarioOutput {

    private Long usuarioId;
}
