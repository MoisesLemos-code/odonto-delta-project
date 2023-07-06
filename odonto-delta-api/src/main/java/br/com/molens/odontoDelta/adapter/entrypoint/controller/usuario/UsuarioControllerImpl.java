package br.com.molens.odontoDelta.adapter.entrypoint.controller.usuario;

import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private InserirUsuarioUsecase inserirUsuarioUsecase;

    @Override
    public ResponseEntity<InserirUsuarioOutput> inserir(InserirUsuarioInput input) {
        return new ResponseEntity<>(inserirUsuarioUsecase.executar(input), HttpStatus.CREATED);
    }


}