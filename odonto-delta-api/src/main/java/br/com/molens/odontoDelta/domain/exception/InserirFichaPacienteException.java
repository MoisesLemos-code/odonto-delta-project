package br.com.molens.odontoDelta.domain.exception;

public class InserirFichaPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InserirFichaPacienteException() {
        super();
    }

    public InserirFichaPacienteException(String message) {
        super(message);
    }

    public InserirFichaPacienteException(String message, Throwable t) {
        super(message, t);
    }
}