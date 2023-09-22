package br.com.molens.odontoDelta.gateway.repository.sessaousuario;

import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SessaoUsuarioDataProviderImpl implements SessaoUsuarioDataProvider {

    private Long id;
    private String login;
    private String nomeCompleto;

    @Override
    public SessaoUsuario get() {
        SessaoUsuario sessaoUsuario = SessaoUsuario
                .builder()
                .id(this.id)
                .login(this.login)
                .nomeCompleto(this.nomeCompleto)
                .build();

        return sessaoUsuario;
    }
}
