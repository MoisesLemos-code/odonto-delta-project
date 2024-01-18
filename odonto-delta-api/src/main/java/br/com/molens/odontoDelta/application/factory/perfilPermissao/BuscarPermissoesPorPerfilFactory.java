package br.com.molens.odontoDelta.application.factory.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.converter.BuscarPermissoesPorPerfilOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPermissoesPorPerfilFactory {

    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("BuscarPermissoesPorPerfilUsecase")
    @DependsOn({"BuscarPermissoesPorPerfilOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPermissoesPorPerfilUsecase criar() {
        return BuscarPermissoesPorPerfilUsecase.builder()
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .perfilDataProvider(perfilDataProvider)
                .build();
    }

    @Bean("BuscarPermissoesPorPerfilOutputConverter")
    public BuscarPermissoesPorPerfilOutputConverter createOutputConverter() {
        return new BuscarPermissoesPorPerfilOutputConverter();
    }
}
