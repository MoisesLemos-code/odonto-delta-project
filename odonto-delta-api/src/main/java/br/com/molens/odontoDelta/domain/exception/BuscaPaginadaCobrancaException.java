package br.com.molens.odontoDelta.domain.exception;

public class BuscaPaginadaCobrancaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscaPaginadaCobrancaException() {
        super();
    }

    public BuscaPaginadaCobrancaException(String message) {
        super(message);
    }

    public BuscaPaginadaCobrancaException(String message, Throwable t) {
        super(message, t);
    }
}