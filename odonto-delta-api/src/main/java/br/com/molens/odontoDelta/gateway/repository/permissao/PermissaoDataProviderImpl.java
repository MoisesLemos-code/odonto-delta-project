package br.com.molens.odontoDelta.gateway.repository.permissao;

import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PermissaoDataProviderImpl implements PermissaoDataProvider {

    @Autowired
    private PermissaoRepository repository;

    @Override
    public List<Permissao> buscarTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Permissao> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
