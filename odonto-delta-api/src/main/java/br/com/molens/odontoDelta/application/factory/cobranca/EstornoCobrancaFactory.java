package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.estornarCobranca.EstornarCobrancaUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EstornoCobrancaFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Bean("EstornarCobrancaUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public EstornarCobrancaUsecase criar() {
        return EstornarCobrancaUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .build();
    }

}
