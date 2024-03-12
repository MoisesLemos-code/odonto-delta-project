package br.com.molens.odontoDelta.adapter.entrypoint.controller.perfilPermissao;

import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao.AtualizarPerfilPermissaoOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil.BuscarPermissoesPorPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoInput;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao.InserirPerfilPermissaoOutput;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(value = "permissao", tags = "Permissão", description = "Manutenção de Permissões")
@RequestMapping("/perfilPermissao")
public interface PerfilPermissaoController {

    @ApiOperation(value = "Insere uma nova permissao")
    @PostMapping("/inserir")
    ResponseEntity<InserirPerfilPermissaoOutput> inserir(@RequestBody @Valid InserirPerfilPermissaoInput input);

    @ApiOperation(value = "Atualiza uma permissao")
    @PostMapping("/atualizar")
    ResponseEntity<AtualizarPerfilPermissaoOutput> atualizar(@RequestBody @Valid AtualizarPerfilPermissaoInput input);

    @ApiOperation(value = "Remove uma permissao")
    @DeleteMapping(value = "/{perfilPermissaoId}")
    ResponseEntity<Void> remover(@PathVariable @Valid Long perfilPermissaoId);

    @ApiOperation(value = "Busca permissoes por perfil")
    @GetMapping("/buscarPerfilPermissaoPorPerfil/perfilId={perfilId}")
    ResponseEntity<List<PerfilPermissao>> buscarPerfilPermissoesPorPerfil(@PathVariable Long perfilId);

}