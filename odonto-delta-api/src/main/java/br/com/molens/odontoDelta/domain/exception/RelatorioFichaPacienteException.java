package br.com.molens.odontoDelta.domain.exception;

public class RelatorioFichaPacienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RelatorioFichaPacienteException() {
        super();
    }

    public RelatorioFichaPacienteException(String message) {
        super(message);
    }

    public RelatorioFichaPacienteException(String message, Throwable t) {
        super(message, t);
    }
}