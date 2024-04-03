package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.PerfilUsuario;

import java.util.List;

public interface PerfilUsuarioDataProvider {

    List<PerfilUsuario> buscarPorUsuario(Long usuarioId);

    void inserir(PerfilUsuario perfil);

    void remover(Long id);

    void inserirTodos(List<PerfilUsuario> perfilUsuarioList);

    void removerTodos(List<PerfilUsuario> perfilUsuarioList);


}
