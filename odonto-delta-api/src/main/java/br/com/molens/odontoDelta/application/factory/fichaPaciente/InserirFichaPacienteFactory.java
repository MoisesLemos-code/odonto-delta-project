package br.com.molens.odontoDelta.application.factory.fichaPaciente;

import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.converter.InserirFichaPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class InserirFichaPacienteFactory {

    @Autowired
    private FichaPacienteDataProvider fichaPacienteDataProvider;

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("InserirFichaPacienteUsecase")
    @DependsOn({"InserirPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirFichaPacienteUsecase criar(InserirFichaPacienteOutputConverter outputConverter) {
        return InserirFichaPacienteUsecase.builder()
                .fichaPacienteDataProvider(fichaPacienteDataProvider)
                .pacienteDataProvider(pacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("InserirFichaPacienteOutputConverter")
    public InserirFichaPacienteOutputConverter createOutputConverter() {
        return new InserirFichaPacienteOutputConverter();
    }

}
