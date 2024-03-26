package br.com.molens.odontoDelta.adapter.entrypoint.controller.usuario;

import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado.BuscarUsuariosPaginadoInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado.BuscarUsuariosPaginadosOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado.BuscarUsuariosPaginadosUsecase;
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
    private BuscarUsuariosPaginadosUsecase buscarUsuariosPaginadosUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<InserirUsuarioOutput> inserir(InserirUsuarioInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirUsuarioUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuscarUsuarioPorIdOutput> buscarPorId(Long usuarioId) {
        return new ResponseEntity<>(buscarUsuarioPorIdUsecase.executar(BuscarUsuarioPorIdInput.builder()
                .usuarioId(usuarioId)
                .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AtualizarUsuarioOutput> atualizar(Long usuarioId, AtualizarUsuarioInput input) {
        input.setId(usuarioId);
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        AtualizarUsuarioOutput output = atualizarUsuarioUsecase.executar(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuscarUsuariosPaginadosOutput> buscaPaginada(Integer page, Integer size, String nome, String sort, String direction) {
        return new ResponseEntity<>(buscarUsuariosPaginadosUsecase.executar(BuscarUsuariosPaginadoInput.builder()
                        .empresaId(userDataUsecase.executar().getEmpresa().getId())
                        .nome(nome)
                        .page(page)
                        .size(size)
                        .sort(sort)
                        .direction(direction)
                .build()), HttpStatus.OK);
    }


}