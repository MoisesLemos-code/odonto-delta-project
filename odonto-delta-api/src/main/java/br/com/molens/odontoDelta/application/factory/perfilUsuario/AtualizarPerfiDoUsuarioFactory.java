package br.com.molens.odontoDelta.application.factory.perfilUsuario;

import br.com.molens.odontoDelta.domain.interfaces.PerfilUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.atualizarPerfilUsuario.AtualizarPerfilUsuarioUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarPerfiDoUsuarioFactory {

    @Autowired
    private PerfilUsuarioDataProvider perfilUsuarioDataProvider;

    @Bean("AtualizarPerfilUsuarioUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarPerfilUsuarioUsecase criar() {
        return AtualizarPerfilUsuarioUsecase.builder()
                .perfilUsuarioDataProvider(perfilUsuarioDataProvider)
                .build();
    }

}
