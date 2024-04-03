package br.com.molens.odontoDelta.domain.exception;

public class AtualizarPerfilUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarPerfilUsuarioException() {
        super();
    }

    public AtualizarPerfilUsuarioException(String message) {
        super(message);
    }

    public AtualizarPerfilUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}