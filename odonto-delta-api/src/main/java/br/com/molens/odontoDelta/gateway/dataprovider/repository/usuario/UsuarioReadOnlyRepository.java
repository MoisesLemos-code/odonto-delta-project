package br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.UsuarioEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UsuarioReadOnlyRepository extends Repository<UsuarioEntity, Long> {


    Optional<UsuarioEntity> findByLogin(String login);

    Optional<UsuarioEntity> findById(Long id);
}
