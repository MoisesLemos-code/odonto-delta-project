package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.Paciente;

import java.util.Optional;

public interface PacienteDataProvider {

    Optional<Paciente> buscarPorId(Long id);

    Paciente inserir(Paciente paciente);

    Paciente atualizar(Paciente paciente);

    void remover(Paciente paciente);
}