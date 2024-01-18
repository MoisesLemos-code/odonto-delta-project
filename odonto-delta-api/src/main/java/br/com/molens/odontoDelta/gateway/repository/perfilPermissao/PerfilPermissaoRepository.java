package br.com.molens.odontoDelta.gateway.repository.perfilPermissao;

import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissao, Long>, QuerydslPredicateExecutor<PerfilPermissao> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM PerfilPermissao obj WHERE obj.perfil.id = :perfilId")
    List<PerfilPermissao> buscarPorPerfil(@Param("perfilId") Long perfilId);
}
