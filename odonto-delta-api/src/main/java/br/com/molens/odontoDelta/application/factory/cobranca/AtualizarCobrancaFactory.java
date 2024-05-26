package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.AtualizarCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.converter.AtualizarCobrancaOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarCobrancaFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Bean("AtualizarCobrancaUsecase")
    @DependsOn({"AtualizarCobrancaOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarCobrancaUsecase criar(AtualizarCobrancaOutputConverter outputConverter) {
        return AtualizarCobrancaUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .pacienteDataProvider(pacienteDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("AtualizarCobrancaOutputConverter")
    public AtualizarCobrancaOutputConverter createOutputConverter() {
        return new AtualizarCobrancaOutputConverter();
    }

}
