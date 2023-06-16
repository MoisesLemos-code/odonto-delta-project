package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.Municipio;

import java.util.Optional;

public interface MunicipioDataProvider {

    Optional<Municipio> buscarPorId(Long id);

}
