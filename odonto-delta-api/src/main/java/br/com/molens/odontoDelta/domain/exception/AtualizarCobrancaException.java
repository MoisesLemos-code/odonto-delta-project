package br.com.molens.odontoDelta.domain.exception;

public class AtualizarCobrancaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarCobrancaException() {
        super();
    }

    public AtualizarCobrancaException(String message) {
        super(message);
    }

    public AtualizarCobrancaException(String message, Throwable t) {
        super(message, t);
    }
}