package br.com.molens.odontoDelta.domain.interfaces.integration;


import br.com.molens.odontoDelta.domain.entity.Email;

public interface EmailDataProvider {
    void enviar(Email email);
}
