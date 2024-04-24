package br.com.molens.odontoDelta.domain.exception;

public class AtualizarFichaPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtualizarFichaPacienteException() {
        super();
    }

    public AtualizarFichaPacienteException(String message) {
        super(message);
    }

    public AtualizarFichaPacienteException(String message, Throwable t) {
        super(message, t);
    }
}