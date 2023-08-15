package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MunicipioDataProviderImpl implements MunicipioDataProvider {

    @Autowired
    private MunicipioRepository repository;

    @Override
    public Optional<Municipio> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Municipio> buscarTodosPorEstado(Long idEstado) {
        return repository.buscarTodosPorEstado(idEstado);
    }
}
