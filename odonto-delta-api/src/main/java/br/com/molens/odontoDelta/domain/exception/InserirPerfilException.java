package br.com.molens.odontoDelta.domain.exception;

public class InserirPerfilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InserirPerfilException() {
        super();
    }

    public InserirPerfilException(String message) {
        super(message);
    }

    public InserirPerfilException(String message, Throwable t) {
        super(message, t);
    }
}