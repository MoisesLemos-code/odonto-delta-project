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
public class CobrancaAsaasWebhook {

    private Event event;
    private Payment payment;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payment {
        private String object;
        private String id;
        private String dateCreated;
        private String customer;
        private String subscription;
        private String installment;
        private String dueDate;
        private BigDecimal value;
        private String netValue;
        private BillingType billingType;
        private String status;
        private String description;
        private String externalReference;
        private String confirmedDate;
        private String originalValue;
        private String interestValue;
        private String originalDueDate;
        private String paymentDate;
        private String clientPaymentDate;
        private String invoiceUrl;
        private String bankSlipUrl;
        private String invoiceNumber;
        private String deleted;
        private CreditCard creditCard;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreditCard {
        private String creditCardNumber;
        private String creditCardBrand;
        private String creditCardToken;
    }

    public enum BillingType {
        BOLETO, CREDIT_CARD, DEBIT_CARD, UNDEFINED, TRANSFER, DEPOSIT, PIX
    }

    public enum Event {
        PAYMENT_CREATED, PAYMENT_UPDATED, PAYMENT_CONFIRMED, PAYMENT_RECEIVED, PAYMENT_OVERDUE,
        PAYMENT_DELETED, PAYMENT_RESTORED, PAYMENT_REFUNDED, PAYMENT_RECEIVED_IN_CASH_UNDONE,
        PAYMENT_CHARGEBACK_REQUESTED, PAYMENT_CHARGEBACK_DISPUTE, PAYMENT_AWAITING_CHARGEBACK_REVERSAL,
        PAYMENT_DUNNING_RECEIVED, PAYMENT_DUNNING_REQUESTED, PAYMENT_BANK_SLIP_VIEWED, PAYMENT_CHECKOUT_VIEWED,
        PAYMENT_REFUND_IN_PROGRESS
    }

}