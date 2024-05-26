package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilUsuario;

import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.atualizarPerfilUsuario.AtualizarPerfilUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "perfilUsuario", tags = "Perfil Usuário", description = "Manutenção de Perfis do Usuário")
@RequestMapping("/perfilUsuario")
public interface PerfilUsuarioController {


    @ApiOperation(value = "Busca todos os perfis do usuário")
    @GetMapping("/buscarPerfisDoUsuario/idUsuario={usuarioId}")
    ResponseEntity<BuscarPerfisDoUsuarioOutput> buscarPerfisDoUsuario(@PathVariable @Valid Long usuarioId, @Valid BuscarPerfisDoUsuarioInput inputData);

    @ApiOperation(value = "Atualiza um perfil de usuário")
    @PutMapping("/atualizar/idUsuario={usuarioId}")
    ResponseEntity<Void> atualizar(@PathVariable @Valid Long usuarioId, @RequestBody @Valid AtualizarPerfilUsuarioInput input);
}