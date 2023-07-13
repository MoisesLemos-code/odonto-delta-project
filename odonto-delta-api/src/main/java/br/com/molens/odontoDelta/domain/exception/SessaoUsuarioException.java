package br.com.molens.odontoDelta.domain.exception;

public class SessaoUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SessaoUsuarioException() {
        super();
    }

    public SessaoUsuarioException(String message) {
        super(message);
    }

    public SessaoUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}