package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPermissoesPorPerfilOutput {

    private List<Permissao> items;

    @Data
    @NoArgsConstructor
    public static class Permissao {
        private Long id;
        private Long perfilId;
        private Long permissaoId;
        private String nome;
        private String descricao;
        private Boolean ativo;
    }
}
