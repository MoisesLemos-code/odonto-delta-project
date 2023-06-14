package br.com.molens.odontoDelta.application.config;

import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.AsaasValidationException;
import br.com.molens.odontoDelta.domain.exception.FalhaPublicacaoMensagemException;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.exception.InserirPacienteException;
import br.com.molens.odontoDelta.utils.exception.ExceptionCommons;
import br.com.molens.odontoDelta.utils.exception.ExceptionServer;
import br.com.molens.odontoDelta.utils.exception.ExceptionValidFields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionValidFields> handlerMethodArgumentNotValidException(
            BindException exception) {

        List<ObjectError> fieldErrors = exception.getBindingResult().getAllErrors();

        String fields = exception.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<ExceptionValidFields>(
                ExceptionValidFields.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Requisição com campos invalidos")
                        .requestBody(exception.getBindingResult().getObjectName())
                        .mensagem(fieldsMessage)
                        .campos(fields)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionValidFields> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ExceptionValidFields.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Requisição com campos invalidos")
                        .requestBody(exception.getBindingResult().getObjectName())
                        .campos(fields)
                        .mensagem(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionCommons> handlerMethodArgumentNotValidException(
            IllegalArgumentException exception) {

        return new ResponseEntity<>(
                ExceptionCommons.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Requisição com campos invalidos")
                        .mensagem(exception.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            {
                    InserirPacienteException.class,
                    FalhaPublicacaoMensagemException.class
            })
    public ResponseEntity<ExceptionServer> handlerMethodArgumentNotValidException(
            RuntimeException exception) {

        return new ResponseEntity<>(
                ExceptionServer.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .titulo("Internal Server Error Exception")
                        .mensagemDesenvolvedor(exception.getStackTrace()[1].getClassName())
                        .mensagem(exception.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AsaasValidationException.class)
    public ResponseEntity<ExceptionCommons> handlerMethodArgumentNotValidException(
            AsaasValidationException exception) {

        return new ResponseEntity<>(
                ExceptionCommons.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .titulo("Internal Server Error")
                        .mensagem(exception.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
