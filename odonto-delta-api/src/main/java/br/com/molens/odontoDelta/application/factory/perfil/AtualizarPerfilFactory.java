package br.com.molens.odontoDelta.application.factory.perfil;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.converter.AtualizarPerfilOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarPerfilFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("AtualizarPerfilUsecase")
    @DependsOn({"AtualizarPerfilOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarPerfilUsecase criar(AtualizarPerfilOutputConverter outputConverter) {
        return AtualizarPerfilUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("AtualizarPerfilOutputConverter")
    public AtualizarPerfilOutputConverter createOutputConverter() {
        return new AtualizarPerfilOutputConverter();
    }


}
