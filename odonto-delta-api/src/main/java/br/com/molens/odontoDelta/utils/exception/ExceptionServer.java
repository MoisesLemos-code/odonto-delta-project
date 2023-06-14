package br.com.molens.odontoDelta.utils.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ExceptionServer extends ExceptionCommons {
    private final String mensagemDesenvolvedor;
}
