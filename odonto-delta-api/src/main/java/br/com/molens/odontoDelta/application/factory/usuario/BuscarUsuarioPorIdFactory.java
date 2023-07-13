package br.com.molens.odontoDelta.application.factory.usuario;

import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.converter.BuscarUsuarioPorIdOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarUsuarioPorIdFactory {

    @Autowired
    private UsuarioDataProvider usuarioDataProvider;

    @Bean("BuscarUsuarioPorIdUsecase")
    @DependsOn({"BuscarUsuarioPorIdOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarUsuarioPorIdUsecase criar(BuscarUsuarioPorIdOutputConverter outputConverter) {
        return BuscarUsuarioPorIdUsecase.builder()
                .usuarioDataProvider(usuarioDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarUsuarioPorIdOutputConverter")
    public BuscarUsuarioPorIdOutputConverter createOutputConverter() {
        return new BuscarUsuarioPorIdOutputConverter();
    }
}
