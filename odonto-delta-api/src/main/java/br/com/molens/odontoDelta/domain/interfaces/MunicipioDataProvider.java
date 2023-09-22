package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.Municipio;
import java.util.List;
import java.util.Optional;

public interface MunicipioDataProvider {

    Optional<Municipio> buscarPorId(Long id);

    List<Municipio> buscarTodosPorEstado(Long idEstado);

}
