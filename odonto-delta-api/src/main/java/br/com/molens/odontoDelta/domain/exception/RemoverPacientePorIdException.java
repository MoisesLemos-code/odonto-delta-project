package br.com.molens.odontoDelta.domain.exception;

public class RemoverPacientePorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoverPacientePorIdException() {
        super();
    }

    public RemoverPacientePorIdException(String message) {
        super(message);
    }

    public RemoverPacientePorIdException(String message, Throwable t) {
        super(message, t);
    }
}