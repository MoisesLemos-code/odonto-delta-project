package br.com.molens.odontoDelta.domain.exception;

public class AtualizarUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarUsuarioException() {
        super();
    }

    public AtualizarUsuarioException(String message) {
        super(message);
    }

    public AtualizarUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}