package br.com.molens.odontoDelta.seguranca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AutorizacaoException extends RuntimeException {
    public AutorizacaoException(String message) {
        super(message);
    }
}
