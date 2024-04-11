package br.com.molens.odontoDelta.domain.exception;

public class ValidarPermissaoUsuarioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidarPermissaoUsuarioException() {
        super();
    }

    public ValidarPermissaoUsuarioException(String message) {
        super(message);
    }

    public ValidarPermissaoUsuarioException(String message, Throwable t) {
        super(message, t);
    }
}