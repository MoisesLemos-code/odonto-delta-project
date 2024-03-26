package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.gateway.entity.Usuario;

import java.util.Optional;

public interface UsuarioDataProvider {
    Optional<Usuario> buscarUsuarioPorLogin(String login);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorIdEmpresaId(Long usuarioId, Long empresaId);

    Long inserir(Usuario usuario);

    Usuario atualizar(Usuario usuario);

    void remover(Long id);

    ListaPaginada<Usuario> buscarUsuariosPaginado(Usuario.Filtro filtro);
}
