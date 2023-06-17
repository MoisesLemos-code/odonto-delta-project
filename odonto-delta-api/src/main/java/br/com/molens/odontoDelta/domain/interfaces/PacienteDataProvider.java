package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;

import java.util.Optional;

public interface PacienteDataProvider {

    Optional<Paciente> buscarPorId(Long pacienteId, Long empresaId);

    boolean existeCnpjCpf(String cpfCnpj);

    Long inserir(Paciente paciente);

    Paciente atualizar(Paciente paciente);

    void remover(Long id);

    ListaPaginada<Paciente> buscaPaginadaComFiltro(Paciente.Filtro filtro);

    Optional<Paciente> buscarPorCnpjCpf(String cnpjCpf);
}
