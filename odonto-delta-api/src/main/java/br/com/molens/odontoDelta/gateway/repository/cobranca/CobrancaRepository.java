package br.com.molens.odontoDelta.gateway.repository.cobranca;

import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long>, QuerydslPredicateExecutor<Cobranca> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Cobranca obj WHERE obj.id = :cobrancaId and obj.empresa.id = :empresaId")
    Optional<Cobranca> buscarPorId(@Param("cobrancaId") Long cobrancaId, @Param("empresaId") Long empresaId);

    @Transactional(readOnly = true)
    @Query("SELECT max(obj.id) FROM Cobranca obj WHERE obj.empresa.id = :empresaId")
    Integer buscarUltimoId(@Param("empresaId") Long empresaId);
}
