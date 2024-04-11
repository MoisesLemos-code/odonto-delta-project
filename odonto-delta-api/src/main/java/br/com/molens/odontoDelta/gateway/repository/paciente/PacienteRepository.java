package br.com.molens.odontoDelta.gateway.repository.paciente;

import br.com.molens.odontoDelta.gateway.entity.Paciente;
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

    boolean existsByCnpjCpfAndEmpresaId(String cpfCnpj, Long empresaId);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Paciente obj WHERE obj.cnpjCpf = :cnpjCpf and obj.empresa.id = :empresaId")
    Optional<Paciente> buscarPorCnpjCpfEmpresa(@Param("cnpjCpf") String cnpjCpf, @Param("empresaId") Long empresaId);
}
