package br.com.molens.odontoDelta.domain.exception;

public class BuscarPacientePorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarPacientePorIdException() {
        super();
    }

    public BuscarPacientePorIdException(String message) {
        super(message);
    }

    public BuscarPacientePorIdException(String message, Throwable t) {
        super(message, t);
    }
}