package br.com.molens.odontoDelta.application.factory.paciente;

import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.removerPacientePorId.RemoverPacientePorIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoverPacientePorIdFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Bean("RemoverPacientePorIdUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RemoverPacientePorIdUsecase criar() {
        return RemoverPacientePorIdUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .build();
    }

}
