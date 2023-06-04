package br.com.molens.odontoDelta.domain.exception;

public class PregaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PregaoException() {
        super();
    }

    public PregaoException(String message) {
        super(message);
    }

    public PregaoException(String message, Throwable t) {
        super(message, t);
    }
}
