package br.com.molens.odontoDelta.adapter.entrypoint.controller.fichaPaciente;

import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.atualizarFichaPaciente.AtualizarFichaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.buscarFichaPacientePorPaciente.BuscarFichaPacientePorPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "fichaPaciente", tags = "Ficha Paciente", description = "Manutenção de Ficha de Pacientes")
@RequestMapping("/fichaPaciente")
public interface FichaPacienteController {

    @ApiOperation(value = "Insere uma nova ficha de paciente")
    @PostMapping("/inserir")
    ResponseEntity<InserirFichaPacienteOutput> inserir(@RequestBody @Valid InserirFichaPacienteInput input);

    @ApiOperation(value = "Busca única ficha pelo paciente")
    @GetMapping(value = "/buscarPorPacienteId/{pacienteId}")
    ResponseEntity<BuscarFichaPacientePorPacienteOutput> buscarPorPacienteId(@PathVariable @Valid Long pacienteId);

    @ApiOperation(value = "Atualiza uma ficha de paciente")
    @PutMapping(value = "/atualizar/{fichaId}")
    ResponseEntity<AtualizarFichaPacienteOutput> atualizar(@PathVariable Long fichaId, @RequestBody @Valid AtualizarFichaPacienteInput input);


}