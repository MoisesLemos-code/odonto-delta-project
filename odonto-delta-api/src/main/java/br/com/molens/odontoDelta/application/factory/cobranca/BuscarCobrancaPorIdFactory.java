package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.converter.BuscarCobrancaPorIdOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarCobrancaPorIdFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Bean("BuscarCobrancaPorIdUsecase")
    @DependsOn({"BuscarCobrancaPorIdOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarCobrancaPorIdUsecase criar(BuscarCobrancaPorIdOutputConverter outputConverter) {
        return BuscarCobrancaPorIdUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarCobrancaPorIdOutputConverter")
    public BuscarCobrancaPorIdOutputConverter createOutputConverter() {
        return new BuscarCobrancaPorIdOutputConverter();
    }

}
