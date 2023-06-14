package br.com.molens.odontoDelta.domain.interfaces.integration;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.CobrancaAsaas;

public interface CobrancaAsaasDataProvider {

    CobrancaAsaas criarCobranca(CobrancaAsaas cobrancaAsaas, String url, String token);
}
