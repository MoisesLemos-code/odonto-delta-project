package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.Empresa;

import java.util.Optional;

public interface EmpresaDataProvider {

    Optional<Empresa> buscarPorId(Long id);

    void inserir(Empresa usuario);

    Empresa atualizar(Empresa usuario);
}
