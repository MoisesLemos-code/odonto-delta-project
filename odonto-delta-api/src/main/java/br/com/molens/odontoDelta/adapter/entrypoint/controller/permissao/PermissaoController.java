package br.com.molens.odontoDelta.adapter.entrypoint.controller.permissao;

import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "permissao", tags = "Permissão", description = "Manutenção de Permissões")
@RequestMapping("/permissao")
public interface PermissaoController {


    @ApiOperation(value = "Busca todas permissões de um usuário")
    @GetMapping("/buscarTodasPermissoesPorUsuario")
    ResponseEntity<BuscarPermissoesUsuarioOutput> buscar(@Valid BuscarPermissoesUsuarioInput inputData);
}