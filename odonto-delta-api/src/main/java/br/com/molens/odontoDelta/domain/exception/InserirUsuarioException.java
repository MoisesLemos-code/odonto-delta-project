package br.com.molens.odontoDelta.domain.exception;

public class InserirUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InserirUsuarioException() {
        super();
    }

    public InserirUsuarioException(String message) {
        super(message);
    }

    public InserirUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}