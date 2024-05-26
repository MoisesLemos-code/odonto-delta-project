package br.com.molens.odontoDelta.utils.relatorios;

import br.com.molens.odontoDelta.domain.exception.RelatorioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;

@Component
public class ReportHTMLUtils {

    private static final String UTF_8 = "UTF-8";

    @Autowired
    private ResourceConfig.ClasspathResourceLoader classpathResourceLoader;

    public byte[] baixar(ConfiguracaoRelatorio configuracaoRelatorio) {
        String html = processarTemplateHTML(configuracaoRelatorio);
        String xhtml = converterParaXHTML(html);
        return this.gerarPDFBytes(xhtml);
    }

    public byte[] baixar(String html) {
        String xhtml = converterParaXHTML(html);
        return this.gerarPDFBytes(xhtml);
    }

    public ConfiguracaoRelatorio construirConfiguracaoPadrao(String nomeRelatorio, Map<String, Object> parametros) {
        return new ConfiguracaoRelatorio(nomeRelatorio, parametros);
    }

    public void carregadorImage(Map<String, Object> parametros, byte[] bytesImagem, String chaveParametro) {
        if (Objects.nonNull(bytesImagem)) {
            parametros.put(chaveParametro, Base64Utils.encodeToString(bytesImagem));
        } else {
            throw new RelatorioException("Erro ao carregar '" + chaveParametro + "' p/ o relatório.", null);
        }
    }

    private static String processarTemplateHTML(ConfiguracaoRelatorio configuracaoRelatorio) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding(UTF_8);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(configuracaoRelatorio.getTemplateBase(), construirContextoThymeleaf(configuracaoRelatorio));
    }

    private static Context construirContextoThymeleaf(ConfiguracaoRelatorio configuracaoRelatorio) {
        final Context context = new Context();
        context.setVariables(configuracaoRelatorio.getParametros());
        context.setVariable("templateHeader", configuracaoRelatorio.getTemplateHeader());
        context.setVariable("templateFooter", configuracaoRelatorio.getTemplateFooter());
        context.setVariable("templateHeaderDefault", configuracaoRelatorio.getTemplateHeaderDefault());
        context.setVariable("templateFooterDefault", configuracaoRelatorio.getTemplateFooterDefault());
        context.setVariable("templateParagrafoInicial", configuracaoRelatorio.getTemplateParagrafoInicialDefault());
        context.setVariable("templateAssinatura", configuracaoRelatorio.getNomeTemplateAssinaturasDefault());
        return context;
    }

    private byte[] gerarPDFBytes(String xHtml) {
        try (ByteArrayOutputStream pdfWriter = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFontDirectory("relatorios.html/times", EMBEDDED);
            new ITextRelatorioPDFUserAgent(renderer, classpathResourceLoader).aplicar();
            String xmlNormalizado = xHtml.replaceAll("[\\000]+", "");
            renderer.setDocumentFromString(xmlNormalizado);
            renderer.layout();
            renderer.createPDF(pdfWriter);

            return pdfWriter.toByteArray();
        } catch (Exception e) {
            throw new RelatorioException("Erro ao gerar PDF.", e);
        }
    }

    private static String converterParaXHTML(String html) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            Tidy tidy = new Tidy();
            tidy.setInputEncoding(UTF_8);
            tidy.setOutputEncoding(UTF_8);
            tidy.setXHTML(true);
            tidy.parseDOM(inputStream, outputStream);
            return outputStream.toString(UTF_8);
        } catch (IOException e) {
            throw new RelatorioException("Erro ao converter p/ XHTML.", e);
        }
    }
}
