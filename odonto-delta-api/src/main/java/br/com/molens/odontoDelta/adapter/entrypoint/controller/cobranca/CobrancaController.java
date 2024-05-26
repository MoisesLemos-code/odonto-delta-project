package br.com.molens.odontoDelta.adapter.entrypoint.controller.cobranca;

import br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca.AtualizarCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.BuscaPaginadaCobrancaOutput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscarCobrancaPorId.BuscarCobrancaPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaInput;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaOutput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
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

@Api(value = "cobranca", tags = "Cobrança", description = "Manutenção de Cobranças")
@RequestMapping("/cobranca")
public interface CobrancaController {

    @ApiOperation(value = "Insere uma nova cobrança")
    @PostMapping("/cobranca/inserir")
    ResponseEntity<InserirCobrancaOutput> inserir(@RequestBody @Valid InserirCobrancaInput input);

    @ApiOperation(value = "Busca única Cobrança")
    @GetMapping(value = "/buscarPorId/{cobrancaId}")
    ResponseEntity<BuscarCobrancaPorIdOutput> buscarPorId(@PathVariable @Valid Long cobrancaId);

    @ApiOperation(value = "Busca paginada de Cobrança")
    @GetMapping
    ResponseEntity<BuscaPaginadaCobrancaOutput> buscaPaginada(@Valid BuscaPaginadaCobrancaInput inputData);

    @ApiOperation(value = "Remove uma Cobrança")
    @DeleteMapping(value = "/deletar/{cobrancaId}")
    ResponseEntity<Void> remover(@PathVariable @Valid Long cobrancaId);

    @ApiOperation(value = "Atualiza uma Cobrança")
    @PutMapping(value = "/atualizar/{cobrancaId}")
    ResponseEntity<Cobranca> atualizar(@PathVariable Long cobrancaId, @RequestBody @Valid AtualizarCobrancaInput input);

}