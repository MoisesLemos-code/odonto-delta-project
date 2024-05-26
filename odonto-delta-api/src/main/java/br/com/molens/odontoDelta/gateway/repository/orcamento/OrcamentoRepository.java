package br.com.molens.odontoDelta.gateway.repository.orcamento;

import br.com.molens.odontoDelta.gateway.entity.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>, QuerydslPredicateExecutor<Orcamento> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Orcamento obj WHERE obj.id = :orcamentoId and obj.empresa.id = :empresaId")
    Optional<Orcamento> buscarPorId(@Param("orcamentoId") Long orcamentoId, @Param("empresaId") Long empresaId);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Orcamento obj WHERE obj.paciente.id = :pacienteId and obj.empresa.id = :empresaId")
    Optional<Orcamento> buscarPorPacienteId(@Param("pacienteId") Long pacienteId, @Param("empresaId") Long empresaId);
}
