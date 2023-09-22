package br.com.molens.odontoDelta.domain.exception;

public class BuscarPerfilPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarPerfilPorIdException() {
        super();
    }

    public BuscarPerfilPorIdException(String message) {
        super(message);
    }

    public BuscarPerfilPorIdException(String message, Throwable t) {
        super(message, t);
    }
}