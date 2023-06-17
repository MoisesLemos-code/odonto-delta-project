package br.com.molens.odontoDelta.domain.exception;

public class AtualizarPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarPacienteException() {
        super();
    }

    public AtualizarPacienteException(String message) {
        super(message);
    }

    public AtualizarPacienteException(String message, Throwable t) {
        super(message, t);
    }
}