package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CobrancaAsaas {

    private String id;
    private String customer;
    private String dueDate;
    private String paymentDate;
    private BigDecimal value;
    private String billingType;
    private String status;
    private String description;
    private String invoiceUrl;
    private String bankSlipUrl;

}


