package br.com.molens.odontoDelta.domain.exception;

public class BuscaPaginadaPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscaPaginadaPacienteException() {
        super();
    }

    public BuscaPaginadaPacienteException(String message) {
        super(message);
    }

    public BuscaPaginadaPacienteException(String message, Throwable t) {
        super(message, t);
    }
}