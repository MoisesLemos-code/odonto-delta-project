package br.com.molens.odontoDelta.application.factory.fichaPaciente;

import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.converter.AtualizarFichaPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarFichaPacienteFactory {

    @Autowired
    private FichaPacienteDataProvider fichaPacienteDataProvider;

    @Bean("AtualizarFichaPacienteUsecase")
    @DependsOn({"AtualizarFichaPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarFichaPacienteUsecase criar(AtualizarFichaPacienteOutputConverter outputConverter) {
        return AtualizarFichaPacienteUsecase.builder()
                .fichaPacienteDataProvider(fichaPacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("AtualizarFichaPacienteOutputConverter")
    public AtualizarFichaPacienteOutputConverter createOutputConverter() {
        return new AtualizarFichaPacienteOutputConverter();
    }

}
