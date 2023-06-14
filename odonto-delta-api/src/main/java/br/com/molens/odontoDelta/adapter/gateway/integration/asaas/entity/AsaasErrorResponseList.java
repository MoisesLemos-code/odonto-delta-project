package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsaasErrorResponseList {

    private List<AsaasErrorResponse> errors;
}
