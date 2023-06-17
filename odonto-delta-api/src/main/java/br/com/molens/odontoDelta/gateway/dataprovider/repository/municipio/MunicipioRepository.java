package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    Optional<Municipio> findById(Long id);
}
