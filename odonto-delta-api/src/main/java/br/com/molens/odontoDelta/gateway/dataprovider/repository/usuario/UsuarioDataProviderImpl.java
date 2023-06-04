package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.domain.entity.Usuario;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.UsuarioConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDataProviderImpl implements UsuarioDataProvider {

    @Autowired
    private UsuarioReadOnlyRepository usuarioReadOnlyRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Override
    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        return usuarioReadOnlyRepository.findByLogin(login).map(usuarioConverter::to);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioReadOnlyRepository.findById(id).map(usuarioConverter::to);
    }
}
