package br.com.molens.odontoDelta.adapter.entrypoint.controller.usuario;

import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdUsecase;
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
    private BuscarUsuarioPorIdUsecase buscarUsuarioPorIdUsecase;
    private AtualizarUsuarioUsecase atualizarUsuarioUsecase;

    @Override
    public ResponseEntity<InserirUsuarioOutput> inserir(InserirUsuarioInput input) {
        return new ResponseEntity<>(inserirUsuarioUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuscarUsuarioPorIdOutput> buscarPorId(Long usuarioId) {
        return new ResponseEntity<>(buscarUsuarioPorIdUsecase.executar(BuscarUsuarioPorIdInput.builder()
                .usuarioId(usuarioId)
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AtualizarUsuarioOutput> atualizar(Long usuarioId, AtualizarUsuarioInput input) {
        input.setId(usuarioId);
        AtualizarUsuarioOutput output = atualizarUsuarioUsecase.executar(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }


}