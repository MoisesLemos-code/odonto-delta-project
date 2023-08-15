package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    Optional<Municipio> findById(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Municipio obj WHERE obj.estado.id = :idEstado ORDER BY obj.nome ASC")
    List<Municipio> buscarTodosPorEstado(@Param("idEstado") Long idEstado);
}
