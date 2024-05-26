package br.com.molens.odontoDelta.adapter.entrypoint.controller.cobranca;

import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.AtualizarCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.AtualizarCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaOutput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaOutput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaUsecase;
import br.com.molens.odontoDelta.domain.usecase.cobranca.removerCobrancaPorId.RemoverCobrancaPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.removerCobrancaPorId.RemoverCobrancaPorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CobrancaControllerImpl implements CobrancaController {

    private InserirCobrancaUsecase inserirCobrancaUsecase;
    private BuscarCobrancaPorIdUsecase buscarCobrancaPorIdUsecase;
    private RemoverCobrancaPorIdUsecase removerCobrancaPorIdUsecase;
    private AtualizarCobrancaUsecase atualizarCobrancaUsecase;
    private BuscaPaginadaCobrancaUsecase buscaPaginadaCobrancaUsecase;

    private UserDataUsecase userDataUsecase;


    @Override
    public ResponseEntity<InserirCobrancaOutput> inserir(InserirCobrancaInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirCobrancaUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuscarCobrancaPorIdOutput> buscarPorId(Long cobrancaId) {
        return new ResponseEntity<>(buscarCobrancaPorIdUsecase.executar(BuscarCobrancaPorIdInput.builder()
                .cobrancaId(cobrancaId)
                .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuscaPaginadaCobrancaOutput> buscaPaginada(BuscaPaginadaCobrancaInput inputData) {
        inputData.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        BuscaPaginadaCobrancaOutput output = buscaPaginadaCobrancaUsecase.executar(inputData);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remover(Long cobrancaId) {
        removerCobrancaPorIdUsecase.executar(RemoverCobrancaPorIdInput.builder()
                .cobrancaId(cobrancaId)
                .build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Cobranca> atualizar(Long cobrancaId, AtualizarCobrancaInput input) {
        input.setPacienteId(cobrancaId);
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        Cobranca output = atualizarCobrancaUsecase.executar(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}