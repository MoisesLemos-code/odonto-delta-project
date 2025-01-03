package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente.PesquisarPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente.converter.BuscaPesquisaPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class PesquisarPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("PesquisarPacienteUsecase")
    @DependsOn({"BuscaPesquisaPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PesquisarPacienteUsecase criar(BuscaPesquisaPacienteOutputConverter outputConverter) {
        return PesquisarPacienteUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean("BuscaPesquisaPacienteOutputConverter")
    public BuscaPesquisaPacienteOutputConverter createOutputDataConverter() {
        return new BuscaPesquisaPacienteOutputConverter();
    }

}
