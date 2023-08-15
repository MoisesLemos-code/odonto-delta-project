package br.com.molens.odontoDelta.adapter.entrypoint.controller.municipio;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoInput;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "municipio", tags = "Municipio", description = "Manutenção de Minicipios")
@RequestMapping("/municipio")
public interface MunicipioController {

    @ApiOperation(value = "Busca de municipios")
    @GetMapping(value = "/all/estado={idEstado}")
    ResponseEntity<BuscaDeCidadesPorEstadoOutput> buscar(@PathVariable @Valid Long idEstado);

}