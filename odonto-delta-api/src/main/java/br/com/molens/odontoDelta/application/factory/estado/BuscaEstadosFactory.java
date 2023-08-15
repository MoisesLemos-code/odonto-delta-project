package br.com.molens.odontoDelta.application.factory.estado;

import br.com.molens.odontoDelta.domain.interfaces.EstadoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.BuscarEstadosUsecase;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.converter.BuscarEstadosOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscaEstadosFactory {

    @Autowired
    private EstadoDataProvider estadoDataProvider;

    @Bean("BuscarEstadosUsecase")
    @DependsOn({"BuscarEstadosOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarEstadosUsecase criar(BuscarEstadosOutputConverter outputConverter) {
        return BuscarEstadosUsecase.builder()
                .estadoDataProvider(estadoDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarEstadosOutputConverter")
    public BuscarEstadosOutputConverter createOutputConverter() {
        return new BuscarEstadosOutputConverter();
    }

}
