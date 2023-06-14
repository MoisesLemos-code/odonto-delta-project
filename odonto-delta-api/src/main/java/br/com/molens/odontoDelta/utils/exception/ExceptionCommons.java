package br.com.molens.odontoDelta.utils.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionCommons {
    protected String titulo;
    protected int status;
    protected LocalDateTime dataHora;
    protected String mensagem;
}
