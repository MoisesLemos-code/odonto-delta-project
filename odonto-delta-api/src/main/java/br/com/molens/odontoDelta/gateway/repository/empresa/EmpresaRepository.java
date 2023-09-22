package br.com.molens.odontoDelta.gateway.repository.empresa;

import br.com.molens.odontoDelta.gateway.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findById(Long id);
}
