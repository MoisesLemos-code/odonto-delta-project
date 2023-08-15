package br.com.molens.odontoDelta.adapter.entrypoint.controller.estado;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.BuscarEstadosOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "estado", tags = "Estado", description = "Manutenção de Estados")
@RequestMapping("/estado")
public interface EstadoController {

    @ApiOperation(value = "Busca de estados")
    @GetMapping(value = "/all")
    ResponseEntity<BuscarEstadosOutput> buscar();

}