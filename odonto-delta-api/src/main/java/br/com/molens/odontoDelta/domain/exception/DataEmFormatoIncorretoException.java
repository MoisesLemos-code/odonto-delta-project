package br.com.molens.odontoDelta.domain.exception;

public class DataEmFormatoIncorretoException extends RuntimeException {

    public DataEmFormatoIncorretoException() {
        super("Data em formato incorreto.");
    }
}