package br.com.molens.odontoDelta.application.factory.usuario;

import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.converter.AtualizarUsuarioOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarUsuarioFactory {

    @Autowired
    private UsuarioDataProvider usuarioDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Bean("AtualizarUsuarioUsecase")
    @DependsOn({"AtualizarUsuarioOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarUsuarioUsecase criar(AtualizarUsuarioOutputConverter outputConverter) {
        return AtualizarUsuarioUsecase.builder()
                .usuarioDataProvider(usuarioDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("AtualizarUsuarioOutputConverter")
    public AtualizarUsuarioOutputConverter createOutputConverter() {
        return new AtualizarUsuarioOutputConverter();
    }
}
