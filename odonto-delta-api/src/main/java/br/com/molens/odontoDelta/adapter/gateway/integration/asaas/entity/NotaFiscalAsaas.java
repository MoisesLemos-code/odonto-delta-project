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
public class NotaFiscalAsaas {

    private String id;
    private Status status;
    private String type;
    private String pdfUrl;
    private String xmlUrl;
    private String rpsSerie;
    private String rpsNumber;
    private String number;
    private String serviceDescription;
    private String observations;
    private BigDecimal value;
    private BigDecimal deductions;
    private String effectiveDate;
    private String municipalServiceId;
    private String municipalServiceCode;
    private String municipalServiceName;
    private String updatePayment;
    private String payment;
    private String customer;
    private Taxas taxes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Taxas {

        private Boolean retainIss;
        private BigDecimal iss;
        private BigDecimal cofins;
        private BigDecimal csll;
        private BigDecimal inss;
        private BigDecimal ir;
        private BigDecimal pis;

    }

    public enum Status {
        SCHEDULED, AUTHORIZED, PROCESSING_CANCELLATION, CANCELED, CANCELLATION_DENIED, ERROR
    }
}
