package br.com.molens.odontoDelta.application.factory.sessaoUsuario;

import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UserDataFactory {

    @Autowired
    private UsuarioDataProvider usuarioDataProvider;

    @Autowired
    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;

    @Autowired
    private BuscarPermissoesUsuarioUsecase buscarPermissoesUsuarioUsecase;

    @Bean("UserDataUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserDataUsecase criar() {
        return UserDataUsecase.builder()
                .usuarioDataProvider(usuarioDataProvider)
                .sessaoUsuarioDataProvider(sessaoUsuarioDataProvider)
                .buscarPermissoesUsuarioUsecase(buscarPermissoesUsuarioUsecase)
                .build();
    }

}
