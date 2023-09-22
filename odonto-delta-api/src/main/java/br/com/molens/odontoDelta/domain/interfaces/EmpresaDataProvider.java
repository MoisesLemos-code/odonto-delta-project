package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.Empresa;

import java.util.Optional;

public interface EmpresaDataProvider {

    Optional<Empresa> buscarPorId(Long id);

    void inserir(Empresa empresa);

    Empresa atualizar(Empresa empresa);
}
