package br.com.molens.odontoDelta.domain.exception;

public class BuscarCobrancaPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarCobrancaPorIdException() {
        super();
    }

    public BuscarCobrancaPorIdException(String message) {
        super(message);
    }

    public BuscarCobrancaPorIdException(String message, Throwable t) {
        super(message, t);
    }
}