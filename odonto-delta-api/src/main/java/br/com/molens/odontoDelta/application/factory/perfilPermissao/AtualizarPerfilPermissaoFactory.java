package br.com.molens.odontoDelta.application.factory.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaolUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarPerfilPermissaoFactory {

    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    @Bean("AtualizarPerfilPermissaolUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarPerfilPermissaolUsecase criar() {
        return AtualizarPerfilPermissaolUsecase.builder()
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .build();
    }

}
