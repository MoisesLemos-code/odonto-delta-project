package br.com.molens.odontoDelta.domain.exception;

public class BuscarUsuarioPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarUsuarioPorIdException() {
        super();
    }

    public BuscarUsuarioPorIdException(String message) {
        super(message);
    }

    public BuscarUsuarioPorIdException(String message, Throwable t) {
        super(message, t);
    }
}