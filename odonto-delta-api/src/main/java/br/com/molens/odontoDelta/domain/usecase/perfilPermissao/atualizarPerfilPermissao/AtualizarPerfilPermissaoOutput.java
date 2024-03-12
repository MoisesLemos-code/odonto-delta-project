package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
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
        private Permissao permissao;
        private Boolean ativo;
}
