package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilPermissao;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "permissao", tags = "Permissão", description = "Manutenção de Permissões")
@RequestMapping("/permissao")
public interface PerfilPermissaoController {

    @ApiOperation(value = "Insere uma nova permissao")
    @PostMapping("/inserir")
    ResponseEntity<InserirPerfilPermissaoOutput> inserir(@RequestBody @Valid InserirPerfilPermissaoInput input);

    @ApiOperation(value = "Atualiza uma permissao")
    @PostMapping("/atualizar")
    ResponseEntity<AtualizarPerfilPermissaoOutput> atualizar(@RequestBody @Valid AtualizarPerfilPermissaoInput input);

    @ApiOperation(value = "Remove uma permissao")
    @DeleteMapping(value = "/{permissaoId}")
    ResponseEntity<Void> remover(@PathVariable @Valid Long permissaoId);

    @ApiOperation(value = "Busca permissoes por perfil")
    @PostMapping("/buscarPermissaoPorPerfil/perfilId={perfilId}")
    ResponseEntity<BuscarPermissoesPorPerfilOutput> buscarPermissoesPorPerfil(@RequestBody @Valid BuscarPermissoesPorPerfilInput input);

}