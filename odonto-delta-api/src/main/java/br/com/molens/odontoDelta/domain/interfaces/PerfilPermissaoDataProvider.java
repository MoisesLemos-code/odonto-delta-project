package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;

import java.util.List;
import java.util.Optional;

public interface PerfilPermissaoDataProvider {

    Long inserir(PerfilPermissao perfilPermissaoId);

    void inserirTodos(List<PerfilPermissao> perfilPermissaoList);

    PerfilPermissao atualizar(PerfilPermissao PerfilPermissao);

    Optional<PerfilPermissao> buscarPorId(Long permissaoId);

    void remover(Long id);

    List<PerfilPermissao> buscarPorPerfil(Long perfilId);

    List<PerfilPermissao> buscarPorPermissaoEhPerfil(Long permissaoId, Long perfilId);
}
