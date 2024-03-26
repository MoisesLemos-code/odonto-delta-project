package br.com.molens.odontoDelta.application.factory.usuario;

import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado.BuscarUsuariosPaginadosUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarUsuariosPaginadosFactory {

    @Autowired
    private UsuarioDataProvider usuarioDataProvider;

    @Bean("BuscarUsuariosPaginadosUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarUsuariosPaginadosUsecase criar() {
        return BuscarUsuariosPaginadosUsecase.builder()
                .usuarioDataProvider(usuarioDataProvider)
                .build();
    }

}
