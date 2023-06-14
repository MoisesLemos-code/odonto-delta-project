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
public class AssinaturaAsaas {

    private String id;
    private String billingType;
    private String customer;
    private String cycle;
    private String dateCreated;
    private String nextDueDate;
    private String endDate;
    private String status;
    private String maxPayments;
    private BigDecimal value;
}


