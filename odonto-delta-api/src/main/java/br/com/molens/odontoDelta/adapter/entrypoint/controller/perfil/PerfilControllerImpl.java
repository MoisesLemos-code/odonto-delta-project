package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfil;

import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilUsecase;
import br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId.RemoverPerfilPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.removerPerfilPorId.RemoverPerfilPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PerfilControllerImpl implements PerfilController {

    private InserirPerfilUsecase inserirPerfilUsecase;
    private BuscarPerfilPorIdUsecase buscarPerfilPorIdUsecase;
    private BuscaPaginadaPerfilUsecase buscaPaginadaPerfilUsecase;
    private RemoverPerfilPorIdUsecase removerPerfilPorIdUsecase;
    private AtualizarPerfilUsecase atualizarPerfilUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<InserirPerfilOutput> inserir(InserirPerfilInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirPerfilUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuscarPerfilPorIdOutput> buscarPorId(Long perfilId) {
        return new ResponseEntity<>(buscarPerfilPorIdUsecase.executar(BuscarPerfilPorIdInput.builder()
                .perfilId(perfilId)
                .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuscaPaginadaPerfilOutput> buscar(BuscaPaginadaPerfilInput inputData) {
        inputData.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        BuscaPaginadaPerfilOutput output = buscaPaginadaPerfilUsecase.executar(inputData);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remover(Long perfilId) {
        removerPerfilPorIdUsecase.executar(RemoverPerfilPorIdInput.builder()
                .perfilId(perfilId)
                .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<AtualizarPerfilOutput> atualizar(Long perfilId, AtualizarPerfilInput input) {
        input.setId(perfilId);
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        AtualizarPerfilOutput output = atualizarPerfilUsecase.executar(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}