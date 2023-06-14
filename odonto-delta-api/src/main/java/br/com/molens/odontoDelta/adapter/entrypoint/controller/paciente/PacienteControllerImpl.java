package br.com.molens.odontoDelta.adapter.entrypoint.controller.paciente;

import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PacienteControllerImpl implements PacienteController {

    private InserirPacienteUsecase inserirPacienteUsecase;

    @Override
    public ResponseEntity<Paciente> inserir(InserirPacienteInput input) {
        return new ResponseEntity<>(inserirPacienteUsecase.executar(input), HttpStatus.CREATED);
    }

}