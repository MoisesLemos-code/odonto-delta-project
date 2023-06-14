package br.com.molens.odontoDelta.domain.interfaces.integration;


import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.ClienteAsaas;
import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.ListaAsaasResponse;

public interface ClienteAsaasDataProvider {

    ClienteAsaas criarNovoCliente(String url, String token, ClienteAsaas cliente);

    ListaAsaasResponse buscarClientePorDocumento(String url, String token);

}
