package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.converter.AtualizarPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class AtualizarPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("AtualizarPacienteUsecase")
    @DependsOn({"AtualizarPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AtualizarPacienteUsecase criar(AtualizarPacienteOutputConverter outputConverter) {
        return AtualizarPacienteUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("AtualizarPacienteOutputConverter")
    public AtualizarPacienteOutputConverter createOutputConverter() {
        return new AtualizarPacienteOutputConverter();
    }


}
