package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.Permissao;

import java.util.List;
import java.util.Optional;

public interface PermissaoDataProvider {

    List<Permissao> buscarTodas();

    Optional<Permissao> buscarPorId(Long id);
}
