package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter.BuscaPaginadaCobrancaFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter.BuscaPaginadaCobrancaOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscaPaginadaCobrancaFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Bean("BuscaPaginadaCobrancaUsecase")
    @DependsOn({"BuscaPaginadaCobrancaFiltroConverter", "BuscaPaginadaCobrancaOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscaPaginadaCobrancaUsecase criar(BuscaPaginadaCobrancaFiltroConverter filtroConverter, BuscaPaginadaCobrancaOutputConverter outputConverter) {
        return BuscaPaginadaCobrancaUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .filtroConverter(filtroConverter)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscaPaginadaCobrancaFiltroConverter")
    public BuscaPaginadaCobrancaFiltroConverter createFiltroConverter() {
        return new BuscaPaginadaCobrancaFiltroConverter();
    }

    @Bean("BuscaPaginadaCobrancaOutputConverter")
    public BuscaPaginadaCobrancaOutputConverter createOutputDataConverter() {
        return new BuscaPaginadaCobrancaOutputConverter();
    }
}
