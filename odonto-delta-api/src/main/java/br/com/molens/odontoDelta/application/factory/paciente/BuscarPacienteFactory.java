package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BuscarPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("BuscarPacientePorIdUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BuscarPacientePorIdUsecase criar() {
        return BuscarPacientePorIdUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .build();
    }

}
