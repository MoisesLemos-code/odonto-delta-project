package br.com.molens.odontoDelta.gateway.dataprovider.repository.perfil;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findById(Long id);
}
