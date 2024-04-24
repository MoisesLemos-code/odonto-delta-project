package br.com.molens.odontoDelta.domain.exception;

public class BuscarFichaPacientePorPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarFichaPacientePorPacienteException() {
        super();
    }

    public BuscarFichaPacientePorPacienteException(String message) {
        super(message);
    }

    public BuscarFichaPacientePorPacienteException(String message, Throwable t) {
        super(message, t);
    }
}