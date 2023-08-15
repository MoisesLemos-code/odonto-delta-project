package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado;

import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado.converter.BuscaDeCidadesPorEstadoOutputConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuscaDeCidadesPorEstadoUsecase {

    private MunicipioDataProvider municipioDataProvider;
    private BuscaDeCidadesPorEstadoOutputConverter outputConverter;

    public BuscaDeCidadesPorEstadoOutput executar(BuscaDeCidadesPorEstadoInput input){
        return outputConverter.to(buscarMunicipios(input));
    }

    private List<Municipio> buscarMunicipios(BuscaDeCidadesPorEstadoInput input) {
        return municipioDataProvider.buscarTodosPorEstado(input.getIdEstado());
    }
}
