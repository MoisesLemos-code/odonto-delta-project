package br.com.molens.odontoDelta.application.factory.permissao;

import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPermissoesFactory {

    @Autowired
    private PermissaoDataProvider permissaoDataProvider;

    @Bean("BuscarPermissoesUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPermissoesUsuarioUsecase criar() {
        return BuscarPermissoesUsuarioUsecase.builder()
                .permissaoDataProvider(permissaoDataProvider)
                .build();
    }
}
