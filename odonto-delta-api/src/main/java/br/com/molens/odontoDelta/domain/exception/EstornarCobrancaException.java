package br.com.molens.odontoDelta.domain.exception;

public class EstornarCobrancaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstornarCobrancaException() {
        super();
    }

    public EstornarCobrancaException(String message) {
        super(message);
    }

    public EstornarCobrancaException(String message, Throwable t) {
        super(message, t);
    }
}