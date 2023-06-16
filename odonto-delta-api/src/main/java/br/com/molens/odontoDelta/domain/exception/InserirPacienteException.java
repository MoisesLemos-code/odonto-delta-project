package br.com.molens.odontoDelta.domain.exception;

public class InserirPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InserirPacienteException() {
        super();
    }

    public InserirPacienteException(String message) {
        super(message);
    }

    public InserirPacienteException(String message, Throwable t) {
        super(message, t);
    }
}