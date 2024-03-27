package br.com.molens.odontoDelta.application.factory.perfilUsuario;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPerfisDoUsuarioFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;
    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    @Autowired
    private PerfilUsuarioDataProvider perfilUsuarioDataProvider;

    @Bean("BuscarPerfisDoUsuarioUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPerfisDoUsuarioUsecase criar() {
        return BuscarPerfisDoUsuarioUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .perfilUsuarioDataProvider(perfilUsuarioDataProvider)
                .build();
    }

}
