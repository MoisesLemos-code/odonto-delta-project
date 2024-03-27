package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilUsuario;

import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.perfilUsuario.buscarPerfisDoUsuario.BuscarPerfisDoUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "perfilUsuario", tags = "Perfil Usuário", description = "Manutenção de Perfis do Usuário")
@RequestMapping("/perfilUsuario")
public interface PerfilUsuarioController {


    @ApiOperation(value = "Busca todos os perfis do usuário")
    @GetMapping("/buscarPerfisDoUsuario")
    ResponseEntity<BuscarPerfisDoUsuarioOutput> buscarPerfisDoUsuario(@Valid BuscarPerfisDoUsuarioInput inputData);
}