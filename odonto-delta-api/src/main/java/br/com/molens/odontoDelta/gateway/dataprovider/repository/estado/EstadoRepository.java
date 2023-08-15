package br.com.molens.odontoDelta.gateway.dataprovider.repository.estado;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Estado obj ORDER BY obj.nome ASC")
    List<Estado> buscarTodos();
}
