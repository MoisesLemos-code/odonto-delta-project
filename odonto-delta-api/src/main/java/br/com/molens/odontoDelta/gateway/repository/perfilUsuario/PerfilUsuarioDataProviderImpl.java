package br.com.molens.odontoDelta.gateway.repository.perfilUsuario;

import br.com.molens.odontoDelta.domain.interfaces.PerfilUsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.entity.PerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerfilUsuarioDataProviderImpl implements PerfilUsuarioDataProvider {

    @Autowired
    private PerfilUsuarioRepository repository;

    @Override
    public List<PerfilUsuario> buscarPorUsuario(Long usuarioId) {
        return repository.buscarPorUsuario(usuarioId);
    }

    @Override
    public void inserir(PerfilUsuario perfil) {
        repository.save(perfil);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
