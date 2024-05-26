package br.com.molens.odontoDelta.utils.relatorios;

import br.com.molens.odontoDelta.domain.entity.Arquivo;
import br.com.molens.odontoDelta.domain.exception.RelatorioException;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanCreationException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SistemaDeRelatorioIntegrationImpl implements SistemaDeRelatorioIntegration {

    private static final String LOGO_ATRIBUTO = "logoRelatorio";

    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;

    private ReportHTMLUtils reportHTMLUtils;

    //private SistemaDeArquivosIntegration sistemaDeArquivosIntegration;

    private RelatorioIntegrationProperties relatorioIntegrationProperties;

    @Override
    public Arquivo gerarPdf(OpcoesRelatorio opcoes) {

        validarOpcoesObrigatorias(opcoes);

        Map<String, Object> opcoesMap = converteOpcoesParaMap(opcoes);

        consumirDataSource(opcoesMap);

        configurarDataHora(opcoesMap);

        configurarProperties(opcoesMap);

        //carregarLogo(opcoesMap);

        ConfiguracaoRelatorio configuracaoRelatorio = configurarRelatorio(opcoesMap);

        return gerarRelatorio(configuracaoRelatorio, null);
    }

    public Arquivo gerarPdf(String html, String nomeArquivoSemExtensao) {
        return gerarRelatorio(new ConfiguracaoRelatorio(nomeArquivoSemExtensao), html);
    }

    private Map<String, Object> converteOpcoesParaMap(OpcoesRelatorio opcoes) {

        HashMap<String, Object> to = new HashMap<>();

        List<Field> campos = Arrays.stream(OpcoesRelatorio.class.getDeclaredFields())
                .collect(Collectors.toList());

        campos.forEach(campo -> {
            try {
                campo.setAccessible(true);
                to.put(campo.getName(), campo.get(opcoes));
            } catch (IllegalAccessException e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            }
        });
        return to;
    }

    private void validarOpcoesObrigatorias(OpcoesRelatorio opcoesRelatorio) {

        if (Objects.isNull(opcoesRelatorio.getDatasource())) {
            throw new RelatorioException("Datasource indefinido");
        }

        if (Objects.isNull(opcoesRelatorio.getNomeTemplate())) {
            throw new RelatorioException("Template indefinido");
        }
    }

    private void configurarProperties(Map<String, Object> opcoes) {
        opcoes.put("properties", relatorioIntegrationProperties);
    }

    private void configurarDataHora(Map<String, Object> opcoes) {
        opcoes.put("dataHoraUsuario", getDataHoraEUsuarioNome());
    }

    @SuppressWarnings("unchecked")
    private void consumirDataSource(Map<String, Object> opcoes) {

        Object dataSource = opcoes.remove("datasource");

        opcoes.putAll((Map<String, Object>) dataSource);
    }

    private ConfiguracaoRelatorio configurarRelatorio(Map<String, Object> opcoes) {
        return reportHTMLUtils.construirConfiguracaoPadrao((String) opcoes.get("nomeTemplate"), opcoes)
                .setNomeTemplateHeader("header-default")
                .setNomeTemplateFooter("footer-default");
    }

    private Arquivo gerarRelatorio(ConfiguracaoRelatorio configuracaoRelatorio, String html) {
        try {
            byte[] content;

            if (StringUtils.isNotEmpty(html)) {
                content = reportHTMLUtils.baixar(html);
            }else{
                content = reportHTMLUtils.baixar(configuracaoRelatorio);
            }

            return Arquivo
                    .builder()
                    .contentType("application/pdf")
                    .nome(String.format("%s.pdf", configuracaoRelatorio.getNomeRelatorio()))
                    .content(content)
                    .build();
        } catch (Exception e) {
            throw new RelatorioException("Não foi possivel realizar a geração do relatorio.", e);
        }
    }

    private String getDataHoraEUsuarioNome() {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        return data + " " + getAutor();
    }

    private String getAutor() {
        try {
            return sessaoUsuarioDataProvider.get().getNomeCompleto();
        } catch (BeanCreationException | NullPointerException e) {
            return "Sistema";
        }
    }

//    private void carregarLogo(Map<String, Object> parametros) {
//        String valor = produtoAtributoDataProvider.getValor(LOGO_ATRIBUTO);
//        Arquivo arquivo = sistemaDeArquivosIntegration.download(valor);
//        reportHTMLUtils.carregadorImage(parametros, arquivo.getContent(), LOGO_ATRIBUTO);
//    }
}