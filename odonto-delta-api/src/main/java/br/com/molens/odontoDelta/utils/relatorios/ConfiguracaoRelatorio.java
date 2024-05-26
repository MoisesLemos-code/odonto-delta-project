package br.com.molens.odontoDelta.utils.relatorios;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConfiguracaoRelatorio {

    private static final String PREFIXO_RELATORIOS_HTML = "relatorios\\html";
    private static final String PREFIXO_TEMPLATE_ASSINATURAS = "assinaturas";
    private static final String PREFIXO_TEMPLATE_HEADER = "header";
    private static final String PREFIXO_TEMPLATE_FOOTER = "footer";
    private static final String PREFIXO_TEMPLATE_PARAGRAFO_INICIAL = "paragrafoinicial";
    private static final String SUFIXO_TEMPLATE = "template";
    private static final String NOME_TEMPLATE_HEADER_DEFAULT = "header-default";
    private static final String NOME_TEMPLATE_FOOTER_DEFAULT = "footer-default";
    private static final String NOME_TEMPLATE_PARAGRAFO_INICIAL_DEFAULT = "paragrafo-inicial-default";
    private static final String NOME_TEMPLATE_ASSINATURAS_DEFAULT = "assinaturas-default";

    private String nomeTemplateHeader;
    private String nomeTemplateFooter;
    private String nomeTemplateParagrafoInicial;
    private String nomeTemplateAssinaturas;
    private String nomeRelatorio;
    private Map<String, Object> parametros;
    private Collection listagemCampos;

    public ConfiguracaoRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
        this.parametros = new HashMap<>();
    }

    public ConfiguracaoRelatorio(String nomeRelatorio, Map<String, Object> parametros) {
        this.nomeRelatorio = nomeRelatorio;
        this.parametros = parametros;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public Collection getListagemCampos() {
        return listagemCampos;
    }

    public ConfiguracaoRelatorio setListagemCampos(Collection listagemCampos) {
        this.listagemCampos = listagemCampos;
        return this;
    }

    public ConfiguracaoRelatorio adicionarParametro(String nome, Object valor) {
        parametros.put(nome, valor);
        return this;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public Map<String, Object> getParametrosDesejados(List<String> parametrosDesejados) {
        Map<String, Object> resultado = new HashMap<>();
        parametrosDesejados.forEach(parametro -> resultado.put(parametro, parametros.get(parametro)));
        return resultado;
    }

    public String getTemplateBase() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, nomeRelatorio, SUFIXO_TEMPLATE);
    }

    public String getTemplateHeader() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_HEADER,
                Objects.isNull(nomeTemplateHeader) ? NOME_TEMPLATE_HEADER_DEFAULT : nomeTemplateHeader);
    }

    public ConfiguracaoRelatorio setNomeTemplateHeader(String nomeTemplateHeader) {
        this.nomeTemplateHeader = nomeTemplateHeader;
        return this;
    }

    public String getTemplateFooter() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_FOOTER,
                Objects.isNull(nomeTemplateFooter) ? NOME_TEMPLATE_FOOTER_DEFAULT : nomeTemplateFooter);
    }

    public ConfiguracaoRelatorio setNomeTemplateFooter(String nomeTemplateFooter) {
        this.nomeTemplateFooter = nomeTemplateFooter;
        return this;
    }

    public ConfiguracaoRelatorio setNomeTemplateParagrafoInicial(String nomeTemplateParagrafoInicial) {
        this.nomeTemplateParagrafoInicial = nomeTemplateParagrafoInicial;
        return this;
    }

    public ConfiguracaoRelatorio setNomeTemplateAssinaturas(String nomeTemplateAssinaturas) {
        this.nomeTemplateAssinaturas = nomeTemplateAssinaturas;
        return this;
    }

    public String getNomeTemplateAssinaturasDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_ASSINATURAS, NOME_TEMPLATE_ASSINATURAS_DEFAULT);
    }

    public String getTemplateHeaderDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_HEADER, NOME_TEMPLATE_HEADER_DEFAULT);
    }

    public String getTemplateParagrafoInicialDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_PARAGRAFO_INICIAL, NOME_TEMPLATE_PARAGRAFO_INICIAL_DEFAULT);
    }

    public String getTemplateFooterDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_FOOTER, NOME_TEMPLATE_FOOTER_DEFAULT);
    }

    public static String getSufixoTemplate() {
        return SUFIXO_TEMPLATE;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }
}
