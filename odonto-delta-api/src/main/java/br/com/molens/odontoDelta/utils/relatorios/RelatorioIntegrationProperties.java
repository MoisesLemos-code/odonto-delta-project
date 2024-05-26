package br.com.molens.odontoDelta.utils.relatorios;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioIntegrationProperties {
    private HalReportIntegrationProperties.Header header;
    private HalReportIntegrationProperties.Footer footer;

    @Data
    @Builder
    public static class Header {
        String titulo1;
        String titulo2;
        String titulo3;
        String titulo4;
    }

    @Data
    @Builder
    public static class Footer {
        String rodape1;
        String rodape2;
    }
}