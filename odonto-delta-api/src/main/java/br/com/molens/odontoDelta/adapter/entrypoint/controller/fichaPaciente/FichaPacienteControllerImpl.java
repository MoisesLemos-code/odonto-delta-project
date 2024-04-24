package br.com.molens.odontoDelta.adapter.entrypoint.controller.fichaPaciente;

import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.BuscarFichaPacientePorPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.BuscarFichaPacientePorPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.BuscarFichaPacientePorPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FichaPacienteControllerImpl implements FichaPacienteController {

    private InserirFichaPacienteUsecase inserirFichaPacienteUsecase;
    private BuscarFichaPacientePorPacienteUsecase buscarFichaPacientePorPacienteUsecase;
    private AtualizarFichaPacienteUsecase atualizarFichaPacienteUsecase;

    private UserDataUsecase userDataUsecase;

    @Override
    public ResponseEntity<InserirFichaPacienteOutput> inserir(InserirFichaPacienteInput input) {
        input.setEmpresaId(userDataUsecase.executar().getEmpresa().getId());
        return new ResponseEntity<>(inserirFichaPacienteUsecase.executar(input), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<BuscarFichaPacientePorPacienteOutput> buscarPorPacienteId(Long pacienteId) {
        return new ResponseEntity<>(buscarFichaPacientePorPacienteUsecase.executar(BuscarFichaPacientePorPacienteInput.builder()
                .pacienteId(pacienteId)
                .empresaId(userDataUsecase.executar().getEmpresa().getId())
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AtualizarFichaPacienteOutput> atualizar(Long fichaId, AtualizarFichaPacienteInput input) {
        input.setId(fichaId);
        return new ResponseEntity<>(atualizarFichaPacienteUsecase.executar(input), HttpStatus.OK);
    }


}