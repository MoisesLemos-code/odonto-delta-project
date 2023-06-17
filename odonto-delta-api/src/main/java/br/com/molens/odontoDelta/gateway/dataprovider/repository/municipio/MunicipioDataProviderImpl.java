package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MunicipioDataProviderImpl implements MunicipioDataProvider {

    @Autowired
    private MunicipioRepository repository;

    @Override
    public Optional<Municipio> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
