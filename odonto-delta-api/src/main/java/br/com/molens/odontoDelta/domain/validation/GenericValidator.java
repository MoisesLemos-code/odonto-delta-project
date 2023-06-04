package br.com.molens.odontoDelta.domain.validation;

public interface GenericValidator<U> {
    void validate(U u);
}
