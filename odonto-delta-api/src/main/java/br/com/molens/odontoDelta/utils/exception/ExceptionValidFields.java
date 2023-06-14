package br.com.molens.odontoDelta.utils.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ExceptionValidFields extends ExceptionCommons {
    private final String campos;
    private final String requestBody;
}
