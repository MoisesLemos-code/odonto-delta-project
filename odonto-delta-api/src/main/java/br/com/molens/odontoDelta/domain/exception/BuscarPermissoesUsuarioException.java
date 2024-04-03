package br.com.molens.odontoDelta.domain.exception;

public class BuscarPermissoesUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarPermissoesUsuarioException() {
        super();
    }

    public BuscarPermissoesUsuarioException(String message) {
        super(message);
    }

    public BuscarPermissoesUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}