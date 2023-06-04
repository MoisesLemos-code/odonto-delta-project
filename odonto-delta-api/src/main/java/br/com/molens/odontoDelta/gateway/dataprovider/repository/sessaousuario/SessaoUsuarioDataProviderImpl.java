package br.com.molens.odontoDelta.gateway.dataprovider.repository.sessaousuario;

import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class SessaoUsuarioDataProviderImpl implements SessaoUsuarioDataProvider {

    private String login;
    private Long empresaId;

    @Override
    public SessaoUsuario get() {
        SessaoUsuario sessaoUsuario = SessaoUsuario
                .builder()
                .login(this.login)
                .empresaId(this.empresaId)
                .build();

        return sessaoUsuario;
    }
}
