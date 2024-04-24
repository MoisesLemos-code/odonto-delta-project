package br.com.molens.odontoDelta.gateway.repository.fichaPaciente;

import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FichaPacienteDataProviderImpl implements FichaPacienteDataProvider {

    @Autowired
    private FichaPacienteRepository repository;

    @Override
    public Optional<FichaPaciente> buscarPorId(Long fichaPacienteId) {
        return repository.findById(fichaPacienteId);
    }

    @Override
    public Long inserir(FichaPaciente fichaPaciente) {
        fichaPaciente.setId(null);
        FichaPaciente save = repository.save(fichaPaciente);
        return save.getId();
    }

    @Override
    public FichaPaciente atualizar(FichaPaciente fichaPaciente) {
        return repository.save(fichaPaciente);
    }

    @Override
    public List<FichaPaciente> buscarPorPaciente(Long pacienteId) {
        return repository.buscarPorPaciente(pacienteId);
    }
}
