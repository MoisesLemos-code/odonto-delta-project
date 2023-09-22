package br.com.molens.odontoDelta.application.factory.perfil;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter.BuscaPaginadaPerfilFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter.BuscaPaginadaPerfilOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscaPaginadaPerfilFactory {

    @Autowired
    private PerfilDataProvider perfilDataProvider;

    @Bean("BuscaPaginadaPerfilUsecase")
    @DependsOn({"BuscaPaginadaPacienteFiltroConverter", "BuscaPaginadaPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscaPaginadaPerfilUsecase criar(BuscaPaginadaPerfilFiltroConverter filtroConverter, BuscaPaginadaPerfilOutputConverter outputConverter) {
        return BuscaPaginadaPerfilUsecase.builder()
                .perfilDataProvider(perfilDataProvider)
                .filtroConverter(filtroConverter)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscaPaginadaPerfilFiltroConverter")
    public BuscaPaginadaPerfilFiltroConverter createFiltroConverter() {
        return new BuscaPaginadaPerfilFiltroConverter();
    }

    @Bean("BuscaPaginadaPerfilOutputConverter")
    public BuscaPaginadaPerfilOutputConverter createOutputDataConverter() {
        return new BuscaPaginadaPerfilOutputConverter();
    }

}
