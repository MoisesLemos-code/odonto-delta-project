package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaAsaasResponse {
    private String object;
    private Boolean hasMore;
    private Integer totalCount;
    private Integer limit;
    private Integer offset;
    private List<ClienteAsaas> data;
}
