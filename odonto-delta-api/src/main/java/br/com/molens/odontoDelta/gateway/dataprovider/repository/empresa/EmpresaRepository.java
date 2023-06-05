package br.com.molens.odontoDelta.gateway.dataprovider.repository.empresa;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findById(Long id);
}
