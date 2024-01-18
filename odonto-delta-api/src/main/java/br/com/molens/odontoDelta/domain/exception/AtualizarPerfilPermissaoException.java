package br.com.molens.odontoDelta.domain.exception;

public class AtualizarPerfilPermissaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarPerfilPermissaoException() {
        super();
    }

    public AtualizarPerfilPermissaoException(String message) {
        super(message);
    }

    public AtualizarPerfilPermissaoException(String message, Throwable t) {
        super(message, t);
    }
}