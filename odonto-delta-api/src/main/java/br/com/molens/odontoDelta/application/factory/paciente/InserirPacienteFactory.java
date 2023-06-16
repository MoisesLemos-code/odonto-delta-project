package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.converter.InserirPacienteOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
public class InserirPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Autowired
    private EmpresaDataProvider empresaDataProvider;

    @Autowired
    private MunicipioDataProvider municipioDataProvider;

    @Bean("InserirPacienteUsecase")
    @DependsOn({"InserirPacienteOutputConverter"})
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InserirPacienteUsecase criar(InserirPacienteOutputConverter inserirPacienteOutputConverter) {
        return InserirPacienteUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .empresaDataProvider(empresaDataProvider)
                .municipioDataProvider(municipioDataProvider)
                .inserirPacienteOutputConverter(inserirPacienteOutputConverter)
                .build();
    }

    @Bean("InserirPacienteOutputConverter")
    public InserirPacienteOutputConverter createOutputConverter() {
        return new InserirPacienteOutputConverter();
    }

}
