package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class NotaFiscalAsaasWebhook {

    private Event event;
    private Invoice invoice;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Invoice {
        private String object;
        private String id;
        private String status;
        private String customer;
        private String type;
        private String statusDescription;
        private String serviceDescription;
        private String pdfUrl;
        private String xmlUrl;
        private String rpsSerie;
        private String rpsNumber;
        private String number;
        private String validationCode;
        private BigDecimal value;
        private BigDecimal deductions;
        private String effectiveDate;
        private String observations;
        private String estimatedTaxesDescription;
        private String payment;
        private String installment;
        private Taxes taxes;
        private String municipalServiceId;
        private String municipalServiceCode;
        private String municipalServiceName;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Taxes {
        private Boolean retainIss;
        private BigDecimal iss;
        private BigDecimal cofins;
        private BigDecimal csll;
        private BigDecimal inss;
        private BigDecimal ir;
        private BigDecimal pis;
    }

    public enum Event {
        INVOICE_CREATED, INVOICE_UPDATED, INVOICE_SYNCHRONIZED, INVOICE_AUTHORIZED, INVOICE_PROCESSING_CANCELLATION,
        INVOICE_CANCELED, INVOICE_CANCELLATION_DENIED, INVOICE_ERROR
    }

}