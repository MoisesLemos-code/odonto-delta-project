package br.com.molens.odontoDelta.gateway.repository.fichaPaciente;

import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM FichaPaciente obj WHERE obj.paciente.id = :pacienteId")
    List<FichaPaciente> buscarPorPaciente(@Param("pacienteId") Long pacienteId);
}
