package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter.BuscaPaginadaPacienteFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter.BuscaPaginadaPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscaPaginadaPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("BuscaPaginadaPacienteUsecase")
    @DependsOn({"BuscaPaginadaPacienteFiltroConverter", "BuscaPaginadaPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscaPaginadaPacienteUsecase criar(BuscaPaginadaPacienteFiltroConverter filtroConverter, BuscaPaginadaPacienteOutputConverter outputConverter) {
        return BuscaPaginadaPacienteUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .filtroConverter(filtroConverter)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscaPaginadaPacienteFiltroConverter")
    public BuscaPaginadaPacienteFiltroConverter createFiltroConverter() {
        return new BuscaPaginadaPacienteFiltroConverter();
    }

    @Bean("BuscaPaginadaPacienteOutputConverter")
    public BuscaPaginadaPacienteOutputConverter createOutputDataConverter() {
        return new BuscaPaginadaPacienteOutputConverter();
    }

}
