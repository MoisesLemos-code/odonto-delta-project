package br.com.molens.odontoDelta.gateway.repository.perfilUsuario;

import br.com.molens.odontoDelta.gateway.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long>, QuerydslPredicateExecutor<PerfilUsuario> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM PerfilUsuario obj WHERE obj.usuario.id = :usuarioId")
    List<PerfilUsuario> buscarPorUsuario(@Param("usuarioId") Long usuarioId);
}
