package br.com.molens.odontoDelta.application.factory.fichaPaciente;

import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.BuscarFichaPacientePorPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.converter.BuscarFichaPacientePorPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarFichaPacientePorPacienteFactory {

    @Autowired
    private FichaPacienteDataProvider fichaPacienteDataProvider;

    @Bean("BuscarFichaPacientePorPacienteUsecase")
    @DependsOn({"BuscarFichaPacientePorPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarFichaPacientePorPacienteUsecase criar(BuscarFichaPacientePorPacienteOutputConverter outputConverter) {
        return BuscarFichaPacientePorPacienteUsecase.builder()
                .fichaPacienteDataProvider(fichaPacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscarFichaPacientePorPacienteOutputConverter")
    public BuscarFichaPacientePorPacienteOutputConverter createOutputConverter() {
        return new BuscarFichaPacientePorPacienteOutputConverter();
    }

}
