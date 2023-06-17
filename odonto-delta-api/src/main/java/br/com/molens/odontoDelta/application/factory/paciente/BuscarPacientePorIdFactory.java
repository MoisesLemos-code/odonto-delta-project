package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.converter.BuscarPacientePorIdOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPacientePorIdFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("BuscarPacientePorIdUsecase")
    @DependsOn({"BuscarPacientePorIdOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPacientePorIdUsecase criar(BuscarPacientePorIdOutputConverter outputConverter) {
        return BuscarPacientePorIdUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarPacientePorIdOutputConverter")
    public BuscarPacientePorIdOutputConverter createOutputConverter() {
        return new BuscarPacientePorIdOutputConverter();
    }


}
