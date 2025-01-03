package br.com.molens.odontoDelta.domain.exception;

public class PesquisarPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PesquisarPacienteException() {
        super();
    }

    public PesquisarPacienteException(String message) {
        super(message);
    }

    public PesquisarPacienteException(String message, Throwable t) {
        super(message, t);
    }
}