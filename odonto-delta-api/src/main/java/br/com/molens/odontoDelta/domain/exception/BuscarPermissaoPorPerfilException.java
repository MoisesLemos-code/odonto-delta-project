package br.com.molens.odontoDelta.domain.exception;

public class BuscarPermissaoPorPerfilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BuscarPermissaoPorPerfilException() {
        super();
    }

    public BuscarPermissaoPorPerfilException(String message) {
        super(message);
    }

    public BuscarPermissaoPorPerfilException(String message, Throwable t) {
        super(message, t);
    }
}