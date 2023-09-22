package br.com.molens.odontoDelta.domain.exception;

public class RemoverPerfilPorIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoverPerfilPorIdException() {
        super();
    }

    public RemoverPerfilPorIdException(String message) {
        super(message);
    }

    public RemoverPerfilPorIdException(String message, Throwable t) {
        super(message, t);
    }
}