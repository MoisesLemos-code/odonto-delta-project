package br.com.molens.odontoDelta.application.factory.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao.RemoverPerfilPermissaoPorIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoverPerfilPermissaoPorIdFactory {

    @Autowired
    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    @Bean("RemoverPerfilPermissaoPorIdUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RemoverPerfilPermissaoPorIdUsecase criar() {
        return RemoverPerfilPermissaoPorIdUsecase.builder()
                .perfilPermissaoDataProvider(perfilPermissaoDataProvider)
                .build();
    }

}
