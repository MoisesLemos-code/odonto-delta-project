package br.com.molens.odontoDelta.gateway.repository.perfilPermissao;

import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PerfilPermissaoDataProviderImpl implements PerfilPermissaoDataProvider {

    @Autowired
    private PerfilPermissaoRepository repository;

    @Override
    public Long inserir(PerfilPermissao perfilPermissao) {
        perfilPermissao.setId(null);
        PerfilPermissao perfilPermissaoEntity = repository.save(perfilPermissao);
        return perfilPermissaoEntity.getId();
    }

    @Override
    public PerfilPermissao atualizar(PerfilPermissao perfilPermissao) {
        return repository.save(perfilPermissao);
    }

    @Override
    public Optional<PerfilPermissao> buscarPorId(Long permissaoId) {
        return repository.findById(permissaoId);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<PerfilPermissao> buscarPorPerfil(Long perfilId) {
        return repository.buscarPorPerfil(perfilId);
    }

    @Override
    public List<PerfilPermissao> buscarPorPermissaoEhPerfil(Long permissaoId, Long perfilId) {
        return repository.buscarPorPermissaoEhPerfil(permissaoId, perfilId);
    }
}
