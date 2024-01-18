package br.com.molens.odontoDelta.domain.exception;

public class RemoverPermissaoPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoverPermissaoPorIdException() {
        super();
    }

    public RemoverPermissaoPorIdException(String message) {
        super(message);
    }

    public RemoverPermissaoPorIdException(String message, Throwable t) {
        super(message, t);
    }
}