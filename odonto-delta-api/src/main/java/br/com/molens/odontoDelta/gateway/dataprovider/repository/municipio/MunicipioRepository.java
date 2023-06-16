package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Long> {

    Optional<MunicipioEntity> findById(Long id);
}
