package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfil;

import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "perfil", tags = "Perfil", description = "Manutenção de Perfis")
@RequestMapping("/perfil")
public interface PerfilController {

    @ApiOperation(value = "Insere um novo perfil")
    @PostMapping("/inserir")
    ResponseEntity<InserirPerfilOutput> inserir(@RequestBody @Valid InserirPerfilInput input);

    @ApiOperation(value = "Busca único perfil")
    @GetMapping(value = "/{perfilId}")
    ResponseEntity<BuscarPerfilPorIdOutput> buscarPorId(@PathVariable @Valid Long perfilId);

    @ApiOperation(value = "Busca paginada de perfil")
    @GetMapping
    ResponseEntity<BuscaPaginadaPerfilOutput> buscar(@Valid BuscaPaginadaPerfilInput inputData);

    @ApiOperation(value = "Remove um perfil")
    @DeleteMapping(value = "/{perfilId}")
    ResponseEntity<Void> remover(@PathVariable @Valid Long perfilId);

    @ApiOperation(value = "Atualiza um perfil")
    @PutMapping(value = "{perfilId}")
    ResponseEntity<AtualizarPerfilOutput> atualizar(@PathVariable Long perfilId, @RequestBody @Valid AtualizarPerfilInput input);


}