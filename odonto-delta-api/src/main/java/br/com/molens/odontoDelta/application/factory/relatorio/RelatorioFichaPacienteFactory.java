package br.com.molens.odontoDelta.application.factory.relatorio;

import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.relatorios.relatorioFichaPaciente.RelatorioFichaPacienteUsecase;
import br.com.molens.odontoDelta.utils.relatorios.SistemaDeRelatorioIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RelatorioFichaPacienteFactory {

    @Autowired
    private PacienteDataProvider pacienteDataProvider;

    @Autowired
    private FichaPacienteDataProvider fichaPacienteDataProvider;

    @Autowired
    private SistemaDeRelatorioIntegration sistemaDeRelatorioIntegration;

    @Bean("RelatorioFichaPacienteUsecase")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RelatorioFichaPacienteUsecase criar() {
        return RelatorioFichaPacienteUsecase.builder()
                .pacienteDataProvider(pacienteDataProvider)
                .fichaPacienteDataProvider(fichaPacienteDataProvider)
                .sistemaDeRelatorioIntegration(sistemaDeRelatorioIntegration)
                .build();
    }


}
