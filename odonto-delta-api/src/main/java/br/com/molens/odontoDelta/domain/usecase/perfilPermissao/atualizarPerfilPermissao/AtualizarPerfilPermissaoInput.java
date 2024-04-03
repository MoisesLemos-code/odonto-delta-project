package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPerfilPermissaoInput {

    @NotNull(message = "O atributo 'perfilId' não pode ser nulo.")
    private Long perfilId;
    private Long empresaId;

    private List<Permissao> items;

    @Data
    @NoArgsConstructor
    public static class Permissao {
        private Long id;
        private Long permissaoId;
        private Boolean ativo;
    }

}
