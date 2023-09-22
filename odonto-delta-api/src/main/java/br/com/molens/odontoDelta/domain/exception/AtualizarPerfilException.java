package br.com.molens.odontoDelta.domain.exception;

public class AtualizarPerfilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarPerfilException() {
        super();
    }

    public AtualizarPerfilException(String message) {
        super(message);
    }

    public AtualizarPerfilException(String message, Throwable t) {
        super(message, t);
    }
}