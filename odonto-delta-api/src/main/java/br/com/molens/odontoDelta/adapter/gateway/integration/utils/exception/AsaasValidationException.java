package br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception;

import lombok.Data;

import java.util.List;

@Data
public class AsaasValidationException extends RuntimeException {
    List<String> args;

    public AsaasValidationException(String message) {
        super(message);
    }

    public AsaasValidationException(Throwable throwable) {
        super(throwable.getMessage() != null ? throwable.getMessage() : "", throwable);
    }
}
