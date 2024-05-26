package br.com.molens.odontoDelta.utils.relatorios;

import br.com.molens.odontoDelta.domain.entity.Arquivo;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

public interface SistemaDeRelatorioIntegration {

    @Builder
    @Data
    class OpcoesRelatorio {
        private Map<String, Object> datasource;
        private String nomeTemplate;
        private String html;
    }

    Arquivo gerarPdf(OpcoesRelatorio opcoes);

    Arquivo gerarPdf(String html, String nomeArquivoSemExtensao);
}