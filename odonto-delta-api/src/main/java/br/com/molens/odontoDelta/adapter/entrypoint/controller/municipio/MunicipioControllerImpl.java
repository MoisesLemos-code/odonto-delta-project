package br.com.molens.odontoDelta.adapter.entrypoint.controller.municipio;

import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoInput;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoOutput;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.BuscaDeCidadesPorEstadoUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MunicipioControllerImpl implements MunicipioController {

    private BuscaDeCidadesPorEstadoUsecase buscaDeCidadesPorEstadoUsecase;

    @Override
    public ResponseEntity<BuscaDeCidadesPorEstadoOutput> buscar(Long idEstado) {
        BuscaDeCidadesPorEstadoOutput output = buscaDeCidadesPorEstadoUsecase.executar(BuscaDeCidadesPorEstadoInput.builder()
                        .idEstado(idEstado)
                .build());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }


}