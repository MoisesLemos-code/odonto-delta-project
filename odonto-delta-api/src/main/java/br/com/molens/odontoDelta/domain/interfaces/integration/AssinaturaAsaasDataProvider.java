package br.com.molens.odontoDelta.domain.interfaces.integration;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.AssinaturaAsaas;

public interface AssinaturaAsaasDataProvider {

    AssinaturaAsaas criarAssinatura(AssinaturaAsaas assinaturaAsaas, String url, String token);
}
