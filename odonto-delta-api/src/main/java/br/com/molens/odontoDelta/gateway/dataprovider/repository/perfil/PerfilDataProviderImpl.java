package br.com.molens.odontoDelta.gateway.dataprovider.repository.perfil;

import br.com.molens.odontoDelta.domain.entity.Perfil;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.PerfilConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PerfilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PerfilDataProviderImpl implements PerfilDataProvider {

    @Autowired
    private PerfilRepository repository;

    @Autowired
    private PerfilConverter converter;

    @Override
    public Optional<Perfil> buscarPorId(Long id) {
        return repository.findById(id).map(converter::to);
    }

    @Override
    public void inserir(Perfil perfil) {
        repository.save(converter.from(perfil));
    }

    @Override
    public Perfil atualizar(Perfil perfil) {
        PerfilEntity perfilEntity = repository.save(converter.from(perfil));
        return converter.to(perfilEntity);
    }

    @Override
    public void remover(Perfil perfil) {
        repository.deleteById(perfil.getId());
    }
}
