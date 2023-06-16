package br.com.molens.odontoDelta.adapter.entrypoint.controller.paciente;

import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteOuput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "paciente", tags = "Paciente", description = "Manutenção de Pacientes")
@RequestMapping("/paciente")
public interface PacienteController {

    @ApiOperation(value = "Insere um novo paciente")
    @PostMapping("/inserir")
    ResponseEntity<InserirPacienteOuput> inserir(@RequestBody @Valid InserirPacienteInput input);

    @ApiOperation(value = "Busca único Paciente")
    @GetMapping(value = "{id}")
    ResponseEntity<Paciente> buscarPorId(@PathVariable @Valid Long id);

}