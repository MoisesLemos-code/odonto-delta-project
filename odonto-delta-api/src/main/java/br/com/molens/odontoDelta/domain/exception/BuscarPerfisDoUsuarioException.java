package br.com.molens.odontoDelta.domain.exception;

public class BuscarPerfisDoUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarPerfisDoUsuarioException() {
        super();
    }

    public BuscarPerfisDoUsuarioException(String message) {
        super(message);
    }

    public BuscarPerfisDoUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}