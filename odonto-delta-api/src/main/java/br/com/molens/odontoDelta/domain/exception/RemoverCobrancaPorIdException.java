package br.com.molens.odontoDelta.domain.exception;

public class RemoverCobrancaPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoverCobrancaPorIdException() {
        super();
    }

    public RemoverCobrancaPorIdException(String message) {
        super(message);
    }

    public RemoverCobrancaPorIdException(String message, Throwable t) {
        super(message, t);
    }
}