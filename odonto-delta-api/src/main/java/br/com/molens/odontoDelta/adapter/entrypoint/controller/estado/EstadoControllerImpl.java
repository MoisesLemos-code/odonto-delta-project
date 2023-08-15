package br.com.molens.odontoDelta.adapter.entrypoint.controller.estado;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.BuscarEstadosOutput;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.BuscarEstadosUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EstadoControllerImpl implements EstadoController {

    private BuscarEstadosUsecase buscarEstadosUsecase;

    @Override
    public ResponseEntity<BuscarEstadosOutput> buscar() {
        BuscarEstadosOutput output = buscarEstadosUsecase.executar();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}