package br.com.molens.odontoDelta.adapter.entrypoint.controller.permissao;

import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.AtualizarPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.BuscarPerfilPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilInput;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.InserirPerfilOutput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes.BuscarPermissoesOutput;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(value = "permissao", tags = "Permissão", description = "Manutenção de Permissões")
@RequestMapping("/permissao")
public interface PermissaoController {


    @ApiOperation(value = "Busca todas permissões")
    @GetMapping("/buscarTodas")
    ResponseEntity<BuscarPermissoesOutput> buscar(@Valid BuscarPermissoesInput inputData);
}