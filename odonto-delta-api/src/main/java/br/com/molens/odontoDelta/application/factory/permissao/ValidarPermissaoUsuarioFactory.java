package br.com.molens.odontoDelta.application.factory.permissao;

import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.permissao.validarPermissoesUsuario.ValidarPermissaoUsuarioUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ValidarPermissaoUsuarioFactory {

    @Autowired
    private BuscarPermissoesUsuarioUsecase buscarPermissoesUsuarioUsecase;

    @Bean("ValidarPermissaoUsuarioUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ValidarPermissaoUsuarioUsecase criar() {
        return ValidarPermissaoUsuarioUsecase.builder()
                .buscarPermissoesUsuarioUsecase(buscarPermissoesUsuarioUsecase)
                .build();
    }
}
