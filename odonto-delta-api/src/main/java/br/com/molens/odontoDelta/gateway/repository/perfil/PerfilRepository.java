package br.com.molens.odontoDelta.gateway.repository.perfil;

import br.com.molens.odontoDelta.gateway.entity.Perfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>, QuerydslPredicateExecutor<Perfil> {

    Optional<Perfil> findById(Long id);
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Perfil obj WHERE obj.id = :perfilId and obj.empresa.id = :empresaId")
    Optional<Perfil> buscarPorId(@Param("perfilId") Long perfilId, @Param("empresaId") Long empresaId);

    boolean existsByNomeAndEmpresa(String nome, Long empresaId);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Perfil obj WHERE obj.nome = :nome and obj.empresa.id = :empresaId")
    Optional<Perfil> buscarPorNomeEmpresa(@Param("nome") String nome, @Param("empresaId") Long empresaId);
}
