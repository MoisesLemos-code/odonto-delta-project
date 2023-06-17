package br.com.molens.odontoDelta.gateway.dataprovider.repository.perfil;

import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PerfilDataProviderImpl implements PerfilDataProvider {

    @Autowired
    private PerfilRepository repository;

    @Override
    public Optional<Perfil> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void inserir(Perfil perfil) {
        repository.save(perfil);
    }

    @Override
    public Perfil atualizar(Perfil perfil) {
        return repository.save(perfil);
    }

    @Override
    public void remover(Perfil perfil) {
        repository.deleteById(perfil.getId());
    }
}
