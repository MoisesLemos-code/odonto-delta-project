package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilPermissao;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaolUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPerfilPermissoesPorPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao.RemoverPerfilPermissaoPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao.RemoverPerfilPermissaoPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PerfilPermissaoControllerImpl implements PerfilPermissaoController {

    private InserirPerfilPermissaoUsecase inserirPerfilPermissaoUsecase;
    private RemoverPerfilPermissaoPorIdUsecase removerPerfilPermissaoPorIdUsecase;
    private BuscarPerfilPermissoesPorPerfilUsecase buscarPermissoesPorPerfilUsecase;
    private AtualizarPerfilPermissaolUsecase atualizarPerfilPermissaolUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<InserirPerfilPermissaoOutput> inserir(InserirPerfilPermissaoInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirPerfilPermissaoUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> atualizar(AtualizarPerfilPermissaoInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        atualizarPerfilPermissaolUsecase.executar(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remover(Long perfilPermissaoId) {
        removerPerfilPermissaoPorIdUsecase.executar(RemoverPerfilPermissaoPorIdInput.builder()
                .perfilPermissaoId(perfilPermissaoId)
                .build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BuscarPermissoesPorPerfilOutput> buscarPerfilPermissoesPorPerfil(Long perfilId) {
        return new ResponseEntity<>(buscarPermissoesPorPerfilUsecase.executar(BuscarPermissoesPorPerfilInput.builder()
                        .perfilId(perfilId)
                        .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }

}