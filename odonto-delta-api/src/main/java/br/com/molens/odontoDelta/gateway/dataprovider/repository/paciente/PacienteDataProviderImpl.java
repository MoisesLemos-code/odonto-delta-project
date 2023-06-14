package br.com.molens.odontoDelta.gateway.dataprovider.repository.paciente;

import br.com.molens.odontoDelta.domain.entity.Paciente;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.PacienteConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PacienteDataProviderImpl implements PacienteDataProvider {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteConverter converter;

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id).map(converter::to);
    }

    @Override
    public Paciente inserir(Paciente paciente) {
        paciente.setId(null);
        PacienteEntity pacienteEntity = repository.save(converter.from(paciente));
        return converter.to(pacienteEntity);
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        PacienteEntity pacienteEntity = repository.save(converter.from(paciente));
        return converter.to(pacienteEntity);
    }

    @Override
    public void remover(Paciente paciente) {
        repository.deleteById(paciente.getId());
    }
}
