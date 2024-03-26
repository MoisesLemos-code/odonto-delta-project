package br.com.molens.odontoDelta.gateway.repository.usuario;

import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {


    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findById(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Usuario obj WHERE obj.id = :usuarioId and obj.empresa.id = :empresaId")
    Optional<Usuario> buscarPorIdEmpresaId(@Param("usuarioId") Long usuarioId, @Param("empresaId") Long empresaId);
}
