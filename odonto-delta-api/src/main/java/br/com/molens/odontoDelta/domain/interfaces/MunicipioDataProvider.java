package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;

import java.util.Optional;

public interface MunicipioDataProvider {

    Optional<Municipio> buscarPorId(Long id);

}
