package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioDataProvider {
    Optional<Usuario> buscarUsuarioPorLogin(String login);

    Optional<Usuario> buscarPorId(Long id);
}
