package br.com.molens.odontoDelta.gateway.repository.estado;

import br.com.molens.odontoDelta.domain.interfaces.EstadoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Estado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDataProviderImpl implements EstadoDataProvider {

    @Autowired
    private EstadoRepository repository;

    @Override
    public List<Estado> buscarTodos() {
        return repository.buscarTodos();
    }
}
