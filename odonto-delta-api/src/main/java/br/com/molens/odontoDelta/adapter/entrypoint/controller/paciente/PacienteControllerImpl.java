package br.com.molens.odontoDelta.adapter.entrypoint.controller.paciente;

import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.removerPacientePorId.RemoverPacientePorIdInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.removerPacientePorId.RemoverPacientePorIdUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PacienteControllerImpl implements PacienteController {

    private InserirPacienteUsecase inserirPacienteUsecase;
    private BuscarPacientePorIdUsecase buscarPacientePorIdUsecase;
    private BuscaPaginadaPacienteUsecase buscaPaginadaPacienteUsecase;
    private RemoverPacientePorIdUsecase removerPacientePorIdUsecase;
    private AtualizarPacienteUsecase atualizarPacienteUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<InserirPacienteOutput> inserir(InserirPacienteInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirPacienteUsecase.executar(input), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuscarPacientePorIdOutput> buscarPorId(Long pacienteId, Long empresaId) {
        return new ResponseEntity<>(buscarPacientePorIdUsecase.executar(BuscarPacientePorIdInput.builder()
                .pacienteId(pacienteId)
                .empresaId(empresaId)
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BuscaPaginadaPacienteOutput> buscaPaginada(BuscaPaginadaPacienteInput inputData) {
        inputData.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        BuscaPaginadaPacienteOutput output = buscaPaginadaPacienteUsecase.executar(inputData);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remover(Long pacienteId, Long empresaId) {
        removerPacientePorIdUsecase.executar(RemoverPacientePorIdInput.builder()
                .pacienteId(pacienteId)
                .empresaId(empresaId)
                .build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<AtualizarPacienteOutput> atualizar(Long pacienteId, AtualizarPacienteInput input) {
        input.setPacienteId(pacienteId);
        AtualizarPacienteOutput output = atualizarPacienteUsecase.executar(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}