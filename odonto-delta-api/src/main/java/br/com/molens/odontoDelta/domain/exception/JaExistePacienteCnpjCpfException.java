package br.com.molens.odontoDelta.domain.exception;

public class JaExistePacienteCnpjCpfException extends RuntimeException {

    public JaExistePacienteCnpjCpfException() {
        super("Já existe um paciente com este CNPJ/CPF cadastrado.");
    }
}