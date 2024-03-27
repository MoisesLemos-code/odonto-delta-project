package br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPerfisDoUsuarioOutput {

    private List<Perfil> items;

    @Data
    @NoArgsConstructor
    public static class Perfil {
        private Long idPerfil;
        private String nome;
        private String descricao;
        private Boolean ativo;
        private List<Permissao> permissoes;
    }

    @Data
    @NoArgsConstructor
    public static class Permissao {
        private Long idPermissao;
        private String nome;
        private String descricao;
        private Boolean ativo;
    }
}
