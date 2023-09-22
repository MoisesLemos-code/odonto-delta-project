package br.com.molens.odontoDelta.adapter.entrypoint.controller.usuario;

import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "usuario", tags = "Usuario", description = "Manutenção de Usuarios")
@RequestMapping("/usuario")
public interface UsuarioController {

    @ApiOperation(value = "Insere um novo usuario")
    @PostMapping("/inserir")
    ResponseEntity<InserirUsuarioOutput> inserir(@RequestBody @Valid InserirUsuarioInput input);

    @ApiOperation(value = "Busca único usuario")
    @GetMapping(value = "/{usuarioId}")
    ResponseEntity<BuscarUsuarioPorIdOutput> buscarPorId(@PathVariable @Valid Long usuarioId);

    @ApiOperation(value = "Atualiza um Usuario")
    @PutMapping(value = "{usuarioId}")
    ResponseEntity<AtualizarUsuarioOutput> atualizar(@PathVariable Long usuarioId, @RequestBody @Valid AtualizarUsuarioInput input);


}