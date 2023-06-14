package br.com.molens.odontoDelta.gateway.dataprovider.repository.perfil;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

    Optional<PerfilEntity> findById(Long id);
}
