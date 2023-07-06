package br.com.molens.odontoDelta.application.factory.usuario;

import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.conveter.InserirUsuarioOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InserirUsuarioFactory {

    @Autowired
    private UsuarioDataProvider usuarioDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Autowired
    private MunicipioDataProvider municipioDataProvider;

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean("InserirUsuarioUsecase")
    @DependsOn({"InserirUsuarioOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirUsuarioUsecase criar(InserirUsuarioOutputConverter outputConverter) {
        return InserirUsuarioUsecase.builder()
                .usuarioDataProvider(usuarioDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .municipioDataProvider(municipioDataProvider)
                .perfilDataProvider(perfilDataProvider)
                .passwordEncoder(passwordEncoder)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("InserirUsuarioOutputConverter")
    public InserirUsuarioOutputConverter createOutputConverter() {
        return new InserirUsuarioOutputConverter();
    }

}
