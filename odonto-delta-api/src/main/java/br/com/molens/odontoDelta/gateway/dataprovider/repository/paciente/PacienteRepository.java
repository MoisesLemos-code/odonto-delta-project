package br.com.molens.odontoDelta.gateway.dataprovider.repository.paciente;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM PacienteEntity obj WHERE obj.id = :pacienteId ")
    Optional<PacienteEntity> buscarPorId(@Param("pacienteId") Long pacienteId);

    boolean existsByCnpjCpf(String cpfCnpj);
}
