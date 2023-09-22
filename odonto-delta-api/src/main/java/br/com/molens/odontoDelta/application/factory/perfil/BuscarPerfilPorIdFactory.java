package br.com.molens.odontoDelta.application.factory.perfil;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.converter.BuscarPerfilPorIdOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPerfilPorIdFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("BuscarPerfilPorIdUsecase")
    @DependsOn({"BuscarPerfilPorIdOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPerfilPorIdUsecase criar(BuscarPerfilPorIdOutputConverter outputConverter) {
        return BuscarPerfilPorIdUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarPerfilPorIdOutputConverter")
    public BuscarPerfilPorIdOutputConverter createOutputConverter() {
        return new BuscarPerfilPorIdOutputConverter();
    }


}
