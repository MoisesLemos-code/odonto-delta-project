package br.com.molens.odontoDelta.adapter.entrypoint.controller.permissao;

import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PermissaoControllerImpl implements PermissaoController {

    private BuscarPermissoesUsecase buscarPermissoesUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<List<Permissao>> buscar(BuscarPermissoesInput inputData) {
        inputData.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(buscarPermissoesUsecase.executar(inputData), HttpStatus.OK);
    }
}