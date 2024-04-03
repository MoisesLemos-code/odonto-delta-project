package br.com.molens.odontoDelta.gateway.repository.permissao;

import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>, QuerydslPredicateExecutor<Permissao> {
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Permissao obj " +
            "LEFT JOIN PerfilUsuario perfilUsuario ON perfilUsuario.usuario.id = :usuarioId " +
            "LEFT JOIN PerfilPermissao perfilPermissao ON perfilPermissao.perfil.id = perfilUsuario.perfil.id AND obj.id = perfilPermissao.permissao.id " +
            "WHERE perfilPermissao.ativo is true ")
    List<Permissao> buscarTodasPermissoesPorUsuario(@Param("usuarioId") Long usuarioId);

}
