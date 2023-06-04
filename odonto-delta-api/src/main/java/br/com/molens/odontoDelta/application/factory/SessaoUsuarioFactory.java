package br.com.molens.odontoDelta.application.factory;

import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.repository.sessaousuario.SessaoUsuarioDataProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Configuration
public class SessaoUsuarioFactory {

    @Autowired
    private HttpServletRequest request;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessaoUsuarioDataProvider createSessaoUsuarioDataProviderImpl() {
        String username = "";
        Long empresaID = 0L;
        if (Objects.nonNull(request.getUserPrincipal())) {
            username = "teste";
        }

        return new SessaoUsuarioDataProviderImpl(
                username,
                empresaID
        );
    }
}
