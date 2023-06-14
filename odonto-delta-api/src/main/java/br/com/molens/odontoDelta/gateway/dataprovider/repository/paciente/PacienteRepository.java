package br.com.molens.odontoDelta.gateway.dataprovider.repository.paciente;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findById(Long id);
}
