package br.com.molens.odontoDelta.application.factory.municipio;

import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoUsecase;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.converter.BuscaDeCidadesPorEstadoOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscaDeCidadesPorEstadoFactory {

    @Autowired
    private MunicipioDataProvider municipioDataProvider;

    @Bean("BuscaDeCidadesUsecase")
    @DependsOn({"BuscaDeCidadesPorEstadoOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscaDeCidadesPorEstadoUsecase criar(BuscaDeCidadesPorEstadoOutputConverter outputConverter) {
        return BuscaDeCidadesPorEstadoUsecase.builder()
                .municipioDataProvider(municipioDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscaDeCidadesPorEstadoOutputConverter")
    public BuscaDeCidadesPorEstadoOutputConverter createOutputConverter() {
        return new BuscaDeCidadesPorEstadoOutputConverter();
    }

}
