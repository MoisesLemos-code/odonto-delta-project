package br.com.molens.odontoDelta.domain.usecase.perfilUsuario.atualizarPerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPerfilUsuarioInput {

    private Long usuarioId;
    private Long empresaId;

    private List<Perfil> items;

    @Data
    @NoArgsConstructor
    public static class Perfil {
        private Long idPerfil;
        private Boolean ativo;
    }
}
