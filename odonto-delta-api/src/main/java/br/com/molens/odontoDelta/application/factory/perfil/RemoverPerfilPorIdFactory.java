package br.com.molens.odontoDelta.application.factory.perfil;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId.RemoverPerfilPorIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoverPerfilPorIdFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("RemoverPerfilPorIdUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RemoverPerfilPorIdUsecase criar() {
        return RemoverPerfilPorIdUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .build();
    }

}
