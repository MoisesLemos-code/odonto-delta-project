package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.domain.entity.Usuario;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.UsuarioConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDataProviderImpl implements UsuarioDataProvider {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioConverter converter;

    @Override
    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        return repository.findByLogin(login).map(converter::to);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id).map(converter::to);
    }

    @Override
    public void inserir(Usuario usuario) {
        repository.save(converter.from(usuario));
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        UsuarioEntity usuarioEntity = repository.save(converter.from(usuario));
        return converter.to(usuarioEntity);
    }
}
