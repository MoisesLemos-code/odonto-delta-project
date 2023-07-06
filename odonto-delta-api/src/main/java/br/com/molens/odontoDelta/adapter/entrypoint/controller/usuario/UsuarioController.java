package br.com.molens.odontoDelta.adapter.entrypoint.controller.usuario;

import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.InserirUsuarioOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "usuario", tags = "Usuario", description = "Manutenção de Usuarios")
@RequestMapping("/usuario")
public interface UsuarioController {

    @ApiOperation(value = "Insere um novo usuario")
    @PostMapping("/inserir")
    ResponseEntity<InserirUsuarioOutput> inserir(@RequestBody @Valid InserirUsuarioInput input);
}