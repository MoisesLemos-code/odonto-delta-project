package br.com.molens.odontoDelta.domain.exception;

public class InserirPerfilPermissaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InserirPerfilPermissaoException() {
        super();
    }

    public InserirPerfilPermissaoException(String message) {
        super(message);
    }

    public InserirPerfilPermissaoException(String message, Throwable t) {
        super(message, t);
    }
}