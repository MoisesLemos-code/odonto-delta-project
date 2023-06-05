package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


    Optional<UsuarioEntity> findByLogin(String login);

    Optional<UsuarioEntity> findById(Long id);
}
