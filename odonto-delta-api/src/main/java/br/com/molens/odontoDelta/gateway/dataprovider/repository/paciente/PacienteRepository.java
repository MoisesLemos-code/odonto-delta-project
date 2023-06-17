package br.com.molens.odontoDelta.gateway.dataprovider.repository.paciente;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, QuerydslPredicateExecutor<Paciente> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Paciente obj WHERE obj.id = :pacienteId and obj.empresa.id = :empresaId")
    Optional<Paciente> buscarPorId(@Param("pacienteId") Long pacienteId, @Param("empresaId") Long empresaId);

    boolean existsByCnpjCpf(String cpfCnpj);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Paciente obj WHERE obj.cnpjCpf = :cnpjCpf")
    Optional<Paciente> buscarPorCnpjCpf(@Param("cnpjCpf") String cnpjCpf);
}
