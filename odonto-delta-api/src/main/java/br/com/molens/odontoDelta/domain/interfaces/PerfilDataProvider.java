package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;

import java.util.Optional;

public interface PerfilDataProvider {

    Optional<Perfil> buscarPorId(Long id);

    void inserir(Perfil perfil);

    Perfil atualizar(Perfil perfil);

    void remover(Perfil perfil);
}
