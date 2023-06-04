package br.com.molens.odontoDelta.domain.exception;

public class ObjetoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjetoInvalidoException() {
        super();
    }

    public ObjetoInvalidoException(String message) {
        super(message);
    }

    public ObjetoInvalidoException(String message, Throwable t) {
        super(message, t);
    }
}
