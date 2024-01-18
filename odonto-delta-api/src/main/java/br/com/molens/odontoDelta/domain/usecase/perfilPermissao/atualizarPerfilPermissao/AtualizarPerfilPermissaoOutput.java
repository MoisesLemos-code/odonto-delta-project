package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.gateway.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPerfilPermissaoOutput {

        private Long id;
        private Perfil perfil;
        private String nome;
        private String descricao;
        private Boolean ativo;
}
