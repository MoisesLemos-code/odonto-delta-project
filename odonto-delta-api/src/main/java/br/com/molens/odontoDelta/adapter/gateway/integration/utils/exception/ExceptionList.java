package br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionList extends RuntimeException {
    private List<AsaasValidationException> exceptions;

    public ExceptionList(String message, List<AsaasValidationException> exceptions) {
        super(message);
        this.exceptions = exceptions;
    }

}
