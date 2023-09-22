package br.com.molens.odontoDelta.application.factory.perfil;

import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.converter.InserirPerfilOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class InserirPerfilFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Bean("InserirPerfilUsecase")
    @DependsOn({"InserirPerfilOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirPerfilUsecase criar(InserirPerfilOutputConverter outputConverter) {
        return InserirPerfilUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("InserirPerfilOutputConverter")
    public InserirPerfilOutputConverter createOutputConverter() {
        return new InserirPerfilOutputConverter();
    }

}
