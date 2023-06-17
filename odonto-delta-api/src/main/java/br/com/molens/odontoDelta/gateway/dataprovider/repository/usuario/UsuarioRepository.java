package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findById(Long id);
}
