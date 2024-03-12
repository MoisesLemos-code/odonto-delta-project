package br.com.molens.odontoDelta.application.factory.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class InserirPerfilPermissaoFactory {

    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    @Autowired
    private PerfilDataProvider perfilDataProvider;
    @Autowired
    private PermissaoDataProvider permissaoDataProvider;

    @Bean("InserirPerfilPermissaoUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirPerfilPermissaoUsecase criar() {
        return InserirPerfilPermissaoUsecase.builder()
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .perfilDataProvider(perfilDataProvider)
                .permissaoDataProvider(permissaoDataProvider)
                .build();
    }

}
