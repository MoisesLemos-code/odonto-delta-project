package br.com.molens.odontoDelta.application.factory.cobranca;

import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.converter.InserirCobrancaOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class InserirCobrancaFactory {

    @Autowired
    private CobrancaDataProvider cobrancaDataProvider;

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Bean("InserirCobrancaUsecase")
    @DependsOn({"InserirPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirCobrancaUsecase criar(InserirCobrancaOutputConverter outputConverter) {
        return InserirCobrancaUsecase.builder()
                .cobrancaDataProvider(cobrancaDataProvider)
                .pacienteDataProvider(pacienteDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("InserirCobrancaOutputConverter")
    public InserirCobrancaOutputConverter createOutputConverter() {
        return new InserirCobrancaOutputConverter();
    }

}
