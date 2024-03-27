package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilUsuario;

import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PerfilUsuarioControllerImpl implements PerfilUsuarioController{

    private BuscarPerfisDoUsuarioUsecase buscarPerfisDoUsuarioUsecase;

    private UserDataUsecase userDataUsecase;
    @Override
    public ResponseEntity<BuscarPerfisDoUsuarioOutput> buscarPerfisDoUsuario(BuscarPerfisDoUsuarioInput inputData) {
        return  new ResponseEntity<>(buscarPerfisDoUsuarioUsecase.executar(BuscarPerfisDoUsuarioInput.builder()
                        .usuarioId(userDataUsecase.executar().getId())
                        .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }
}
