package br.com.molens.odontoDelta.application.factory.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPerfilPermissoesPorPerfilUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPermissoesPorPerfilFactory {

    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    @Autowired
    private PermissaoDataProvider permissaoDataProvider;

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("BuscarPerfilPermissoesPorPerfilUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPerfilPermissoesPorPerfilUsecase criar() {
        return BuscarPerfilPermissoesPorPerfilUsecase.builder()
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .permissaoDataProvider(permissaoDataProvider)
                .perfilDataProvider(perfilDataProvider)
                .build();
    }

}
