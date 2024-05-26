package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.removerCobrancaPorId.RemoverCobrancaPorIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoverCobrancaPorIdFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Bean("RemoverCobrancaPorIdUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RemoverCobrancaPorIdUsecase criar() {
        return RemoverCobrancaPorIdUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .build();
    }

}
