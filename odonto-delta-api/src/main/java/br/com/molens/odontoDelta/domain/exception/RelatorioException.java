package br.com.molens.odontoDelta.domain.exception;

public class RelatorioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RelatorioException() {
        super();
    }

    public RelatorioException(String message) {
        super(message);
    }

    public RelatorioException(String message, Throwable t) {
        super(message, t);
    }
}