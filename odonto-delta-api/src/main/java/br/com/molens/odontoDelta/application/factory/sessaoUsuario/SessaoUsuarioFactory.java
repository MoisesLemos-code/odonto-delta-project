package br.com.molens.odontoDelta.application.factory.sessaoUsuario;

import br.com.molens.odontoDelta.application.security.UserSS;
import br.com.molens.odontoDelta.domain.exception.SessaoUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.repository.sessaoUsuario.SessaoUsuarioDataProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Configuration
public class SessaoUsuarioFactory {

    @Autowired
    private HttpServletRequest request;

    @Bean("SessaoUsuarioDataProvider")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessaoUsuarioDataProvider createSessaoUsuarioDataProviderImpl() {
        if (Objects.nonNull(request.getUserPrincipal())) {
            UserSS usuario = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new SessaoUsuarioDataProviderImpl(
                    usuario.getId(),
                    usuario.getLogin(),
                    usuario.getNomeCompleto()
            );
        } else {
            throw new SessaoUsuarioException("Falha ao obter informações do usuário!");
        }

    }
}
