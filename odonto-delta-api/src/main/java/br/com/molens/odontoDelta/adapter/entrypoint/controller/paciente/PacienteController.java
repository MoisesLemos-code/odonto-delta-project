package br.com.molens.odontoDelta.adapter.entrypoint.controller.paciente;

import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.InserirPacienteOutput;
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
    ResponseEntity<InserirPacienteOutput> inserir(@RequestBody @Valid InserirPacienteInput input);

    @ApiOperation(value = "Busca único Paciente")
    @GetMapping(value = "{empresaId}/{pacienteId}")
    ResponseEntity<BuscarPacientePorIdOutput> buscarPorId(@PathVariable @Valid Long pacienteId, @PathVariable @Valid Long empresaId);

    @ApiOperation(value = "Busca paginada de Paciente")
    @GetMapping
    ResponseEntity<BuscaPaginadaPacienteOutput> buscaPaginada(@Valid BuscaPaginadaPacienteInput inputData);

    @ApiOperation(value = "Remove um Paciente")
    @DeleteMapping(value = "{empresaId}/{pacienteId}")
    ResponseEntity<Void> remover(@PathVariable @Valid Long pacienteId, @PathVariable @Valid Long empresaId);

    @ApiOperation(value = "Atualiza um Paciente")
    @PutMapping(value = "{pacienteId}")
    ResponseEntity<AtualizarPacienteOutput> atualizar(@PathVariable Long pacienteId, @RequestBody @Valid AtualizarPacienteInput input);


}