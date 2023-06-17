package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDataProviderImpl implements UsuarioDataProvider {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void inserir(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return repository.save(usuario);
    }
}
