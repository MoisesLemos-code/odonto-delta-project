package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;

import java.util.List;
import java.util.Optional;

public interface FichaPacienteDataProvider {

    Optional<FichaPaciente> buscarPorId(Long fichaPacienteId);

    Long inserir(FichaPaciente fichaPaciente);

    FichaPaciente atualizar(FichaPaciente fichaPaciente);

    List<FichaPaciente> buscarPorPaciente(Long pacienteId);
}
