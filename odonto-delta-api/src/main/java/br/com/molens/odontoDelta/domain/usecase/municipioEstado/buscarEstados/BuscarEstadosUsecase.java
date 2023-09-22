package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados;

import br.com.molens.odontoDelta.domain.interfaces.EstadoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados.converter.BuscarEstadosOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Estado;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuscarEstadosUsecase {

    private EstadoDataProvider estadoDataProvider;
    private BuscarEstadosOutputConverter outputConverter;

    public BuscarEstadosOutput executar(){
        return outputConverter.to(buscarTodos());
    }

    private List<Estado> buscarTodos() {
        return estadoDataProvider.buscarTodos();
    }
}
