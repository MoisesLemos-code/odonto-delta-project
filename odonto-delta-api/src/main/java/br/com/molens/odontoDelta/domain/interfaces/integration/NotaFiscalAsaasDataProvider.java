package br.com.molens.odontoDelta.domain.interfaces.integration;


import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.NotaFiscalAsaas;

public interface NotaFiscalAsaasDataProvider {

    NotaFiscalAsaas criarNotaFiscal(String url, String token, NotaFiscalAsaas notaFiscal);
}
