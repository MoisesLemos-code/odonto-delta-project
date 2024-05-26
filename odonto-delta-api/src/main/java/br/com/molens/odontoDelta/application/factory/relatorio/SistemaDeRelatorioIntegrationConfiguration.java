package br.com.molens.odontoDelta.application.factory.relatorio;

import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.utils.relatorios.HalReportIntegrationProperties;
import br.com.molens.odontoDelta.utils.relatorios.RelatorioIntegrationProperties;
import br.com.molens.odontoDelta.utils.relatorios.ReportHTMLUtils;
import br.com.molens.odontoDelta.utils.relatorios.SistemaDeRelatorioIntegration;
import br.com.molens.odontoDelta.utils.relatorios.SistemaDeRelatorioIntegrationImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

//@RefreshScope
@Configuration
public class SistemaDeRelatorioIntegrationConfiguration {

    @Bean
    @Primary
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @DependsOn({"RelatorioIntegrationProperties"})
    public SistemaDeRelatorioIntegration createSistemaDeRelatorioIntegration(
            RelatorioIntegrationProperties relatorioIntegrationProperties,
            SessaoUsuarioDataProvider sessaoUsuarioDataProvider,
            ReportHTMLUtils reportHTMLUtils
    ) {
        return SistemaDeRelatorioIntegrationImpl.builder()
                .relatorioIntegrationProperties(relatorioIntegrationProperties)
                .sessaoUsuarioDataProvider(sessaoUsuarioDataProvider)
                .reportHTMLUtils(reportHTMLUtils)
                .build();
    }

    @Bean("RelatorioIntegrationProperties")
    public RelatorioIntegrationProperties createRelatorioIntegrationProperties() {
        return RelatorioIntegrationProperties.builder()
                .header(HalReportIntegrationProperties.Header.builder().build())
                .footer(HalReportIntegrationProperties.Footer.builder().build())
                .build();
// TODO Reativar header e footer quando for necessário exibir títulos e rodapés nos relatórios
//  Será necessário criar changeset das propriedades do relatório
//        return RelatorioIntegrationProperties
//                .builder()
//                .header(HalReportIntegrationProperties.Header
//                        .builder()
//                        .titulo1(licitacaoPropertiesDataProvider.getRelatorio().getHeader().getTitulo1())
//                        .titulo2(licitacaoPropertiesDataProvider.getRelatorio().getHeader().getTitulo2())
//                        .titulo3(licitacaoPropertiesDataProvider.getRelatorio().getHeader().getTitulo3())
//                        .titulo4(licitacaoPropertiesDataProvider.getRelatorio().getHeader().getTitulo4())
//                        .build())
//                .footer(HalReportIntegrationProperties.Footer
//                        .builder()
//                        .rodape1(licitacaoPropertiesDataProvider.getRelatorio().getFooter().getRodape1())
//                        .rodape2(licitacaoPropertiesDataProvider.getRelatorio().getFooter().getRodape2())
//                        .build())
//                .build();
    }
}

