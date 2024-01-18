package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilPermissao;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaolUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao.RemoverPerfilPermissaoPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.removerPerfilPermissao.RemoverPerfilPermissaoPorIdUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PerfilPermissaoControllerImpl implements PerfilPermissaoController {

    private InserirPerfilPermissaoUsecase inserirPerfilPermissaoUsecase;
    private RemoverPerfilPermissaoPorIdUsecase removerPerfilPermissaoPorIdUsecase;
    private BuscarPermissoesPorPerfilUsecase buscarPermissoesPorPerfilUsecase;
    private AtualizarPerfilPermissaolUsecase atualizarPerfilPermissaolUsecase;

    @Override
    public ResponseEntity<InserirPerfilPermissaoOutput> inserir(InserirPerfilPermissaoInput input) {
        return new ResponseEntity<>(inserirPerfilPermissaoUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AtualizarPerfilPermissaoOutput> atualizar(AtualizarPerfilPermissaoInput input) {
        return new ResponseEntity<>(atualizarPerfilPermissaolUsecase.executar(input), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remover(Long permissaoId) {
        removerPerfilPermissaoPorIdUsecase.executar(RemoverPerfilPermissaoPorIdInput.builder()
                .permissaoId(permissaoId)
                .build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BuscarPermissoesPorPerfilOutput> buscarPermissoesPorPerfil(BuscarPermissoesPorPerfilInput input) {
        return new ResponseEntity<>(buscarPermissoesPorPerfilUsecase.executar(input), HttpStatus.OK);
    }

}