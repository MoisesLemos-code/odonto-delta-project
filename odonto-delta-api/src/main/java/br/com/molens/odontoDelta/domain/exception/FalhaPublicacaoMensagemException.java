package br.com.molens.odontoDelta.domain.exception;

public class FalhaPublicacaoMensagemException extends RuntimeException {

    public FalhaPublicacaoMensagemException() {
    }

    public FalhaPublicacaoMensagemException(Throwable cause) {
        super(cause);
    }

    public FalhaPublicacaoMensagemException(String string) {
        super(string);
    }
}
