package br.com.molens.odontoDelta.adapter.entrypoint.controller.permissao;

import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PermissaoControllerImpl implements PermissaoController {

    private BuscarPermissoesUsuarioUsecase buscarPermissoesUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<BuscarPermissoesUsuarioOutput> buscar(BuscarPermissoesUsuarioInput inputData) {
        inputData.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        inputData.setUsuarioId(userDataUsecase.executar().getId());
        return new ResponseEntity<>(buscarPermissoesUsecase.executar(inputData), HttpStatus.OK);
    }
}